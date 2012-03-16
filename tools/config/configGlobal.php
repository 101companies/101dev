<?php

/*-%<-----------------------------------------------------------------
 CUT AND PASTE THE CODE BELOW IN THE FILE localConfigGlobal.php
and change the value of the settings according to your local
configuration. Note that config/localConfig* files are ignored
during github commit because of .gitignore

<?php
//--------------------------------------------------------------------
//   DEBUGING AND LOGS
//--------------------------------------------------------------------
// change this constant if you want more DEBUG or 0 for no DEBUG
define('DEBUG',1) ;



//--------------------------------------------------------------------
//   PATH TO LIBRARIES
//--------------------------------------------------------------------

// All constants starting with ABSPATH are absolute paths ending with /


// Absolute path to the 101dev package
// No reason to change this constant
define('ABSPATH_BASE',dirname(dirname(__DIR__)).'/') ;

// Absolute path to the external libraries
// This constant is used in the 
define('ABSPATH_EXTERNAL_LIBARIES',ABSPATH_BASE.'../') ;

// megalib directory
// Change it only if it is not in the regular place for libraries
define('ABSPATH_MEGALIB',ABSPATH_BASE.'../megalib/') ;

-------------------------------------------------------------------*/

/**
 * This file describe global constants for the 101 project.
 * Some other configXXX files are used to configure particular components.
 * The constants that are to be changed on particular installations
 * are to be found in localConfig* files.
 * There is a priori no reason to change this file for deployement.
 */ 

require_once 'localConfigGlobal.php' ;   // if it fails, see above!

//---------------------------------------------------------------
//        URL scheme of the 101 project 
//---------------------------------------------------------------

// project url
define('URL_PROJECT_101_URL','http://101companies.org') ;

// wiki json representation
define('URL_WIKI_101_JSON_URL',URL_PROJECT_101_URL.'/~wiki101/all.json') ;



