<?php

if (!defined('MEDIAWIKI')) die('Not an entry point.');

$wgHooks['InternalParseBeforeLinks'][] = 'fnMyHook';

function fnMyHook( &$parser, &$text ) { 
 $pattern = "/(\[{3})([\w\W\s][^\][^\]]+)(\]{3})/"; //looking for [[[text]]]
 preg_match_all($pattern, $text, $out, PREG_PATTERN_ORDER); 

 $i = 0;
 foreach($out[0] as $k=>$v) {
    $replacement = "[[:Category:" . $out[2][$i] . "|" . $out[2][$i] . "]]";
    $i++;
    $text = str_replace($v, $replacement, $text);
 } 

 return true;
}



