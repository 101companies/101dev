<?php
require_once($_SERVER['DOCUMENT_ROOT'] . "/wiki/wikibot/wikibot.classes.php");

if (!defined('MEDIAWIKI')) die('Not an entry point.');

$wgHooks['InternalParseBeforeLinks'][] = 'fnMyHook';

function fnMyHook( &$parser, &$text ) { 
   $pattern = "/(\[{3})([\w\W\s][^\]]+)(\]{3})/"; 
   preg_match_all($pattern, $text, $out, PREG_PATTERN_ORDER); 
 
   $bot = 'Bot';
   $wiki = 'Wiki';
   $wpapi = new wikipediaapi ('', '', '', $bot, $wiki, true );
   $categories = $wpapi->listcategories(); //listprefix('Category'); 
   //var_dump($categories);
   
   $categoryNames = array();
   foreach($categories as $c){
       array_push($categoryNames, $c['*']);
       $subCats = $wpapi->categorymembers("category:" . $c['*']);
       foreach($subCats as $sc){
          $t = str_replace("Category:", "", $sc['title']); // take Category:Test as input and produce only Test
          array_push($categoryNames, $t);
      }       
   }
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



