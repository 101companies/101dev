<?php

/*-%<-----------------------------------------------------------------
      CUT AND PASTE THE CODE BELOW IN THE FILE localConfigRDF101.php
      and change the value of the settings according to your local
      configuration. Note that config/localConfig* files are ignored 
      during github commit because of .gitignore
      
<?php
// this define the access to the RDF 101 store
define('RDF_101_DATABASE_NAME','arc2_db101') ;
define('RDF_101_DATABASE_USER','db101') ;
define('RDF_101_DATABASE_PASSWORD','xxxxxxxxxxxxxxxxxxxxxxxxxxxxx') ;
define('RDF_101_STORE_NAME','s101') ;

-------------------------------------------------------------------*/

require_once '../config/configGlobal.php' ;  
require_once '../config/localConfigRDF101.php' ;  // file missing? See above!!!

// information for the database containing the s101 rdf store
require_once ABSPATH_MEGALIB.'config/configRDF.php' ;

// schema file that is used by the converter json to rdf to select the
// information that will go in the rdf
define('WIKI_101_SCHEMA_URL','./Schema101.ss') ;

// mapping between entity kinds and top-level json tags
define('WIKI_101_ENTITY_JSON_MAPPING_URL','./EntityKind101ToJson101Tag.json') ;


define('RDF_DATA_101_PREFIX','d101') ;
define('RDF_DATA_101_PREFIX_URL',URL_PROJECT_101_URL.'/data/') ;

define('RDF_SCHEMA_101_PREFIX_URL',URL_PROJECT_101_URL.'/schema#') ;
define('RDF_SCHEMA_101_PREFIX','s101') ;

define('RDF_WIKI_101_NAMED_GRAPH',URL_PROJECT_101_URL.'/dumps/Wiki101Full.rdf') ;
define('RDF_CODE_101_NAMED_GRAPH',URL_PROJECT_101_URL.'/dumps/Code101Full.rdf') ;



require_once ABSPATH_MEGALIB.'RDF.php' ;
/**
 * Return the 101 store.
 * This function should execute with no error if the database is set properly.
 * @return RDFStore!
 */
function get101Store() {
  $dbaccount = new DatabaseAccount(
      RDF_101_DATABASE_NAME,
      RDF_101_DATABASE_USER,
      RDF_101_DATABASE_PASSWORD) ;
  
  // define the prefixes available in SPARQL endpoint for instance
  $prefixes101 = array (
      RDF_DATA_101_PREFIX=>RDF_DATA_101_PREFIX_URL, 
      RDF_SCHEMA_101_PREFIX=>RDF_SCHEMA_101_PREFIX_URL) ;
  
  $configurationStore101 = new RDFStoreConfiguration(
      $prefixes101,
      $dbaccount, 
      RDF_101_STORE_NAME) ;
  return new RDFStore($configurationStore101) ;
}
