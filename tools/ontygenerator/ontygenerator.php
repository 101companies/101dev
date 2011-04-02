<?php

define('BASE_PATH',str_replace('ontygenerator','',dirname(__FILE__)));
$outputShallowFolder = BASE_PATH . "onty/data/shallow/";
$outputDeepFolder = BASE_PATH . "onty/data/deep/";
$dataFolder = BASE_PATH . "onty/data/";
$inputXml = BASE_PATH . "intentsExtractor/intents.xml";
//$categories = array("Language", "Programming technique", "Technology", "Term");
/**
     * Converts a simpleXML element into an array. Preserves attributes and everything.
     * You can choose to get your elements either flattened, or stored in a custom index that
     * you define.
     * For example, for a given element
     * $array['field']['name'] = 'someName';
     * $array['field']['type'] = 'someType';
     * If you choose not to flatten, you get:
     * $array['field']['@attributes']['name'] = 'someName';
    * _____________________________________
     * @return array the resulting array.
     */
    function simpleXMLToArray($xml,
                    $flattenValues=true,
                    $flattenAttributes = true,
                    $flattenChildren=true,
                    $valueKey='@value',
                    $attributesKey='@attributes',
                    $childrenKey='@children'){

        $return = array();
        if(!($xml instanceof SimpleXMLElement)){return $return;}
        $name = $xml->getName();
        $_value = trim((string)$xml);
        if(strlen($_value)==0){$_value = null;};

        if($_value!==null){
            if(!$flattenValues){$return[$valueKey] = $_value;}
            else{$return = $_value;}
        }

        $children = array();
        $first = true;
        foreach($xml->children() as $elementName => $child){
            $value = simpleXMLToArray($child, $flattenValues, $flattenAttributes, $flattenChildren, $valueKey, $attributesKey, $childrenKey);
            if(isset($children[$elementName])){
                if($first){
                    $temp = $children[$elementName];
                    unset($children[$elementName]);
                    $children[$elementName][] = $temp;
                    $first=false;
                }
                $children[$elementName][] = $value;
            }
            else{
                $children[$elementName] = $value;
            }
        }
        if(count($children)>0){
            if(!$flattenChildren){$return[$childrenKey] = $children;}
            else{$return = array_merge($return,$children);}
        }

        $attributes = array();
        foreach($xml->attributes() as $name=>$value){
            $attributes[$name] = trim($value);
        }
        if(count($attributes)>0){
            if(!$flattenAttributes){$return[$attributesKey] = $attributes;}
            else{$return = array_merge($return, $attributes);}
        }
       
        return $return;
    }
    
function members($cat) {
   	$xml = simplexml_load_file($GLOBALS["inputXml"]);
 	$queryResult = $xml->xpath('//cat[@name = \'Category:'.$cat.'\']');
 	$children = $queryResult[0]->children();
 	$members = array();
 	foreach($children as $child)
 	  foreach($child->attributes() as $k => $v)
 	   if ($k == 'name') {
 	    //echo $v;
 	   	array_push($members,$v);
 	   }
 	return $members;
 }
 
function getChildrenRecursive($elem){
 $members = array();
 if($elem == NULL) return $members();
 $children = $elem->children();

  foreach($children as $child){
  foreach($child->attributes() as $k => $v){
    if ($k == 'name') {
     //echo $v;
     array_push($members,$v);
     $subChilds = getChildrenRecursive($child);
     foreach($subChilds as $sc){
      array_push($members,$sc['name']);
     }     
   }
  }
 }
 
 return $members;
}


function getWithSubMembers($cat){
  $allMembers = array();
  $xml = simplexml_load_file($GLOBALS["inputXml"]);
  $queryResult = $xml->xpath('//cat[@name = \'Category:'.$cat.'\']');
  if(count($queryResult) == 0){
    $queryResult = $xml->xpath('//page[@name = \''.$cat.'\']');
  }
  $children = $queryResult[0]->children();
  foreach($children as $ch){
    array_push($allMembers, $ch['name']);
    $members = getChildrenRecursive ($ch);
    array_push($allMembers, $members);
  }    
  return $allMembers; 
}


function writeWithIdent($fh, $obj, $level){
   if(gettype($obj) == "array"){
     ++$level;
    foreach($obj as $o){
        writeWithIdent($fh, $o, $level);
    }
  }
  else{
    echo getIntetByLevel($level) . getObjectWithTexPrefix($obj);
    fwrite($fh, getIntetByLevel($level) . getObjectWithTexPrefix($obj));
  }
}
function string_begins_with($string, $search)
{
    return (strncmp($string, $search, strlen($search)) == 0);
}

function getObjectWithTexPrefix($obj){
  // category --> concept
  $intend = getIndentTextByName($obj);
  $escaped = str_replace("#", "\\#", $intend);  
  if(string_begins_with($obj,'Category:')){
    $l = split(":", $obj);
    $obj = $l[1];
    return "\\concept{" . $obj . "}{" . links($escaped) . "}" . PHP_EOL;
  }
  return "\\instance{" . $obj . "}{" . links($escaped) . "}" . PHP_EOL;
}

function getIntetByLevel($level){
  $default = "\\tab";
  for($i = 0; $i <= $level - 1; $i++){
    $default .= "\\tab";
  }
  return $default . "\\tab";
}



