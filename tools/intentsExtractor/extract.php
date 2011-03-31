<?php
 include "../wikibotClasses/wikibot.classes.php";
 
 function startsWith($suffix, $text) {
    return (strcmp(substr($text, 0, strlen($suffix)),$suffix)===0);
 }
 
 function extractIntent($wpq,$name) {
    $content = $wpq->getpage($name);
    $inIntent= false;
 	$indent = "";
    foreach(explode(PHP_EOL, $content) as $line) {
      if($inIntent) {
          if (startsWith("==",trim($line))) {
            break;
          }
          if ($line != '') {
          	$intent = $line;
          	break;
          }
      }
      if (startsWith("==Intent==",str_replace(' ','',trim($line)) )) {
        $inIntent= true;
      }  
    }
    return $intent;
 }
 
 function addNode(&$parent, $cat, $wpapi,$wpq) {
 	$catNode = $parent->addChild("intent");
 	$catNode->addAttribute("pagename", $cat);
 	$members = $wpapi->categorymembers($cat);
 	$content = $wpq->getpage($cat);
 	$intent = extractIntent($wpq,$cat);
    $catNode->addAttribute("text", $intent);
 	echo $intent;
 	
 	foreach((array)$members as $member) {
 	 $title = $member['title'];
 	 if(startsWith("Category:",$title))
 	 	addNode($parent, $title,$wpapi,$wpq);
 	 else {
 	    $pageNode = $parent->addChild("intent");
 	    $pageNode->addAttribute("pagename", $title);
 	    $pIntent = extractIntent($wpq,$title);
 	    $pageNode->addAttribute("text", $pIntent);
 	    
 	 }
 	}
 }
 
 $handle = fopen ("intents.xml", "w");
 fwrite($handle, '<?xml version="1.0" encoding="utf8"?>');
 fwrite($handle, '<intents></intents>');
 fclose($handle);

 
 $xml = simplexml_load_file('intents.xml');
 $wpapi	= new wikipediaapi ( '', '', '', "IntentBot", "101companies", true );
 
 $wpq = new wikipediaquery($wapi,$wquery,$windex,'CatBot','101companies',true);
 $baseMembers = $wpapi->categorymembers("Base");
 foreach((array)$baseMembers as $member){
    $title = $member['title'];
    echo $title;
 	addNode($xml, $title, $wpapi,$wpq);
 }
 
$handle = fopen("intents.xml", "wb"); 
fwrite($handle, $xml->asXML());
fclose($handle);

?>