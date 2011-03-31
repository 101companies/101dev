<?php
 include "../wikibotClasses/wikibot.classes.php";
 
 function startsWith($suffix, $text) {
    return (strcmp(substr($text, 0, strlen($suffix)),$suffix)===0);
 }
 
 
 function addNode(&$parent, $cat, $wpapi) {
 	$catNode = $parent->addChild("cat");
 	$catNode->addAttribute("name", $cat);
 	$members = $wpapi->categorymembers($cat);
 	
 	foreach((array)$members as $member) {
 	 $title = $member['title'];
 	 if(startsWith("Category:",$title))
 	 	addNode($catNode, $title,$wpapi);
 	 else {
 	    $pageNode = $catNode->addChild("page");
 	    $pageNode->addAttribute("name", $title);
 	    
 	 }
 	}
 }
 
 $handle = fopen ("catTree.xml", "w");
 fwrite($handle, '<?xml version="1.0" encoding="utf8"?>');
 fwrite($handle, '<cat name="Category:Base"></cat>');
 fclose($handle);

 
 $xml = simplexml_load_file('catTree.xml');
 $wpapi	= new wikipediaapi ( '', '', '', "CatBot", "101companies", true );
 $baseMembers = $wpapi->categorymembers("Base");
 foreach((array)$baseMembers as $member){
    $title = $member['title'];
    echo $title;
 	addNode($xml, $title, $wpapi);
 }
 
$handle = fopen("catTree.xml", "wb"); 
fwrite($handle, $xml->asXML());
fclose($handle);

?>