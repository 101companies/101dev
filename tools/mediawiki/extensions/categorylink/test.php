<?php
//require_once("/var/www/wiki/wikibot/wikibot.classes.php");
require_once($_SERVER['DOCUMENT_ROOT'] . "/wikibot/wikibot.classes.php");

function getWpapi(){
   $bot = 'Bot';
   $wiki = 'Wiki';
   $wpapi = new wikipediaapi ('', '', '', $bot, $wiki, true);
   
   return $wpapi;
}

function getSubCategories($categoryTitle){
     $categoryNames = array();
     $subCats = getWpapi()->categorymembers("category:" . $categoryTitle);
      foreach($subCats as $sc){
          if(substr_count($sc['title'],"Category") == 1){ //found a Category
            $t = str_replace("Category:", "", $sc['title']); // take Category:Test as input and produce only Test
            array_push($categoryNames, $t);
            $sc = getSubCategories($t);
            if(count($sc) > 0){
             foreach($sc as $s){   //make flat array, as soon we do not track dependencies
               array_push($categoryNames, $s);
             }
            }
          }           
      } 
      
      return $categoryNames;
}

function getCategories(){
   $categories = getWpapi()->listcategories();
   $categoryNames = array();

   foreach($categories as $c){
      array_push($categoryNames, $c['*']);
      $subCategories = getSubCategories($c['*']);
      foreach($subCategories as $sc){
       array_push($categoryNames, $sc);
      }
   }
   
   return $categoryNames;
}

//$categoryNames = getCategories(); 
//var_dump($categoryNames); 


/*foreach($subCategories as $sc){
  echo $sc . PHP_EOL;
}*/

