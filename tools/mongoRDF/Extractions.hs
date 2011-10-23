{-# LANGUAGE OverloadedStrings, ExtendedDefaultRules #-}
module Extractions where

import Database.MongoDB
import Types
import Queries
import X2Graph
import Text.RDF.Core
import Text.RDF.NTriplesSerializer
import Text.RDF.TriplesGraph
import Control.Monad

extractImpls :: Pipe -> IO Triples
extractImpls pipe = 
  liftM (concat.(map (\tit -> triplesOf $ implTitle2Graph tit))) (queryImpls pipe)
  
extractFeats :: Pipe -> IO Triples
extractFeats pipe =
  liftM (concat.(map (\tit -> triplesOf $ featTitle2Graph tit))) (queryFeatures pipe)
  
extractTechs:: Pipe -> IO Triples
extractTechs pipe =
  liftM (concat.(map (\tit -> triplesOf $ techTitle2Graph tit))) (queryTechs pipe)
  
extractLangs :: Pipe -> IO Triples
extractLangs pipe =
  liftM (concat.(map (\tit -> triplesOf $ langTitle2Graph tit))) (queryLangs pipe)    
  
extractCoverage :: Pipe -> IO Triples
extractCoverage pipe = 
  liftM (triplesOf.coverage2Graph) (queryCoverage pipe)
  
extractLangUsage :: Pipe -> IO Triples
extractLangUsage pipe = 
  liftM (triplesOf.langUsage2Graph) (queryLangUsage pipe)
  
extractTechUsage :: Pipe -> IO Triples
extractTechUsage pipe = 
  liftM (triplesOf.techUsage2Graph) (queryTechUsage pipe)
                           