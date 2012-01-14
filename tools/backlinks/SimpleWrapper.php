<?php

include "../wikibotClasses/wikibot.classes.php";

function getWpapi(){
   $bot = 'Bot';
   $wiki = '101companies';
   $wpapi = new wikipediaapi ('', '', '', $bot, $wiki, true);  
   return $wpapi;
}

function getAllPages(){
  $pages = getWpapi()->listprefix("");
  $pageNames = array();
  foreach($pages as $p)
  	array_push($pageNames, $p['title']);
  return $pageNames;
}

function getCategories(){
   $categories = getWpapi()->listcategories();
   $categoryNames = array();
   foreach($categories as $c)
      array_push($categoryNames, 'Category:'.$c['*']);
   return $categoryNames;
}

function get101companiesPages(){
	$pPages = getWpapi()->listprefix("", 4);
	$pPageNames = array();
	foreach($pPages as $p)
		array_push($pPageNames, $p['title']);
	return $pPageNames;
}

function getPageContent($title){
 $page = getWpapi()->revisions($title, 1, 'older', true);
 $content = $page[0]['*'];
 //var_dump($content);
 return $content;
}

function getDirtyBacklinks($title){
 return getWpapi()->backlinks($title);
}

function startsWith($suffix, $text) {
    return (strcmp(substr($text, 0, strlen($suffix)),$suffix)===0);
}

function categoryMembers($title){
	$membersP = getWpapi()->categorymembers($title);
   	$members = array();
   	foreach($membersP as $memberP)
   	 	array_push($members,$memberP['title']);	
	return $members;
}

