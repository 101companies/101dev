<?php

include 'SimpleWrapper.php';


function getBacklinks($title){
	$h = fopen('contents.json','r');
	$backlinkss = unserialize(fread($h,filesize('contents.json')));
	$backlinks = $backlinkss[$title];
	if ($backlinks == NULL)
		return array();
	return $backlinks;
}

function allBacklinks(){
	echo 'Getting ALL backlinks...'.PHP_EOL;
	$pageMerge = array_merge(array_merge(getAllPages(), getCategories()),get101companiesPages());
	$backlinkss = array();
	foreach($pageMerge as $p){
		echo 'Getting backlinks from '.$p.' ...'.PHP_EOL;
		$backlinkss =  updateBacklinksFromPage($p, $backlinkss);
	}
	$h = fopen('contents.json', 'w+');
	fwrite($h, serialize($backlinkss));
	fclose($h);
}

function updateBacklinksFromPage2($title){
	$h = fopen('contents.json','r');
	$backlinkss = unserialize(fread($h,filesize('contents.json')));
	$backlinkss2 = updateBacklinksFromPage($title, $backlinkss);
	$h2 = fopen('contents.json', 'w+');
	fwrite($h2, serialize($backlinkss2));
	fclose($h2);
	
}

function updateBacklinksFromPage($title,$backlinkss){
	$content = getPageContent($title);
	$links = array();
	$content = preg_replace('/(\[\[(Category:)((\w|\d|\s|\/|\-|\.|\#)+)\]\])/', '', $content);
	preg_match_all('/\[\[(:)?(((\w|\d|\s|\/|\-|\.|\#|\_)+)(:((\w|\d|\s|\/|\-|\.|\#|\_)+))?)\|((\w|\d|\s|\/|\-|\.|\#|\'|\_)+)\]\]/', $content, $out, PREG_SET_ORDER);
	foreach($out as $o){
		$otitle = str_replace('_',' ',$o[2]);
		array_push($links, $otitle);
	}
	preg_match_all('/\[\[(:)?(((\w|\d|\s|\/|\-|\.|\#|\_)+)(:((\w|\d|\s|\/|\-|\.|\#|\_)+))?)\]\]/', $content, $out, PREG_SET_ORDER);
	foreach($out as $o){
		$otitle = str_replace('_',' ',$o[2]);
		array_push($links, $otitle);
	}
	$links = array_unique($links);
	//var_dump($links);
	foreach($links as $link){
		if (startsWith("Category:", $link) && in_array($title, categoryMembers($link)))
			continue;
		if ($backlinkss[$link] != NULL){
			///echo $link.' does exist...';
			//var_dump($backlinkss[$link]);
			if(in_array($title,$backlinkss[$link]))
				;//echo 'link is already known'.PHP_EOL;
			else {
				echo 'link is NOT known'.PHP_EOL;
				//echo 'registrating...';
				array_push($backlinkss[$link], $title);
				//var_dump($backlinkss[$link]);
				//echo 'Done.'.PHP_EOL;
			}
		}
		else { 
			//echo $link.' does not exist. Registrating page and backlink'.PHP_EOL;
			$backlinks = array($title);
			$backlinkss[$link] = $backlinks;
		}
	}
	$count=0;
	foreach($backlinkss as $t => $backlinks){
		if(in_array($title, $backlinks) && !in_array($t,$links)) {
			echo 'Link is known to '.$t.' is known but outdated'.PHP_EOL;
			//echo 'Removing...';
			$backlinkss[$t] = array_diff($backlinks, array($title));
			//var_dump(array_diff($backlinks['backlinks'], array($title)));
			//echo 'Done.'.PHP_EOL;
		}
	}
	return $backlinkss;
}
//allBacklinks();
var_dump(getBacklinks('Language:Java'));
//var_dump(updateBacklinksFromPage2("101implementation:happstack"));
