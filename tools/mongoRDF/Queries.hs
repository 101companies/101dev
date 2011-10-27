{-# LANGUAGE OverloadedStrings, ExtendedDefaultRules #-}
module Queries where

import Database.MongoDB
import qualified Data.String.Utils as S
import Data.List.Split

import Types
import ActionRunner

getTitle (String s) = unpack s  

queryListed :: String -> UString -> String -> Pipe -> IO [(String,[String])]
queryListed ns sectionName elemNs pipe  = do
  maybeDocs <- run pipe $ find (select ["ns" =: ns] "page") 
                         {project = ["title" =: 1, "sections" =: 1, "_id" =: 0]}
                    >>= rest
  return $ case maybeDocs of                     
            Right docs -> map (\doc -> (getTitle $ valueAt "title" $ doc, 
                                        getX sectionName elemNs $ valueAt "sections" $ doc)) docs
            Left e    -> error $ show e
  
queryCoverage = queryListed "101implementation" "Features" "101feature"

queryLangUsage = queryListed "101implementation" "Languages" "Language"  

queryTechUsage = queryListed "101implementation" "Technologies" "Technology"
  
getX :: UString -> String -> Value -> [String] 
getX sectionName elemNs (Array secs) = saveHead $ map extractX$ filter isFSec secs
    where
      saveHead [] = []
      saveHead [x] = x  
      isFSec (Doc c) = valueAt "tag" c == String sectionName
      isFSec _       = False  
      extractX (Doc c) = case (valueAt "content" c) of
              String s -> map (S.replace (elemNs ++ ":") "") $ 
                          filter (S.startswith (elemNs ++ ":")) $
                          map (S.replace "*[[" "") $  
                          S.split "]]" $ 
                          S.replace "* " "*" $
                          head $ (splitOn "|") $
                          S.replace "\n" "" $
                          unpack s
                            
queryFeatures = queryNamespace "101feature" 

queryImpls =  queryNamespace "101implementation"

queryTechs = queryNamespace "Technology"     

queryLangs = queryNamespace "Language"     
      
queryNamespace :: String -> Pipe -> IO [String] 
queryNamespace ns pipe = do
  maybeDocs <- run pipe $ find (select ["ns" =: ns] "page") 
                         {project = ["title" =: 1, "_id" =: 0]}
                          >>= rest
  return $ case maybeDocs of                     
            Right docs -> map (getTitle.(valueAt "title")) docs
            Left e    -> error $ show e     