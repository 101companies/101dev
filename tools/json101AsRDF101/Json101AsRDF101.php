<?php

require_once '../config/configRDF101.php' ;
require_once ABSPATH_MEGALIB.'JsonGraphAsSimpleGraph.php' ;
require_once ABSPATH_MEGALIB.'SimpleGraphAsRDF.php' ;
require_once ABSPATH_MEGALIB.'HTML.php' ;

$graph = jsonGraphToSimpleGraph(
            URL_WIKI_101_JSON_URL,
            WIKI_101_SCHEMA_URL,
            WIKI_101_ENTITY_JSON_MAPPING_URL) ;


$store101 = get101Store() ;
//$store101->reset() ;
$graphasrdf = new SimpleGraphAsRDF() ;
$graphasrdf->addSimpleGraph($graph,RDF_DATA_101_PREFIX_URL,RDF_SCHEMA_101_PREFIX_URL) ;
$graphasrdf->save($store101,RDF_WIKI_101_NAMED_GRAPH) ;

