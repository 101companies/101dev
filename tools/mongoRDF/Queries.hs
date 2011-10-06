{-# LANGUAGE OverloadedStrings, ExtendedDefaultRules #-}
module Queries(queryCoverage, queryFeatures, queryImpls) where

import Database.MongoDB
import qualified Data.String.Utils as S

import Types
import ActionRunner

getTitle (String s) = unpack s  

queryCoverage :: Pipe -> IO Coverage 
queryCoverage pipe = do
  maybeDocs <- run pipe $ find (select ["ns" =: "101implementation"] "page") 
                         {project = ["title" =: 1, "sections" =: 1, "_id" =: 0]}
                    >>= rest
  -- get list of features for all implementations                  
  let featuress = case maybeDocs of                     
            Right docs -> map (\doc -> (getTitle $ valueAt "title" $ doc, 
                                        getFeatures $ valueAt "sections" $ doc)) docs
            Left e    -> error $ show e
  --mapM insertFeatures featuress
  mapM_ (putStrLn.show) featuress
  return $ featuress
  
getFeatures (Array secs) = saveHead $ map extractFeatures $ filter isFSec secs
    where
      saveHead [] = []
      saveHead [x] = x  
      isFSec (Doc c) = valueAt "tag" c == String "Features"
      isFSec _       = False  
      extractFeatures (Doc c) = case (valueAt "content" c) of
              String s -> map (S.replace "101feature:" "") $ 
                          filter (S.startswith "101feature") $
                          map (S.replace "*[[" "") $  
                          S.split "]]" $ 
                          S.replace "* " "*" $ 
                          unpack s
                            
queryFeatures = queryNamespace "101feature" 

queryImpls =  queryNamespace "101implementation"     
      
queryNamespace :: String -> Pipe -> IO [String] 
queryNamespace ns pipe = do
  maybeDocs <- run pipe $ find (select ["ns" =: ns] "page") 
                         {project = ["title" =: 1, "_id" =: 0]}
                          >>= rest
  return $ case maybeDocs of                     
            Right docs -> map (getTitle.(valueAt "title")) docs
            Left e    -> error $ show e     