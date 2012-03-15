<?php
/**
 * This file describe global constants for the 101 project.
 * The constants that are to be changed on particular installations
 * are to be found in localConfig* files.
 * There is a priori no reason to change this file for deployement.
 */ 


define('DEBUG',1) ;

// All constants starting with ABSPATH are absolute paths ending with /

// absolute path to the 101dev package
define('ABSPATH_BASE',dirname(dirname(__DIR__)).'/') ;

// absolute path to the external libraries
define('ABSPATH_EXTERNAL_LIBARIES',ABSPATH_BASE.'../') ;


// the directory where the megalib is installed
define('ABSPATH_MEGALIB',ABSPATH_BASE.'../megalib/') ;


//---------------------------------------------------------------
//        URL scheme of the 101 project 
//---------------------------------------------------------------

// project url
define('URL_PROJECT_101_URL','http://101companies.org') ;

// wiki json representation
define('URL_WIKI_101_JSON_URL',URL_PROJECT_101_URL.'/~wiki101/all.json') ;



