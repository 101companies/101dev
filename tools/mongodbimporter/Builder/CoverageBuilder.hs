{-# LANGUAGE OverloadedStrings, ExtendedDefaultRules #-}
module Builder.CoverageBuilder(coverageBuilder) where

import Types
import Database.MongoDB
import qualified Data.String.Utils as S

import ActionRunner

coverageBuilder :: Builder [(String,[String])]
coverageBuilder = Builder "101implementation" ["Features"] coverageHandler 


coverageHandler :: Pipe -> IO [(String,[String])] 
coverageHandler pipe = do
  maybeDocs <- run pipe $ find (select ["ns" =: "101implementation"] "page") 
                         {project = ["title" =: 1, "sections" =: 1, "_id" =: 0]}
                    >>= rest
  -- get list of features for all implementations                  
  let featuress = case maybeDocs of                     
            Right docs -> map (\doc -> (getTitle $ valueAt "title" $ doc, 
                                        getFeatures $ valueAt "sections" $ doc)) docs
            Left e    -> error $ show e
  mapM insertFeatures featuress
  return $ featuress
    where
      insertFeatures (title, features) = do 
        run pipe $ insert "coverage" $ ["title" =: title, "features" =: features]
  
getTitle (String s) = unpack s  
  
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
                          S.replace " " "" $
                          unpack  s