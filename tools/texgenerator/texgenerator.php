<?php

define('BASE_PATH',str_replace('texgenerator','',dirname(__FILE__)));
$outputShallowFolder = BASE_PATH . "texgenerator/tex/ontology/data/shallow/";
$outputDeepFolder = BASE_PATH . "texgenerator/tex/ontology/data/deep/";
$dataFolder = BASE_PATH . "texgenerator/tex/ontology/data/";

$texFolder = BASE_PATH . "texgenerator/tex/content/data/";
$texFolderMatrix = BASE_PATH . "texgenerator/tex/matrix/data/";

require_once(BASE_PATH . 'API/ApiWrapper2.php');
require_once("commandLine.php");
require_once("formatter.php");

function getIntetByLevel($level){
  $default = "\\tab";
  for($i = 0; $i <= $level - 1; $i++){
    $default .= "\\tab";
  }
  return $default . "\\tab";
}


function escape($txt){
  $escaped = str_replace("#", "\\#", $txt);
  return $escaped;
}

class OntyGenerator{
  private $catPage;
  
 function __construct($catPage){
    $this->catPage = $catPage;
  }
  
 function generateCategoryFile(){
  $categoryFile = "";
  $allCategories = $this->catPage->getFullCategoryTree();
  foreach($allCategories as $cat){
     $symbols = array(" ", "/");
     $fileName = str_replace($symbols, "_", $cat->getTitle());     
     $categoryFile .= "\categoryfile{" . $fileName ."}" . PHP_EOL;
  }
 
  return $categoryFile;
 }
 
 function generateShallowTexStructure($catPage){
   $shallowTex = "\\tree{" . $catPage->getTitle() . "}{" . escape($catPage->intent) . "}{\n" ;
   foreach($catPage->members as $m){
     $shallowTex .= "\\tab\concept{" . $m->getTitle() . "}{". escape($m->intent) . "}\n";
   }
   
   return $shallowTex;
 }
}

$args = CommandLine::parseArgs($_SERVER['argv']);

