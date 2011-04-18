<?php

define('BASE_PATH',str_replace('texgenerator','',dirname(__FILE__)));
$outputShallowFolder = BASE_PATH . "texgenerator/tex/ontology/data/shallow/";
$outputDeepFolder = BASE_PATH . "texgenerator/tex/ontology/data/deep/";
$dataFolder = BASE_PATH . "texgenerator/tex/ontology/data/";

$texFolder = BASE_PATH . "texgenerator/tex/content/data/";
$texFolderMatrix = BASE_PATH . "texgenerator/tex/matrix/data/";
$texFolderDump = BASE_PATH . "texgenerator/tex/dump/data/";

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
  $escaped = str_replace("#", "\#", $txt);
  $escaped = str_replace('$', "\$", $escaped);
  //$escaped = str_replace('<', "\<", $escaped);
  //$escaped = str_replace('<', "\<", $escaped);
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

//$args['mode'] = 'dump';
//$args['whitelist'] = "whitelist.txt";
if($args['mode'] == 'dump'){
   echo "Entering dump mode, please wait..." . PHP_EOL;
   $wiki = new Wiki();
   $f = fopen($texFolderDump . "macros.tex", "w+");
   $allPages = $wiki->getAllPages();
   $catPages = $wiki->getCategoryPages();
   echo "Dumping wiki content..." . PHP_EOL;
   $pages = array();
   $pages = array_merge($catPages, $allPages);
   
   $content = "";
   foreach($pages as $page){
    $content .= $page->dumpToTex();
   }
   fwrite($f, $content);
   fclose($f);
}
else if($args['mode'] == 'ontology'){ //generate ontology
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
   $fileName = $cat->getFileName() . ".tex";
   $f = fopen($outputShallowFolder . $fileName, 'w+') or die("can't open file");
   $tex = $cat->getShallowTex();
   foreach (explode(PHP_EOL, $tex) as $line)
   {
    $line = formatter::toTex($line);
    fwrite($f, $line . PHP_EOL);
   }
    //fwrite($f, $tex);
   fclose($f);
  }
  
  echo PHP_EOL . "generating deep ontology";
  foreach($allCategories as $cat){
   $tex = $cat->getDeepTex();
   $fileName = $cat->getFileName() . ".tex";
   $f = fopen($outputDeepFolder . $fileName, 'w+') or die("can't open file");
   foreach (explode(PHP_EOL, $tex) as $line)
   {
     $line = formatter::toTex($line);
     fwrite($f, $line . PHP_EOL);
    }
   fclose($fh);
  }
  
}
else if($args['mode'] == 'content'){ //generate tex wiki pages representation
  $wiki = new Wiki();
  $catImpl = new CategoryPage("101implementation");
  $impl = $catImpl->getImplementations();
  $allLangs = $wiki->getLanguagepages();
  $allTechnologies = $wiki->getTechnologyPages();
  $catFeature = new CategoryPage("101feature");
  
  // var_dump($impl);
  $fImpl = fopen($texFolder . "implementations.tex", "w+");
  $fMacro = fopen($texFolder . "macros.tex", "w+");
  foreach($impl as $i){
    fwrite($fImpl, "\\iwiki{" . getTexCommandName($i->getTitle()) . "}" . PHP_EOL);
    // echo PHP_EOL . $i->getTitle() . PHP_EOL;
    //var_dump($i->toTexMacro());
    fwrite($fMacro, $i->toTexMacro());
  }
  foreach($allLangs as $lang){
  //var_dump($lang->toTexMacro()); 
  fwrite($fMacro, $lang->toTexMacro());
  }
  foreach($allTechnologies as $tech){
   fwrite($fMacro, $tech->toTexMacro());
  }
  foreach($catFeature->members as $cf){
    fwrite($fMacro, "\\newcommand{\\" .getTexCommandName($cf->getTitle()) . "FeatureCategory}{" . $cf->getTitle() ."}". PHP_EOL);;
    foreach($cf->members as $f){
      fwrite($fMacro, $f->toTexMacro());
    }
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
  
  $fFeatures = fopen($texFolder . "features.tex", "w+");
  foreach($catFeature->members as $cf){
    fwrite($fFeatures, "\\fcwiki{" .getTexCommandName($cf->getTitle()) . "}" . PHP_EOL);
    foreach($cf->members as $f){
      fwrite($fFeatures, "\\fwiki{" .getTexCommandName($f->getTitle()) . "}" . PHP_EOL);
    }    
  }
  fclose($fFeatures);

  //formatTex();
}
else if($args['mode'] == 'matrix'){
  $whiteList = array();
  if($args['whitelist'] != ''){
   $whiteList = file($args['whitelist'], FILE_IGNORE_NEW_LINES|FILE_SKIP_EMPTY_LINES);
  } 
 $wiki = new Wiki();
 $features = $wiki->getFeaturePages();
 $catImpl = new CategoryPage("101implementation");
 $impl = $catImpl->getImplementations();
 $technologies = $wiki->getTechnologyPages();
 
 buildTechnicalSpacesMatrix($technologies);
 buildImplSpacesMatrix($impl, $whiteList);
 
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
   }
  }
 }
 
 asort($implFeatures, SORT_NUMERIC);
 asort($featureFrequency, SORT_NUMERIC);
 $implFeatures = array_reverse($implFeatures);
 $featureFrequency = array_reverse($featureFrequency);
 
 $content = buildTableHeader(array_keys($implFeatures));
 
 foreach($featureFrequency as $ff=>$v){
  $impl = getBy($catImpl, $ff);
  //apply whitelist
  if(count($whiteList) > 0){
    if(in_array($impl->getTitle(), $whiteList) == false){
      continue;
    }
  }
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

function getTechologyBy($technologies, $val){
  foreach($technologies as $t){
    if($t->getTitle() == $val) return $t;
  }
}

function buildTableHeader($features, $delimPosition){
 $numCols = count($features);
  $th = "\\begin{tabular}{l";
 for($i=0; $i<$numCols; $i++){
  if($i != $delimPosition){
    $th .= "|c";
  }
  else{
    $th .= "||c";
  }  
 } 
 $th .= "}" . PHP_EOL;
 foreach($features as $f){
  $th .= "& \\hLegend{" . $f . "} ";
 }
 return $th . PHP_EOL . "\\newRow" . PHP_EOL;
}


function buildTechnicalSpacesMatrix($technologies){
   $catSpace = new CategoryPage("Technical space");
   $spaces = $catSpace->members;
   
   $catSegments = new CategoryPage("Technical segment");
   $segments = $catSegments->members;
   
   $spaceNames = array();
   foreach($spaces as $s){
    array_push($spaceNames, $s->getTitle());
   }
   foreach($segments as $s){
    array_push($spaceNames, $s->getTitle());
  }  
   
   $implSpaces = array();
   foreach($spaceNames as $s){
    $implSpaces[$s] = 0;
   }
 
   $spaceFrequency = array();
   foreach($spaceFrequency as $sf){
    $spaceFrequency[$sf] = 0;  
   } 
 
 foreach($technologies as $t){
  foreach($spaceNames as $sn){
   if(in_array($sn, $t->spaces)){
    $implSpaces[$sn] ++;
    $spaceFrequency[$t->getTitle()] ++;
   }
  }
 }
 
 asort($implSpaces, SORT_NUMERIC);
 asort($spaceFrequency, SORT_NUMERIC);
 $implSpaces = array_reverse($implSpaces);
 $spaceFrequency = array_reverse($spaceFrequency);
 
 $content = buildTableHeader($spaceNames, count($spaces));//array_keys($implSpaces));
 
 foreach($spaceFrequency as $sf=>$v){
  $t = getTechologyBy($technologies, $sf);
  
  $atleastOnce = false;
  foreach($spaceNames as $sn){
  //foreach(array_keys($implSpaces) as $sn){
   if(count($t->spaces) == 0) break; 
   if(in_array($sn, $t->spaces)){
    $atleastOnce = true;
   }
  }
  
  $technicalSegments = array();
  foreach($segments as $s){
    array_push($technicalSegments, $s->getTitle());
  }
  
  if($atleastOnce == true)
  {
    $row .= "\\vLegend{". $t->getTitle() ."}";
    foreach($spaceNames as $sn){
    //foreach(array_keys($implSpaces) as $sn){
    if(in_array($sn, $t->spaces)){
        $row .= " & \\okValue";
    }
    else{
     $row .= " & \\noValue";
    }
   } 
   $row .= PHP_EOL . "\\newRow" . PHP_EOL;    
  }
 }
 
 $content .= $row . "\\end{tabular}";
 
 global $texFolderMatrix;
 $f = fopen($texFolderMatrix . "technologyspaces.tex", "w+");
 fwrite($f, $content); 
 fclose($f);  
}

function buildImplSpacesMatrix($impl, $whiteList){
  $catSpace = new CategoryPage("Technical space");
  $spaces = $catSpace->members;
  $arrSpaces = array();
  foreach($spaces as $s){
    array_push($arrSpaces, $s->getTitle());
  }
  $content = buildTableHeader($arrSpaces);
  
  foreach($impl as $i){
   //apply whitelist
   if(count($whiteList) > 0){
     if(in_array($i->getTitle(), $whiteList) == false){
      continue;
     }
   }
    $row .= "\\vLegend{". $i->getTitle() ."}";
    foreach($arrSpaces as $s){
      $sp = $i->spaces;
     if(in_array($s, $sp)){
      $row .= " & \\okValue";
     }
     else{
      $row .= " & \\noValue";
     }
    }
    $row .= PHP_EOL . "\\newRow" . PHP_EOL;
  }
  
  $content .= $row . "\\end{tabular}";
  global $texFolderMatrix;
  $f = fopen($texFolderMatrix. "implementationspaces.tex", "w+");
  fwrite($f, $content);
  fclose($f);
}

echo PHP_EOL . "DONE" . PHP_EOL;






