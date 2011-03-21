<?php
 
 function members($cat) {
 	$xml = simplexml_load_file('catTree.xml');
 	$queryResult = $xml->xpath('//cat[@name = \'Category:'.$cat.'\']');
 	$children = $queryResult[0]->children();
 	$members = array();
 	foreach($children as $child)
 	  foreach($child->attributes() as $k => $v)
 	   if ($k == 'name') {
 	    echo $v;
 	   	array_push($members,$v);
 	   }
 	return $members;
 }

?>