{-# LANGUAGE OverloadedStrings, ExtendedDefaultRules #-}
module X2Graph
  ( implTitle2Graph
  , featTitle2Graph
  , coverage2Graph) 
where

import Database.MongoDB
import Types
import Text.RDF.Core
import Text.RDF.TriplesGraph
import Text.RDF.Namespace
import Data.Map(fromList)
import Data.Maybe

-- helpers
ns101 = "http://www.101companies.org/ns#"
prefixMap = PrefixMappings $ fromList [(fromJust $ prefixOf rdf, uriOf rdf)]

titTriple tit sort = triple 
      (unode $ s2b $ ns101 ++ sort ++ tit)
      (unode $ s2b $ ns101 ++ sort ++ "name") 
      (lnode $ TypedL (s2b tit) (mkFastString (s2b ((b2s (uriOf rdfs)) ++ "string"))))
-- /helpers

title2Graph :: String -> String -> TriplesGraph
title2Graph sort t = mkGraph [titTriple t sort] Nothing prefixMap

-- create implementation title graph
implTitle2Graph :: String -> TriplesGraph
implTitle2Graph = title2Graph "impl"
    
-- create feature title graph
featTitle2Graph :: String -> TriplesGraph
featTitle2Graph  = title2Graph "impl"
      
-- create coverage bags
coverage2Graph :: Coverage -> TriplesGraph
coverage2Graph cov = mkGraph (concat (map cov2Triples cov)) Nothing prefixMap
  where
    cov2Triples (tit, implcov) =  (map (iFeatTriple tit) (zip implcov [1..length implcov])) 
                                ++ [assoTriple tit, typeTriple tit]
    assoTriple tit = triple
      (unode $ s2b $ ns101 ++ "impl" ++ tit)
      (unode $ s2b $ ns101 ++ "implCoverage")
      (bnode $ s2b $ "_:" ++ tit ++ "cov")
    typeTriple tit = triple
      (bnode $ s2b $ "_:" ++ tit ++ "cov")
      (unode $ s2b $ (b2s (uriOf rdf)) ++ "type") 
      (unode $ s2b $ (b2s (uriOf rdf)) ++ "Bag")
    iFeatTriple tit (ftit,n) = triple
      (bnode $ s2b $ "_:" ++ tit ++ "cov")
      (unode $ s2b $ (b2s (uriOf rdf)) ++ "_" ++ show n)
      (unode $ s2b $ ns101 ++ "feat" ++ ftit)
      