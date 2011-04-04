<?php
define('BASE_PATH',str_replace('API','',dirname(__FILE__)));
require_once(BASE_PATH . 'API/ApiWrapper.php' );
require_once('Utils.php');

function extractIntent($content) {
    $inIntent= false;
    $indent = "";
    foreach(explode(PHP_EOL, $content) as $line) {
      if (startsWith("==Intent==",str_replace(' ','',trim($line)) )) {
        $inIntent= true;
      }
      if (startsWith("==",trim($line))) {
         continue;
      }
      if($inIntent) {
          if ($line != '') {
          	$intent = $line;
          	break;
          }
      }
    }
    
    return $intent;
}

function extractContent($content, $pattern){
  $inside = false;
  $res = "";
  $content = explode(PHP_EOL, $content);
  foreach($content as $line) {
    //echo  PHP_EOL . $line . PHP_EOL;
    if($inside) {
     if (($line != '') && (startsWith("==", str_replace(' ','',$line)))){ //next section begins
      $inside = false;
      break;    
     }
     
     if ($line != '') { //we don't include empty lines
       $res .= PHP_EOL .$line;
     }     
    }
    if($inside == false){ //we're not inside the section yet
     if (startsWith($pattern, str_replace(' ','',$line) )) {
      $inside = true;
     }  
   }

  }
  return $res;
}

class Page{
 private $title;
 public $content;
 public $intent;
 public $namespace;
 
 function getTitle(){
  if(startsWith("Category:", $this->title)){
   return str_replace("Category:", "", $this->title);
  }
  else if(startsWith("101implementation:", $this->title)){
    return str_replace("101implementation:", "", $this->title);
  }
  else if(startsWith("Technology:", $this->title)){
    return str_replace("Technology:", "", $this->title);
  }
  else if(startsWith("Language:", $this->title)){
    return str_replace("Language:", "", $this->title);
  }
  else if(startsWith("101feature:", $this->title)){
   return str_replace("101feature:", "", $this->title);
  }

  return $this->title;
 }
 
 function getFileName(){
  $t = $this->getTitle();
  $symbols = array(" ", "/");
  return str_replace($symbols, "_", $t);
 }
 
 
 function toTex(){
  $tex = "wikisite\\" . "category" . "{" . $this->getTitle() . "}{" . escape($this->intent) . "}";
  
  return $tex;
 }
 
 function __construct($title){
  $this->title = $title;
  $this->namespace = ""; 	
  $this->content = getPageContent($title);
  $this->intent = extractIntent($this->content);
  $this->description = extractContent($this->descriptiopn, "==Description==");
 }
}

class CategoryPage extends Page{
 public $members;
  
 function __construct($title){
  parent::__construct($title);
  $this->namespace = "Category";
  $this->members = array();
  $subCats = getWpapi()->categorymembers($title);
  
  if($subCats == NULL) return;
   foreach($subCats as $sc){
    if(substr_count($sc['title'],"Category") == 1){ //found a Category
      $page = new CategoryPage($sc['title']);
      array_push($this->members, $page);
    }
    else if(substr_count($sc['title'],"101implementation") == 1){ //found an implementation page
      $page = new ImplementationPage($sc['title']);
      array_push($this->members, $page);
    }
   else{
    $page = new Page($sc['title']);
    array_push($this->members, $page);
   }
  } 
 }
 
 function getFullCategoryTree(){
  $memberTree = array();
  foreach($this->members as $m){
   if($m->namespace == "Category"){
    array_push($memberTree, $m);
    $childlen = $m->getFullCategoryTree();
    foreach($childlen as $c){
     array_push($memberTree, $c);
    }
   }   
  }
  
  return $memberTree;
 }
 
 function getImplementations(){
  $memberTree = array();
  foreach($this->members as $m){ //since we don't have nested implementations, top-level loop is OK
   if($m->namespace == "101implementation"){
    array_push($memberTree, $m);
   }   
  }
  
  return $memberTree;
 }
 
 
 private function getObjType($cat){
  if($cat->namespace == "Category"){
   return "concept";
  }
  
  return "instance";
 }
 
 function getShallowTex(){
   $tex = "\\tree{" . $this->getTitle() . "}{" . escape($this->intent) . "}{\n" ;
   foreach($this->members as $m){
     $tex .= "\\tab\\" . $this->getObjType($m) . "{" . $m->getTitle() . "}{". escape($m->intent) . "}\n";
   }
   $tex .= "}";
   
   return $tex;
 }
 
 private function getIntetByLevel($level){
  $default = "\\tab";
  for($i = 0; $i <= $level - 1; $i++){
    $default .= "\\tab";
  }
  return $default;
 }
 
