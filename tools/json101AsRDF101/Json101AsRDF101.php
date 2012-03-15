<?php

require_once '../config/configRDF101.php' ;
require_once ABSPATH_MEGALIB.'SimpleGraph.php' ;
require_once ABSPATH_MEGALIB.'SimpleGraphAsRDF.php' ;
require_once ABSPATH_MEGALIB.'HTML.php' ;

/**
 * Load the information from json file into a SimpleGraph structure.
 * @param SimpleGraph! $graph  The target graph where the information will 
 * be load. The schema should be already loaded into the graph (that is
 * $graph->SCHEMA should be set).
 * @param JSON! $json the json structure to convert with the top level being 
 * a Map(String!,Set*(Entity)) where String is the extension tag (e.g.
 * "implementations" and the result is the extension of the "implementation"
 * entity kind).  See below for the name of the entity kind.
 * @param Map+(EntityKind!,String!)! This map describes the link between
 * the entity kind name in the schema (e.g. "implementation") and the
 * corresponding top-level tag in the json file (e.g."implementations").
 * This is not necessarily simply a "s" at the because of some irregularities in naming
 * such as "categories" => "category".
 */
function loadJsonIntoGraph($graph,$json,$extensionMap) {
  assert(isset($graph->SCHEMA));  
  // for each entity kind defined in the schema, load the corresponding extension
  foreach (  $graph->SCHEMA->getEntityKinds() as $entitykind ) {
    $jsonExtensionTag = $extensionMap[$entitykind] ;
    if (DEBUG) echo "<li>Loading '$entitykind' extension from top-level tag '$jsonExtensionTag':<br/>".count($json[$jsonExtensionTag])." json entities found." ;
    loadEntityExtensionIntoGraph($graph,$json[$jsonExtensionTag],$entitykind) ;
    if (DEBUG) echo '</br>'.count($graph->DATA[$entitykind])." ".$entitykind."(s) in the resulting graph</li>" ;
  }
}

// type EntityKind == String!  (entity kinds defined in the SimpleSchema)
// Here feature, language, etc. 

/**
 * @param Entity $entityKind
 * @param unknown_type $keyValueOfEntity
 * @return string
 */
function makeEntityId($entityKind,$keyValueOfEntity) { 
  return $entityKind.'/'.strtolower($keyValueOfEntity) ;
}


/**
 * Extract 
 * @param SimpleGraph! $graph The graph object in which to add the instances.
 * The schema of the graph should be already loaded and it define what element
 * will be extracted from the json.
 * @param JSON! $jsonExtensionArray The extension for the given entity kind, represented as
 * an array of entities.
 * @param EntityKind! $entitykind The kind of entities to load.
 */
function loadEntityExtensionIntoGraph($graph,$jsonExtensionArray, $entitykind) {
  assert(isset($graph->SCHEMA));
  $schema = $graph->SCHEMA ;

  // get info about attributes of this type of entities
  $attributes = $schema->getAttributeDescriptions($entitykind) ;
  $key_attribute = $schema->getKeyAttribute($entitykind) ;
  if (DEBUG >= 2) {
    echo "expected attributes are " ;
    print_r($attributes) ;
  }
  
  // for each entity in the file 
  foreach ($jsonExtensionArray as $entity) {
    
    // create the record for the entity
    $key_value = $entity[$key_attribute] ;
    $entity_id = makeEntityId($entitykind,$key_value) ;
    $graph->DATA[$entitykind][$entity_id] = array() ;
    
    // process each attribute in the schema depending on its characteristics
    foreach($attributes as $attribute => $attributeinfo) {
      $type = $attributeinfo['type'] ;
      switch ($attributeinfo['tag']) {
        case '@':
        case '!':
          if (! isset($entity[$attribute])) {
            die("attribute $attribute is not set for $entitykind named $key_value");
          }
          $graph->DATA[$entitykind][$entity_id][$attribute]=$entity[$attribute] ;
          break ;
        case '?':
          if (isset($entity[$attribute])) {
            $graph->DATA[$entitykind][$entity_id][$attribute]=$entity[$attribute] ;
          }
          break ;
        case '*':
          if (isset($entity[$attribute])) {
            $graph->DATA[$entitykind][$entity_id][$attribute]=array() ;
            foreach($entity[$attribute] as $element) {
            
              // here we eliminate the 'name' level which is boring and useless in the json model
              $graph->DATA[$entitykind][$entity_id][$attribute][]=makeEntityId($type,$element['name']) ;
            }
          }
          break ;
        default:
          assert(false) ;
      }
    }
  }  
}


