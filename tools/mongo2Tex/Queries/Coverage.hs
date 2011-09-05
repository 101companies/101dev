{-# LANGUAGE OverloadedStrings, ExtendedDefaultRules #-}
module Queries.Coverage where

import Database.MongoDB

import Queries.Utils
import ActionRunner

queryCoverage :: Pipe -> IO [(String,[String])] 
queryCoverage pipe = do
  maybeDocs <- run pipe $ find (select [] "coverage") 
                         {project = ["title" =: 1, "features" =: 1, "_id" =: 0]}
                          >>= rest
  return $ case maybeDocs of                     
            Right docs -> map (\doc -> (getString $ valueAt "title" $ doc, 
                                        getFeatures $ valueAt "features" $ doc)) docs
            Left e    -> error $ show e
  where            
      getFeatures (Array fs) = map getString fs
                                  