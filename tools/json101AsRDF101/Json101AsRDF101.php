<?php
define ('DEBUG',1) ;

// absolute path to the 101dev package
define('ABSPATH_BASE',dirname(dirname(__DIR__)).'/') ;

// the directory where the megalib is installed 
define('ABSPATH_MEGALIB',ABSPATH_BASE.'../megalib/') ;

// the directory where the files are looked for
define('ABSPATH_DATA_DIRECTORY',ABSPATH_BASE.'tools/data/') ;

require_once ABSPATH_MEGALIB.'SimpleGraph.php' ;
require_once ABSPATH_MEGALIB.'SimpleGraphAsRDF.php' ;
require_once ABSPATH_MEGALIB.'HTML.php' ;

define('SCHEMA101FILE','Schema101.ss') ;
define('DATA101PREFIX','http://data.101companies.org/data/') ;
define('ONTOLOGY101PREFIX','http://data.101companies.org/schema#') ;



/**
 * Load all json file into a SimpleGraph structure.
 * @param SimpleGraph! $graph  The target graph where the information will 
 * be load. The kinds of entity to load are extracted by the schema.
 */
function loadAllJsonFilesIntoGraph(/*SimpleGraph!*/$graph) {
  foreach (  $graph->SCHEMA->getEntityKinds() as $entitykind ) {
    loadJsonFileIntoGraph($graph,$entitykind) ;
    if (DEBUG) echo count($graph->DATA[$entitykind])." ".$entitykind."(s)" ;
  }
}

// type EntityKind == String!  (entity kinds defined in the SimpleSchema)
// Here feature, language, etc. 
//


function makeEntityId($entityKind,$keyValueOfEntity) { 
  return $entityKind.'/'.strtolower($keyValueOfEntity) ;
}


/**
 * 
 * @param EntityKind! $entitykind The kind of entities to load.
 * Currently there is one file per entity kind. The file is 
 * in the directory ABSPATH_DATA_DIRECTORY.$entitykind.'.json'.
 * @param SimpleGraph! $graph The target graph where the information will go.
 */
function loadJsonFileIntoGraph($graph, $entitykind) {
  $schema = $graph->SCHEMA ;

  // load the file and transform it from json to an array
  $filename = ABSPATH_DATA_DIRECTORY.$entitykind.".json" ;
  if (DEBUG) echo "<h1>loading $entitykind entities</h1>" ;
  $json = file_get_contents($filename);
  $entities_array = json_decode($json,true)  ;
  if ($entities_array == NULL) {
    die ("Error in loading $filename : "
         ."json_last_error=".json_last_error_message()) ;
  }
  //print_r($entities_array) ;
  
  // get info about attributes of this type of entities
  $attributes = $schema->getAttributeDescriptions($entitykind) ;
  $key_attribute = $schema->getKeyAttribute($entitykind) ;
  if (DEBUG >= 2) {
    echo "expected attributes are " ;
    print_r($attributes) ;
  }
  
  // for each entity in the file 
  foreach ($entities_array as $entity) {
    
    // create the record for the entity
    $key_value = $entity[$key_attribute] ;
    $entity_id = makeEntityId($entitykind,$key_value) ;
    $DATA[$entitykind][$entity_id] = array() ;
    
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



//------------------------------------------------------------------------
//--- loading the json file to a simple graph structure ------------------
//------------------------------------------------------------------------  
// create an empty graph with the appropriate schema
$schema = new SimpleSchema(file_get_contents(SCHEMA101FILE)) ;
$graph = new SimpleGraph($schema) ;

// load the graph from the json files stored in ABSPATH_DATA_DIRECTORY
if (DEBUG) echo '<h1>Loading the json files </h1>'
                .'<p>Loading files from '.ABSPATH_DATA_DIRECTORY.'</p>' ;
loadAllJsonFilesIntoGraph($graph) ;

// checking the constraint on the simple graph (referential constraints)
if (DEBUG) echo '<h1>Checking constraints</h1>' ;
$graph->checkConstraints() ;



//------------------------------------------------------------------------
//--- converting the graph to triples ------------------------------------
//------------------------------------------------------------------------  
  
if (DEBUG) echo '<h1>Converting to RDF triples</h1>' ;
  
$graphasrdf = new SimpleGraphAsRDF() ;
$graphasrdf->addSimpleGraph($graph,DATA101PREFIX,ONTOLOGY101PREFIX) ;
echo arrayMapToHTMLTable($graphasrdf->getTriples()) ;


//$store->reset() ;
//$triples=simpleGraphToRDFtripletSet($graph,ONTOLOGY101PREFIX) ;
//saveTriplesToRDFStore($triples,$store,GRAPH101URI) ;






