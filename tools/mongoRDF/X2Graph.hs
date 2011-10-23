{-# LANGUAGE OverloadedStrings, ExtendedDefaultRules #-}
module X2Graph
  ( implTitle2Graph
  , featTitle2Graph
  , techTitle2Graph
  , langTitle2Graph
  , coverage2Graph
  , techUsage2Graph
  , langUsage2Graph) 
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
      (unode $ s2b $ ns101 ++ sort ++ "Name") 
      (lnode $ TypedL (s2b tit) (mkFastString (s2b ((b2s (uriOf rdfs)) ++ "string"))))
-- /helpers

title2Graph :: String -> String -> TriplesGraph
title2Graph sort t = mkGraph [titTriple t sort] Nothing prefixMap

-- create implementation title graph
implTitle2Graph :: String -> TriplesGraph
implTitle2Graph = title2Graph "impl"
    
-- create feature title graph
featTitle2Graph :: String -> TriplesGraph
featTitle2Graph  = title2Graph "feat"

-- create technology title graph
techTitle2Graph :: String -> TriplesGraph
techTitle2Graph = title2Graph "tech"

-- create language title graph
langTitle2Graph :: String -> TriplesGraph
langTitle2Graph = title2Graph "lang"
      
-- create coverage bags
coverage2Graph :: Coverage -> TriplesGraph
coverage2Graph cov = mkGraph (concat (map cov2Triples cov)) Nothing prefixMap
  where
    cov2Triples (tit, implcov) =  (map (iFeatTriple tit) (zip implcov [1..length implcov])) 
                                ++ [assoTriple tit, typeTriple tit]
    assoTriple tit = triple
      (unode $ s2b $ ns101 ++ "impl" ++ tit)
      (unode $ s2b $ ns101 ++ "featCoverage")
      (bnode $ s2b $ "_:" ++ tit ++ "cov")
    typeTriple tit = triple
      (bnode $ s2b $ "_:" ++ tit ++ "cov")
      (unode $ s2b $ (b2s (uriOf rdf)) ++ "type") 
      (unode $ s2b $ (b2s (uriOf rdf)) ++ "Bag")
    iFeatTriple tit (ftit,n) = triple
      (bnode $ s2b $ "_:" ++ tit ++ "cov")
      (unode $ s2b $ (b2s (uriOf rdf)) ++ "_" ++ show n)
      (unode $ s2b $ ns101 ++ "feat" ++ ftit)
      
techUsage2Graph = usage2Graph "tech"         
      
langUsage2Graph = usage2Graph "lang" 
      
-- create usage bags
usage2Graph :: String -> Usage -> TriplesGraph
usage2Graph kind us = mkGraph (concat (map us2Triples us)) Nothing prefixMap
  where
    us2Triples (tit, implus) =  (map (iUseTriple tit) (zip implus [1..length implus])) 
                                ++ [assoTriple tit, typeTriple tit]
    assoTriple tit = triple
      (unode $ s2b $ ns101 ++ "impl" ++ tit)
      (unode $ s2b $ ns101 ++ kind ++"Usage")
      (bnode $ s2b $ "_:" ++ tit ++ kind ++ "use")
    typeTriple tit = triple
      (bnode $ s2b $ "_:" ++ tit ++ kind ++ "use")
      (unode $ s2b $ (b2s (uriOf rdf)) ++ "type") 
      (unode $ s2b $ (b2s (uriOf rdf)) ++ "Bag")
    iUseTriple tit (utit,n) = triple
      (bnode $ s2b $ "_:" ++ tit ++ kind ++ "use")
      (unode $ s2b $ (b2s (uriOf rdf)) ++ "_" ++ show n)
      (unode $ s2b $ ns101 ++ kind ++ utit)      