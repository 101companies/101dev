<?php
require_once($_SERVER['DOCUMENT_ROOT']."/wikibot/wikibot.classes.php");

$wgHooks['ParserBeforeStrip'][''] = 'addIndex';

function startsWith($suffix, $text) {
    return (strcmp(substr($text, 0, strlen($suffix)),$suffix)===0);
}

function addIndex( &$parser, &$text,  &$strip_state) {

   // Checking for correct run  
   static $run = 0;
   $run++; 
   if ($run != 2) 
      return true;

   $title = $parser->getTitle();

   if (startsWith("Category:",$title)) {
   		$catName = substr($title, strpos($title, ":")+1);
   		$text .= PHP_EOL.'== Category Tree ==';
   		$text .= PHP_EOL.'<categorytree mode=pages>'.$catName.'</categorytree>';
   		return true;
   }		
   
   $isContrPage = startsWith("Contributor:",$title);
   $bot = 'Wikibot';
   $wiki = '101companies';
   $wpapi = new wikipediaapi ( '', '', '', $bot, $wiki, true );
   $backlinksP = $wpapi->backlinks($title);
   $backlinks = array();
   foreach($backlinksP as $backP)
   	  array_push($backlinks, $backP["title"]);
   if ($isContrPage){
      $contName = substr($title, strpos($title, ":")+1);
      $text .= PHP_EOL.'== List of contributions by '.$contName.' ==';
      $implTitles = array();
      foreach((array)$backlinks as $backlink) {
        $backLTitle = $backlink;
        if (startsWith("Implementation:",$backLTitle)){
          array_push($implTitles, $backLTitle);
        }
      }
      sort($implTitles);
      foreach($implTitles as $k=>$v) {
       $implName = substr($v, strpos($v, ":") + 1);
       $text .= PHP_EOL.'* [['.$v.'|'.$implName.']]';
      }
      
   } 
   else {
      
   }
   
   return true;
}

?>