function getIndentTextByName($name){
  $xml = simplexml_load_file($GLOBALS["inputXml"]);
  // 1.check for category
  $queryResult = $xml->xpath('//cat[@name = \''. $name .'\']');
  if(count($queryResult)==0)
  {
    $name;
    if(string_begins_with($name, "Category:")){
      $l = split(":", $name);
      $name = $l[1];
    }
    
    $queryResult = $xml->xpath('//cat[@name = \''. $name .'\']');
    if(count($queryResult) == 0){
      $queryResult = $xml->xpath('//page[@name = \'' . $name . '\']');
      //var_dump($queryResult);
      $arr = simpleXMLToArray($queryResult[0]);
      return $arr['intent'];
    }
    else{
        $arr = simpleXMLToArray($queryResult[0]);
        return $arr['indent'];
    }
   
  }
   $arr = simpleXMLToArray($queryResult[0]);
   return $arr['indent'];
}


function getAllCategories(){
    $xml = simplexml_load_file($GLOBALS["inputXml"]);
    $queryResult = $xml->xpath('//cat');
    $children = $queryResult[0]->children();
 	$members = array();
 	foreach($children as $child)
 	  foreach($child->attributes() as $k => $v)
 	   if ($k == 'name') {
 	    //echo $v;
 	   	array_push($members,$v);
 	   }
 	return $members;
}

function withoutCategoryPrefix($obj){
    if(string_begins_with($obj, "Category:")){
    $r = split(":", $obj);
    return $r[1];
    }
    
    return $obj;
}

   function links($text) {
    $pattern = '/\[\[((Technology|Language|:Category)):((\w|\d|\s|\/|\-|\.)+)\]\]/';
    $replacement = '\3';   
    $text = preg_replace($pattern, $replacement, $text);
   
    $pattern =  '/\[\[((\w|\d|\s|\/|\-|\.)+):((\w|\d|\s|\/|\-|\.)+)\]\]/';
    $replacement = '\1:\3';
    $text = preg_replace($pattern, $replacement, $text);
   
    $pattern =  '/\[\[(:)?(((\w|\d|\s|\/|\-|\.)+):)?((\w|\d|\s|\/|\-|\.)+)\|((\w|\d|\s|\/|\-|\.)+)\]\]/';
    $replacement = '\7';
    $text = preg_replace($pattern, $replacement, $text);
   
    $pattern =  '/\[\[((\w|\d|\s|\/|\-|\.)+)\]\]/';
    $replacement = '\1';
    $text =  preg_replace($pattern, $replacement, $text);
   
    $pattern =  '/\[((\w|\d|\s|\/|\-|\.)+)\]/';
    $replacement = '\1';
    return preg_replace($pattern, $replacement, $text);
  }
$categories = getAllCategories();
//var_dump($categories);

$allCategories = array();
$f = fopen($dataFolder . "files.tex", 'w+'); //or die("can't open file");
foreach($categories as $category){
    $cat = simpleXMLToArray($category);
   // echo $category;
    $fileName = str_replace(" ", "", withoutCategoryPrefix($cat));     
    fwrite($f, "\categoryfile{" . $fileName ."}" . PHP_EOL);
    array_push($allCategories,  withoutCategoryPrefix($cat));
    
    $topLevelMembers = members(withoutCategoryPrefix($cat));
    foreach($topLevelMembers as $m){
        $mem = simpleXMLToArray($m);
        if(string_begins_with($mem, "Category:")){
         $fileName = str_replace(" ", "", withoutCategoryPrefix($mem));     
         fwrite($f, "\categoryfile{" . $fileName ."}" . PHP_EOL);
         array_push($allCategories, withoutCategoryPrefix($mem));
        }
    }
}

fclose($f);

var_dump($allCategories);


//DEEP
foreach($allCategories as $category){
  $fileName = str_replace(" ", "", $category);  
  $myFile =  $outputDeepFolder . $fileName . ".tex";
  $fh = fopen($myFile, 'w+'); //or die("can't open file");
  $indent = getIndentTextByName("Category:". $category);
  fwrite($fh, "\\tree{" . $category . "}{" .links($indent) . "}{" . PHP_EOL);
  $topLevelMembers = members($category);

 foreach($topLevelMembers as $member){
  $strMem = simpleXMLToArray($member);
  $indent = getIndentTextByName($strMem);
  echo $strMem;
  //$l = split(":", $strMem);
  //if(count($l) == 2) $strMem = $l[1];
  //else $strMem = $l[0];
  $escaped = str_replace("#", "\\#", $indent);
  fwrite($fh, "\\tab\\concept{" . $strMem . "}{" . links($escaped)  . "}". PHP_EOL);
  
  
  $allMembers = getWithSubMembers(withoutCategoryPrefix($strMem));
  foreach($allMembers as $m){
    writeWithIdent($fh, $m, 0);
 }
 echo PHP_EOL;
 }
 fwrite($fh, "}");
 fclose($fh);
}


//SHALLOW
foreach($allCategories as $category){
  $shallow= array(); 
  $members = members($category);
  
  foreach($members as $m){
    $l = str_replace("Category:", "", $m);
    array_push($shallow, $l);
  }
  
  $fileName = str_replace(" ", "", $category);  
  $myFile =  $outputShallowFolder . $fileName . ".tex";
  $fh = fopen($myFile, 'w+'); //or die("can't open file");
  $indent = getIndentTextByName("Category:". $category);
  $escaped = str_replace("#", "\\#", $indent);
  fwrite($fh, "\\tree{" . $category . "}{" . links($escaped) . "}{\n" );
  foreach($shallow as $s){
    $indent = getIndentTextByName("Category:". $s);
    $escaped = str_replace("#", "\\#", $indent);
    fwrite($fh, "\\tab\concept{" . $s . "}{". links($escaped)  . "}\n");
  }
  $shallow= array();
  fwrite($fh, "}");
  fclose($fh);
}

echo "DONE";



