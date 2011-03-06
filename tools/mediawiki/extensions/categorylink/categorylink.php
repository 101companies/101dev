<?php

if (!defined('MEDIAWIKI')) die('Not an entry point.');

$wgHooks['InternalParseBeforeLinks'][] = 'fnMyHook';

function fnMyHook( &$parser, &$text ) { 
 $pattern = "/(\[{3})([\w\W\s][^\]]+)(\]{3})/"; 
 preg_match_all($pattern, $text, $out, PREG_PATTERN_ORDER); 
 
 $i = 0;
 foreach($out[0] as $k=>$v) {
    $c = substr_count($out[2][$i],"|"); 
    var_dump($c);
	
    $replacement = "[[:Category:" . $out[2][$i] . "|" . $out[2][$i] . "]]";
		
    if(substr_count($out[2][$i],"|") == 1){ //looking for xxx|yyy
	list($s1, $s2) = split('|', $out[2][$i]);
	$replacement = "[[:Category:" . $out[2][$i] . "]]";
    }   
    
    $i++;
    $text = str_replace($v, $replacement, $text);
 } 

 return true;
}



