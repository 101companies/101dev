<?php

define('BASE_PATH',str_replace('tagcloudgenerator','',dirname(__FILE__)));
require_once("config.php");
require_once(BASE_PATH . "API/ApiWrapper.php");

function writeFile($fileName, $arr){
  $myFile =  $GLOBALS['cloudy_data_home'] . $fileName;
 $fh = fopen($myFile, 'w') or die("can't open file");
 foreach($arr as $el){
   $el = str_replace(" ", "_", $el);
   fwrite($fh, "\"" . $el . "\"\n" );
 }
fclose($fh);
}

$allTechnologies = array();
$allLanguages = array();

 $impl = getAllImplementations();
 if($impl == NULL) die ("pages have not been downloaded, please try a bit later" . PHP_EOL);
 foreach($impl as $i){
  $page = getPageContent($i['title']);
  $technologies = extractTechnologies($page);
  $languages = extractLanguages($page);
  flattern($technologies, &$allTechnologies); 
  flattern($languages, &$allLanguages); 
 }

 writeFile("TechnologiesInImplementations.txt", $allTechnologies);
 writeFile("LanguagesInImplementations.txt", $allLanguages);

$allTechnologies = array();
$allLanguages = array();

 $pages = getAllPages();
 if($pages == NULL) die ("pages have not been downloaded, please try a bit later" . PHP_EOL);
 
 foreach($pages as $p){
  $page = getPageContent($p['title']);
  $technologies = extractTechnologies($page);
  $languages = extractLanguages($page);
  if($technologies != NULL) flattern($technologies, &$allTechnologies); 
  if($languages != NULL) flattern($languages, &$allLanguages); 
 }

writeFile("TechnologiesAnywhere.txt", $allTechnologies);
writeFile("LanguagesAnywhere.txt", $allLanguages);