;

/**
 * Convert JSON to a simpleGraph
 * @param URL! $jsonUrl URL or filename of the JSON file to convert
 * @param String! $schemaFile URL or Filename of the schema use to direct the conversion
 * @param String! $entityJsonMappingFile URL or filename containing the map from entity kind to json
 * tag. This should be a json file.
 * @return SimpleGraph! return a simple graph.
 */
function jsonToSimpleGraph($jsonUrl, $schemaUrl, $entityJsonMappingUrl) {  
  if (DEBUG) echo '<h2>jsonToSimpleGraph</h2>' ;
  
  if (DEBUG>2) echo "<p>Loading the file $jsonUrl, $schemaUrl and $entityJsonMappingUrl</p>" ;
    
  // load the json file
  $jsonSource = file_get_contents($jsonUrl) ;
  if ($jsonSource===false) {
    die("Cannot open ".$jsonUrl) ;
  }
  $json = json_decode($jsonSource,true) ;
  if (! is_array($json)) {
    if (DEBUG) var_dump($json) ;
    die("incorrect json value in $jsonUrl : $jsonSource") ;
  }  
  // load the schema file
  $schemaSource = file_get_contents($schemaUrl) ;
  if ($schemaSource === false){
    die("cannot open $schemaUrl") ;
  }
  $schema = new SimpleSchema($schemaSource) ;
  
  // load the mapping file
  $mappingSource = file_get_contents($entityJsonMappingUrl) ;
  if ($mappingSource === false){
    die("cannot open $entityJsonMappingUrl") ;
  } 
  $entityJsonMapping = json_decode($mappingSource,true) ;
  if (! is_array($entityJsonMapping)) {
    if (DEBUG) var_dump($entityJsonMapping) ;
    die("incorrect mapping in $entityJsonMappingUrl : $mappingSource") ;
  }
  
  // create the graph
  $graph = new SimpleGraph($schema) ;
  loadJsonIntoGraph($graph,$json,$entityJsonMapping) ;

  // checking the constraint on the simple graph (referential constraints)
  if (DEBUG) echo '<h1>Checking constraints</h1>' ;
  $graph->checkConstraints() ;
  return $graph ;
}


/**
 * Store a graph to a rdf store.
 * @param SimpleGraph! $graph  
 * @param RDFStore! $store 
 * @return number of triples added to the store
 */
function simpleGraphToRDFStore($graph,$store,$dataprefixurl,$schemaprefixurl,$namedgraph) {
  //------------------------------------------------------------------------
  //--- converting the graph to triples ------------------------------------
  //------------------------------------------------------------------------
  
  if (DEBUG) echo '<h2>Converting to RDF triples</h2>' ;
  $graphasrdf = new SimpleGraphAsRDF() ;
  $graphasrdf->addSimpleGraph($graph,$dataprefixurl,$schemaprefixurl) ;
  // echo arrayMapToHTMLTable($graphasrdf->getTriples()) ;
  $tripleSet = $graphasrdf->getTripleSet() ;

  //------------------------------------------------------------------------
  //--- saving the triples to the RDF store --------------------------------
  //------------------------------------------------------------------------  
  if (DEBUG) echo '<h2>Saving the triples into the RDF store</h2>' ;
  $store = get101Store() ;
  $n = $store->loadTripleSet($tripleSet,$namedgraph,false) ;
  if (DEBUG) echo '<p>'.$n.' triples inserted into the store' ;
  return $n ;
}


$graph = jsonToSimpleGraph(
            URL_WIKI_101_JSON_URL,
            WIKI_101_SCHEMA_URL,
            WIKI_101_ENTITY_JSON_MAPPING_URL) ;


$store101 = get101Store() ;
$store101->reset() ;

simpleGraphToRDFStore(
    $graph, 
    $store101,
    RDF_DATA_101_PREFIX_URL,
    RDF_SCHEMA_101_PREFIX_URL,
    RDF_WIKI_101_NAMED_GRAPH) ;

