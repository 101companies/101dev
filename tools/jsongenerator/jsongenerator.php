<?php

define('BASE_PATH',str_replace('jsongenerator','',dirname(__FILE__)));
error_reporting(E_ALL);
ini_set('display_errors','On');
require_once(BASE_PATH . 'API/ApiWrapper2.php');
require_once(BASE_PATH . 'API/ApiWrapper.php');
require_once("commandLine.php");

// helper
function escape($txt){ return $txt; }

// helper
function getTopSections($content) {
  $sections = array();
  $section = '';
  $sectioncontent = '';
  foreach(preg_split( '/\r\n|\r|\n/', $content) as $line){
    if (startsWith('== ',$line)) {
      if ($section != '') {
       $sec = array();
       $sec['tag'] = trim($section);
       $sec['content'] = $sectioncontent;
       array_push($sections,$sec);
       $sectioncontent = '';
      }
      $pattern =  '/={2}\s*([\.A-Za-z\s]+)\s*\={2}/';
      $replacement = '\1';
      $section = preg_replace($pattern, $replacement, $line);
    } else {
       $sectioncontent .= $line;
    }
    
  }
  if ($section != ''){
    $sec = array();
    $sec['tag'] = $section;
    $sec['content'] = $sectioncontent;
    array_push($sections,$sec);
  }
  return $sections;
} 

function memberOf($namespace,$content){
  if ($namespace == '101implementation' || $namespace == '101contributor')
    return array($namespace);
  else {
      preg_match_all('/\[\[Category:((\w|\d|\s|\/|\-|\.|\#)+)|(\w|\d|\s|\/|\-|\.|\#)+\]\]/', $content, $out1, PREG_PATTERN_ORDER);
      preg_match_all('/\[\[Category:((\w|\d|\s|\/|\-|\.|\#)+)\]\]/', $content, $out2, PREG_PATTERN_ORDER);
      $out = array_merge($out1[1],$out2[1]);
      $res = array();
      foreach($out as $o){
        if ($o != '' && !in_array($o,$res)){
          array_push($res,$o);
        }
      }                                         
      return $res;
  }

}

function toJson($title){
  $title = str_replace('_',' ',$title);
  $c = new Page($title);
  $arr = array(); 
  // title
  $arr['title']=str_replace("\"", "", $c->getTitle());
  // namespace
  $arr['namespace']=str_replace(':', '', str_replace($c->getTitle(),'',$c->getFullTitle()));
  // sections
  $arr['sections'] = getTopSections($c->content);
  
  // META
  $metaarr = array();
  
  // categories
  $metaarr['categories'] = memberOf($arr['namespace'],$c->content);
  
  // lastrev
  $lastrevarr = array();
  // version 
  $lastrevarr['version'] = $c->lastrev[0]['revid'];
  // author
  $lastrevarr['author'] = $c->lastrev[0]['user'];
  // time
  $lastrevarr['time'] = str_replace('Z','.0',str_replace('T',' ',$c->lastrev[0]['timestamp']));
  $metaarr['lastrev'] = $lastrevarr;
  
  // creation
  $creationarr = array();
  // author
  $creationarr['author'] = $c->creation[0]['user'];
  // time
  $creationarr['time'] = str_replace('Z','.0',str_replace('T',' ',$c->creation[0]['timestamp']));
  $metaarr['creation'] = $creationarr;
  
  
  $arr['meta'] = $metaarr;
  // writing data to file
  $file = fopen('data/'.str_replace(' ','_',str_replace(':','$',$title)).'.json','w+');
  fwrite($file, json_encode($arr));
  fclose($file);
}

// MAIN
// get CLI args
$args = CommandLine::parseArgs($_SERVER['argv']);

if (!isset($args['mode']) || ($args['mode']!="some" && $args['mode']!= "all"))
  echo 'ERROR: "mode" not set. Available modes: "some" and "all"';

if ($args['mode'] == 'some'){
  if (isset($args['titles'])) {
    $titles = explode(',',$args['titles']);
  } 
  else {
    echo 'Specify page titles using "--titles=pagetitle1,pagetitle2,..."';
  }
}

if ($args['mode'] == 'all'){
  $allPages = getAllPages();
  $titles = array();
  foreach($allPages as $page)
    array_push($titles,$page['title']);
}

$i = 1;
foreach($titles as $title) {
      echo $i++.'/'.count($titles).': Creating .json for '.$title.' ...';
      toJson($title);
      echo 'DONE.'.PHP_EOL;
}

echo 'ALL DONE.';