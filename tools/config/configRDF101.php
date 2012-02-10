<?php
// this file should be restructure to follow the architecture in megalib/config
// making the distinction between constants and localConfiguration

define ('DEBUG',1) ;

// absolute path to the 101dev package
define('ABSPATH_BASE',dirname(dirname(__DIR__)).'/') ;

// the directory where the megalib is installed
define('ABSPATH_MEGALIB',ABSPATH_BASE.'../megalib/') ;


// information for the database containing the s101 rdf store
require_once ABSPATH_MEGALIB.'config/configRDF.php' ;
/**
* To simplify the database account is specified below with the credential
* used for megalib/tests/testRDF.php. Create the database following the instructions
* in megalib/configRDF.php. Then check with testRDF.php that the database can be
* used. 
*/ 
define('RDF_101_DATABASE_NAME',RDF_TEST_DATABASE_NAME) ;
define('RDF_101_DATABASE_USER',RDF_TEST_DATABASE_USER) ;
define('RDF_101_DATABASE_PASSWORD',RDF_TEST_DATABASE_PASSWORD) ;



define('STORE101','s101') ;

// the directory where the json files are looked for (used by the converter)
define('ABSPATH_DATA_DIRECTORY',ABSPATH_BASE.'tools/data/') ;

define('SCHEMA101FILE','Schema101.ss') ;

// various URI (we don't really care for know as rdf file will not be served right now).
define('DATA101PREFIX','http://data.101companies.org/data/') ;
define('ONTOLOGY101PREFIX','http://data.101companies.org/schema#') ;
define('COMPLETE101RESOURCEURI','http://data.101companies.org/dumps/Full101Store-2012-10-02.rdf') ;

/**
 * Then this function should execute with no error if the database is set properly.
 * @return RDFStore
 */
require_once ABSPATH_MEGALIB.'RDF.php' ;
function get101Store() {
  $account = new DatabaseAccount(
      RDF_101_DATABASE_NAME,
      RDF_101_DATABASE_USER,
      RDF_101_DATABASE_PASSWORD) ;

  $prefixes101 = array ('d101'=>DATA101PREFIX, 's101'=>ONTOLOGY101PREFIX) ;
  $configurationStore101 = new RDFStoreConfiguration($prefixes101,$account, STORE101) ;
  return new RDFStore($configurationStore101) ;
}
