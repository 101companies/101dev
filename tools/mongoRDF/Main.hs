{-# LANGUAGE OverloadedStrings, ExtendedDefaultRules #-}
module Main where

import Database.MongoDB
import Types
import Queries
import X2Graph
import Extractions
import TripleSaver
import ActionRunner
import Text.RDF.Core hiding (select)
import Text.RDF.NTriplesSerializer
import Text.RDF.TriplesGraph hiding (select)
import Control.Monad
import IO

main = do 
  pipe <- runIOE $ connect (host "127.0.0.1")
  itts <- extractImpls pipe
  ftts <- extractFeats pipe
  cts <- extractCoverage pipe 
  let allTriples = concat [itts, ftts, cts]
  handle <- openFile "wikimeta.nt" WriteMode
  writeTriples handle allTriples
  hClose handle
  run pipe (delete (select [] "wiki.meta")) 
  mapM_ (saveTriple pipe) allTriples 
  return allTriples
                                                           