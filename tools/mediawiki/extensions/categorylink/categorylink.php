<?php
require_once("test.php");

if (!defined('MEDIAWIKI')) die('Not an entry point.');

$wgHooks['InternalParseBeforeLinks'][] = 'fnMyHook';

function fnMyHook( &$parser, &$text ) { 
   $pattern = "/(\[{3})([\w\W\s][^\]]+)(\]{3})/"; 
   preg_match_all($pattern, $text, $out, PREG_PATTERN_ORDER); 
   if(count($out) == 0) return true; 
   
   $categoryNames = getCategories();
   var_dump($categoryNames);
   $i = 0;
   foreach($out[0] as $k=>$v) {
     $category = $out[2][$i];

     $replacement = "[[:Category:" . $category . "|" . $category . "]]";
     
     if(substr_count($category,"|") == 1){ //looking for xxx|yyy
        $s = explode('|', $category);
 	$replacement = "[[:Category:" . $s[0] . "|" . $s[1] . "]]";  
        $category = $s[0];
     }
     
     if(in_array($category, $categoryNames) == true){ 
        $text = str_replace($v, $replacement, $text);
    }
    else{
        $replacement = "[[" . $category . "]]"; 
        $text = str_replace($v, $replacement, $text);
    }
     
    $i++;    
 } 

 return true;
}

   