//$args['mode'] = 'matrix';
if($args['mode'] == 'ontology'){ //generate ontology
  echo "entering ontology generation mode, please wait...";
  $tex = "";
  $wiki = new Wiki();
  $base = new CategoryPage("Base");
  $generator = new OntyGenerator($base);
  $categoryFile = $generator->generateCategoryFile();

  $f = fopen($dataFolder . "files.tex", 'w+') or die("can't open file");
  fwrite($f, $categoryFile);
  fclose($f);
  echo PHP_EOL . "generating shallow ontology";
  $allCategories = $base->getFullCategoryTree();
  foreach($allCategories as $cat){
   $tex = $cat->getShallowTex();
   $fileName = $cat->getFileName() . ".tex";
   $f = fopen($outputShallowFolder . $fileName, 'w+') or die("can't open file");
   fwrite($f, $tex);
   fclose($fh);
  }
  
  echo PHP_EOL . "generating deep ontology";
  foreach($allCategories as $cat){
   $tex = $cat->getDeepTex();
  
   $fileName = $cat->getFileName() . ".tex";
   $f = fopen($outputDeepFolder . $fileName, 'w+') or die("can't open file");
   fwrite($f, $tex);
   fclose($fh);
  }
  
}
else if($args['mode'] == 'content'){ //generate tex wiki pages representation
  $wiki = new Wiki();
  $catImpl = new CategoryPage("101implementation");
  $impl = $catImpl->getImplementations();
  $allLangs = $wiki->getLanguagepages();
  $allTechnologies = $wiki->getTechnologyPages();
  // var_dump($impl);
  $fImpl = fopen($texFolder . "implementations.tex", "w+");
  $fMacro = fopen($texFolder . "macros_raw.tex", "w+");
  foreach($impl as $i){
    fwrite($fImpl, "\\iwiki{" . getTexCommandName($i->getTitle()) . "}" . PHP_EOL);
    // echo PHP_EOL . $i->getTitle() . PHP_EOL;
    //var_dump($i->toTexMacro());
    fwrite($fMacro, escape($i->toTexMacro()));
  }
  foreach($allLangs as $lang){
  //var_dump($lang->toTexMacro()); 
  fwrite($fMacro, escape($lang->toTexMacro()));
  }
  foreach($allTechnologies as $tech){
   fwrite($fMacro, escape($tech->toTexMacro()));
  }
  fclose($fImpl);
  fclose($fMacro);

  $fLang = fopen($texFolder . "languages.tex", "w+");
  foreach($allLangs as $lang){
    fwrite($fLang, "\\lwiki{" . getTexCommandName($lang->getTitle()) . "}". PHP_EOL);
  }
  fclose($fLang);

  $fTech = fopen($texFolder . "technologies.tex", "w+");
  foreach($allTechnologies as $t){
    fwrite($fTech, "\\twiki{" .getTexCommandName($t->getTitle()) . "}" . PHP_EOL);
  }
  fclose($fTech);

  formatTex();
}
else if($args['mode'] == 'matrix'){
 $wiki = new Wiki();

 //1. get all features
 $features = $wiki->getFeaturePages();

 //2. get all implementations
 $catImpl = new CategoryPage("101implementation");
 $impl = $catImpl->getImplementations();
 
 $featureNames = array();
 foreach($features as $f){
  array_push($featureNames, $f->getTitle());
 }
 $row = "";
 $implFeatures = array();
 foreach($featureNames as $fn){
  $implFeatures[$fn] = 0;
 }
 
 $featureFrequency = array();
 foreach($featureFrequency as $ff){
  $featureFrequency[$ff] = 0;  
 }
 foreach($impl as $i){
   foreach($featureNames as $fn){
   if(in_array($fn, $i->features)){
    $implFeatures[$fn] ++;
    $featureFrequency[$i->getTitle()] ++;
    //$row .= " & \\okValue";
   }
   else{
    //$row .= " & \\noValue";
   }
  }
 }
 
 //$implFeatures = array_flip($implFeatures);
 asort($implFeatures, SORT_NUMERIC);
 asort($featureFrequency, SORT_NUMERIC);
 $implFeatures = array_reverse($implFeatures);
 $featureFrequency = array_reverse($featureFrequency);
 
 $content = buildTableHeader(array_keys($implFeatures));
 
 foreach($featureFrequency as $ff=>$v){
  $impl = getBy($catImpl, $ff);
  $row .= "\\vLegend{". $impl->getTitle() ."}";
  foreach(array_keys($implFeatures) as $fn){
   if(in_array($fn, $impl->features)){
    $row .= " & \\okValue";
   }
   else{
    $row .= " & \\noValue";
   }
  }
  $row .= PHP_EOL . "\\newRow" . PHP_EOL;
 }
 
 $content .= $row . "\\end{tabular}";
 //3. calculate features frequency (e.g. using hashtable with [featureName][counter]
 
 $f = fopen($texFolderMatrix . "features.tex", "w+");
 fwrite($f, $content); 
 fclose($f);
}
else{
  die('the following params are supported: --mode=ontology|content|matrix' . PHP_EOL);
}

function getBy($catImpl, $val){
  $impl = $catImpl->getImplementations();
  foreach($impl as $i){
    if($i->getTitle() == $val) return $i;
  }
}

function buildTableHeader($features){
 $numCols = count($features);
  $th = "\\begin{tabular}{l";
 for($i=0; $i<$numCols; $i++){
  $th .= "|c";
 } 
 $th .= "}" . PHP_EOL;
 foreach($features as $f){
  $th .= "& \\hLegend{" . $f . "} ";
 }
 return $th . PHP_EOL . "\\newRow" . PHP_EOL;
}

echo PHP_EOL . "DONE" . PHP_EOL;






