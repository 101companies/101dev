{-# LANGUAGE OverloadedStrings, ExtendedDefaultRules #-}
module TripleSaver where

import Database.MongoDB
import Text.RDF.NTriplesSerializer
import Text.RDF.TriplesGraph
import Text.RDF.Core

import ActionRunner

saveTriple :: Pipe -> Triple -> IO ()
saveTriple pipe t = do 
  run pipe (insert "meta" tripleDoc)
  return ()
    where
      tripleDoc = [ "subject" =: showNode (subjectOf t)
                  , "predicate" =: showNode (predicateOf t)
                  , "object" =: showNode (objectOf t)]
        
showNode (UNode fs) = "<" ++ show fs ++ ">"
showNode (BNode fs) = show fs
showNode (LNode (TypedL l t)) = "\"" ++ b2s l ++ "\"^^<" ++ show t ++ ">"              
