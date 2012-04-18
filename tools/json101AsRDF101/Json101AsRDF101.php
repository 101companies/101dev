<?php

require_once '../config/configRDF101.php' ;
require_once ABSPATH_MEGALIB.'JsonGraphAsSimpleGraph.php' ;
require_once ABSPATH_MEGALIB.'SimpleGraphAsRDF.php' ;
require_once ABSPATH_MEGALIB.'HTML.php' ;

// Create a SimpleGraph from the existing json file
$graph = jsonGraphToSimpleGraph(
            URL_WIKI_101_JSON_URL,
            WIKI_101_SCHEMA_URL,
            WIKI_101_ENTITY_JSON_MAPPING_URL) ;

// Convert this simple graph to a RDF triple set
$graphasrdf = new SimpleGraphAsRDF() ;
$graphasrdf->addSimpleGraph($graph,RDF_DATA_101_PREFIX_URL,RDF_SCHEMA_101_PREFIX_URL) ;

// Save the triples in different file formats
if (DEBUG) echo "<h2>Saving the triples in files</h2>" ;
$tripleset = $graphasrdf->getTripleSet() ;
$formats='HTML,GraphML,Turtle,RDFXML,RDFJSON,NTriples' ;
$tripleset->saveFiles($formats,RDF_WIKI_101_DATA_GENERATED_CORE_FILENAME) ;



if (DEBUG) echo "<h2>Saving the triples in the RDF store</h2>" ;
// Save the triples in the RDF101 store
$store101 = get101Store() ;
//$store101->reset() ;
$graphasrdf->save($store101,RDF_WIKI_101_NAMED_GRAPH) ;

