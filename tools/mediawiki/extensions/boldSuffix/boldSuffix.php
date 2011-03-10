<?php

$wgHooks['OutputPageParserOutput'][''] = 'boldSuffix';

function boldSuffix( &$out, $parseroutput ) {
   $title = $parseroutput->getTitleText();
   if (!($pos = strpos($title,":")) === false) {
      $suffix = substr($title, $pos + 1);
      $prefix = substr($title, 0, strlen($title) - strlen($suffix));
      $parseroutput->setTitleText($prefix.'<b>'.$suffix.'</b>');
   }
   return true;
}

?>