 private function writeWithIdent($cat, $level){
   $tex .= $this->getIntetByLevel($level) . "\\" . $this->getObjType($cat) . "{" . $cat->getTitle() . "}{". escape($cat->intent) . "}\n";
   foreach($cat->members as $c){
    $tex .= $this->writeWithIdent($c, $level + 1); 
   }
   
   return $tex;
 }
 
 function getDeepTex(){
  $tex = "\\tree{" . $this->getTitle() . "}{" . escape($this->intent) . "}{\n" ;
  $level = 0;
  foreach($this->members as $m){
   $tex .= $this->writeWithIdent($m, $level);
  }
  $tex .= "}";  
  return $tex;
 }
}


class ImplementationPage extends Page{
 public $languages;
 public $technologies;
 public $features;
 public $motivation;
 public $architecture;
 public $usage;
 public $contributors;
  
 function toTexMacro(){
   $tex = "\\newcommand{\\". getTexCommandName($this->getTitle()) . "ImplementationTitle}{". $this->getTitle() ."}" . PHP_EOL; 
   $tex .= "\\newcommand{\\" . getTexCommandName($this->getTitle()) . "ImplementationIntent}{" . $this->intent . "}" . PHP_EOL;
   $tex .= "\\newcommand{\\" . getTexCommandName($this->getTitle()) . "ImplementationMotivation}{" . $this->motivation . "}" . PHP_EOL;
   $tex .= "\\newcommand{\\" . getTexCommandName($this->getTitle()) . "ImplementationLanguages}{" . getItemizedTex($this->languages) . "}" . PHP_EOL;
   $tex .= "\\newcommand{\\" . getTexCommandName($this->getTitle()) . "ImplementationTechnologies}{" . getItemizedTex($this->technologies) . "}" . PHP_EOL;
   return $tex;
 } 

 function __construct($title){
  parent::__construct($title);
  $this->namespace = "101implementation";
  
  $this->languages = extractContent($this->content, "==Languages==");
  $this->technologies = extractContent($this->content, "==Technologies==");
  $featuresContent = extractContent($this->content, "==Features==");
   
  $this->features = array();
  $pattern = "/(101feature:)([\w\W\s][^\]]+)(\]{2})/"; 
  foreach(explode(PHP_EOL, $featuresContent) as $line){
    if($line == '') continue;
    
    preg_match_all($pattern, $line, $out, PREG_PATTERN_ORDER);
    if(count($out) != 0){
    foreach($out as $f){
     //var_dump($f);
     $feature = new Feature($f);
     array_push($features, $feature);
    }	
   } 
  }
    
  $this->motivation = extractContent($this->content, "==Motivation==");
  $this->architecture = extractContent($this->content, "==Architecture==");
  $this->usage = extractContent($this->content, "==Usage==");
  $this->contributors = extractContent($this->content, "==Contributors==");
 }
}

class LanguagePage extends Page{
 public $description;
 function __construct($content){
 parent::__construct($content);
 $this->description = extractContent($this->content, "==Description==");
 $this->namespace = "Language";
 }
 
  function toTexMacro(){
   $tex = "\\newcommand{\\". getTexCommandName($this->getTitle()) . "LanguageTitle}{". $this->getTitle() ."}" . PHP_EOL;
   $tex .= "\\newcommand{\\" . getTexCommandName($this->getTitle()) . "LanguageIntent}{" . $this->intent . "}" . PHP_EOL;
   $tex .= "\\newcommand{\\" . getTexCommandName($this->getTitle()) . "LanguageDescription}{" . $this->description . "}" . PHP_EOL;
   return $tex;
  }

}

class TechnologyPage extends Page{
 public $description;
 function __construct($content){
  parent::__construct($content);
  $this->description = extractContent($this->content, "==Description==");
  $this->namespace = "Technology";
 }
 
 function toTexMacro(){
   $tex = "\\newcommand{\\". getTexCommandName($this->getTitle()) . "TechnologyTitle}{". $this->getTitle() ."}" . PHP_EOL;
   $tex .= "\\newcommand{\\" . getTexCommandName($this->getTitle()) . "TechnologyIntent}{" . $this->intent . "}" . PHP_EOL;
   $tex .= "\\newcommand{\\" . getTexCommandName($this->getTitle()) . "TechnologyDescription}{" . $this->description . "}" . PHP_EOL;
   return $tex;
  }
}

class FeaturePage extends Page{
 public $discussion;
 function __construct($content){
  parent::__construct($content);
  $this->discussion = extractContent($this->content, "==Discussion==");
  $this->namespace = "101feature";
 }
}
class Feature{
 public $name;
 function __construct($content){
  $this->name = $content;
 }
}




