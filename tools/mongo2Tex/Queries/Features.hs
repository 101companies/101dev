{-# LANGUAGE OverloadedStrings, ExtendedDefaultRules #-}
module Queries.Features where

import Database.MongoDB

import Queries.Utils
import ActionRunner

queryFeatures :: Pipe -> IO [String] 
queryFeatures pipe = do
  maybeDocs <- run pipe $ find (select ["ns" =: "101feature"] "page") 
                         {project = ["title" =: 1, "_id" =: 0]}
                          >>= rest
  return $ case maybeDocs of                     
            Right docs -> map (getString.(valueAt "title")) docs
            Left e    -> error $ show e
                                  