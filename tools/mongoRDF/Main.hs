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
  putStrLn "Extracting metadata..."
  itts <- extractImpls pipe
  ftts <- extractFeats pipe
  tets <- extractTechs pipe
  lts <- extractLangs pipe
  cts <- extractCoverage pipe
  tuts <- extractTechUsage pipe
  luts <- extractLangUsage pipe 
  putStrLn $ show $ length $ cts
  let allTriples = concat [itts, ftts, tets, lts, cts, tuts, luts]
  handle <- openFile "wikimeta.nt" WriteMode
  writeTriples handle allTriples
  hClose handle
  run pipe (delete (select [] "meta")) 
  putStrLn "Saving triples"
  forM (zip allTriples [1..length allTriples]) $ \(t,n) -> do
    putStrLn ("Saving Triple " ++ show n ++ "/" ++  show (length allTriples) ++ "..")
    saveTriple pipe t
  return ()  