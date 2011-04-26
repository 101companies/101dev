<?php

/*  Function:   convert_number
**  Arguments:  int
**  Returns:    string
**  Description:
**      Converts a given integer (in range [0..1T-1], inclusive) into
**      alphabetical format ("one", "two", etc.).
*/
function convert_number($number)
{
    if (($number < 0) || ($number > 999999999))
    {
        return "$number";
    }

    $Gn = floor($number / 1000000);  /* Millions (giga) */
    $number -= $Gn * 1000000;
    $kn = floor($number / 1000);     /* Thousands (kilo) */
    $number -= $kn * 1000;
    $Hn = floor($number / 100);      /* Hundreds (hecto) */
    $number -= $Hn * 100;
    $Dn = floor($number / 10);       /* Tens (deca) */
    $n = $number % 10;               /* Ones */

    $res = "";

    if ($Gn)
    {
        $res .= convert_number($Gn) . "Million";
    }

    if ($kn)
    {
        $res .= (empty($res) ? "" : " ") .
            convert_number($kn) . "Thousand";
    }

    if ($Hn)
    {
        $res .= (empty($res) ? "" : " ") .
            convert_number($Hn) . " Hundred";
    }

    $ones = array("", "One", "Two", "Three", "Four", "Five", "Six",
        "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen",
        "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eightteen",
        "Nineteen");
    $tens = array("", "", "Twenty", "Thirty", "Fourty", "Fifty", "Sixty",
        "Seventy", "Eigthy", "Ninety");

    if ($Dn || $n)
    {
        if (!empty($res))
        {
            $res .= "and";
        }

        if ($Dn < 2)
        {
            $res .= $ones[$Dn * 10 + $n];
        }
        else
        {
            $res .= $tens[$Dn];

            if ($n)
            {
                $res .= $ones[$n];
            }
        }
    }

    if (empty($res))
    {
        $res = "zero";
    }

    return $res;
}

function getItemizedTex($markup){
    return $markup;
    
  if($markup == '') return $markup;
  
  $tex = PHP_EOL . "\\begin{itemize}";
  foreach(explode(PHP_EOL, $markup) as $line) {
    if($line != '') {
      //$t = formatter::toTex($line);
      //$t1 = formatter::toPlainText($t); //hadnle remaining wiki markup
     // if($t == $line){
     //    $tex .= PHP_EOL . "\\item \\wikiref{}{}". $t1;
    //  }
    //  else{
           $tex .= PHP_EOL . "\\item ". $line;
    //  }   
    }
  }
  $tex .=  PHP_EOL . "\\end{itemize}" . PHP_EOL;
  return $tex;
}

function handleUmlauts($txt){
 $txt = str_replace("ä", "\\\"{a}", $txt);
 $t = str_replace("ü", "\\\"{u}", $txt);
 return $t;
}

function handleWikiRef($txt){
    $res = str_replace(" ", "_", $txt);
    return $res;
}

function getTexCommandName($txt){
 //var_dump($txt);
 for($i=1000; $i>=0; $i--){
  $str = (string)$i;
  if(strlen(strstr($txt,$str))>0){
    $word = convert_number($i);
    $txt = str_replace($str, $word, $txt);
    //break;
   }
  }

  $txt = str_replace(".", "dot", $txt);
  $txt = str_replace("-", "", $txt);
  $txt = str_replace("/", "", $txt);
  $txt = str_replace("ä", "ae", $txt);
  $txt = str_replace("ü", "ou", $txt);
  $txt = str_replace(":", "", $txt);
  $res = str_replace(' ', '',$txt);
  return $res;
}


function startsWith($suffix, $text) {
    return (strcmp(substr($text, 0, strlen($suffix)),$suffix)===0);
}

function endsWith($haystack,$needle,$case=true) {
      if($case){return (strcmp(substr($haystack, strlen($haystack) - strlen($needle)),$needle)===0);}
      return (strcasecmp(substr($haystack, strlen($haystack) - strlen($needle)),$needle)===0);
}

class formatter{
    public static function toPlainText($text) { 
    
     $pattern =  '/\[\[(:)?((\w|\d|\s|\/|\-|\.|\#)+):((\w|\d|\s|\/|\-|\.|\#)+)\]\]/';
     $replacement = '\2:\4';
     $text = preg_replace($pattern, $replacement, $text);
   
     
     $pattern =  '/\[\[(:)?(((\w|\d|\s|\/|\-|\.|\#)+):)?((\w|\d|\s|\/|\-|\.|\#)+)\|((\w|\d|\s|\/|\-|\.|\#)+)\]\]/';
     $replacement = '\7';
     $text = preg_replace($pattern, $replacement, $text);
     
     $pattern =  '/\[\[((\w|\d|\s|\/|\-|\.|\#)+)\]\]/';
     $replacement = '\1';
     $text =  preg_replace($pattern, $replacement, $text);
     
     $pattern =  $pattern =  '/\[(\S+)\s+\S+\]/';
     $replacement = '\1';
     $text =  preg_replace($pattern, $replacement, $text);
     
    return $text;
   }
   
   public static function handleLinks($text){
    if($text == '') return '';
     if($text == null) return '';
     
     $pattern =  '/\[\[(:)?((\w|\d|\s|\/|\-|\.|\#)+):((\w|\d|\s|\/|\-|\.|\#)+)\|((\w|\d|\s|\/|\-|\.|\#)+)\]\]/';
     $replacement = '\\wikiref{\2:\4}{\6}';
     $text = preg_replace($pattern, $replacement, $text);
    
     //  --- recognize specific links ---
     $pattern =  '/(Technology:)((\w|\d|\s|\/|\-|\.|\#)+)/';
     $replacement = '\\wikitref{\2}';
     $text = preg_replace($pattern, $replacement, $text);
     
     $pattern =  '/(Category:)((\w|\d|\s|\/|\-|\.|\#)+)/';
     $replacement = '\\wikiref{Category:\2}{\2}';
     $text = preg_replace($pattern, $replacement, $text);
     
     $pattern =  '/(Language:)((\w|\d|\s|\/|\-|\.|\#)+)/';
     $replacement = '\\wikilref{\2}';
     $text = preg_replace($pattern, $replacement, $text);
     
     $pattern =  '/(101feature:)((\w|\d|\s|\/|\-|\.|\#)+)/';
     $replacement = '\\wikifref{\2}';
     $text = preg_replace($pattern, $replacement, $text);
     
     $pattern =  '/(101implementation:)((\w|\d|\s|\/|\-|\.|\#)+)/';
     $replacement = '\\wikiiref{\2}';
     $text = preg_replace($pattern, $replacement, $text);
     
     $pattern =  '/(101contributor:)((\w|\d|\s|\/|\-|\.|\#)+)/';
     $replacement = '\\wikicontribref{\2}';
     $text = preg_replace($pattern, $replacement, $text);
     
     $pattern =  '/(101companies:)((\w|\d|\s|\/|\-|\.|\#)+)/';
     $replacement = '\\wikicref{\2}';
     $text = preg_replace($pattern, $replacement, $text);
          
     //----------------------------------------------------------
     
     $pattern =  '/\[\[(:)?((\w|\d|\s|\/|\-|\.|\#)+):((\w|\d|\s|\/|\-|\.|\#)+)\]\]/';
     $replacement = '\\wikiref{\2:\4}{\4}';
     $text = preg_replace($pattern, $replacement, $text);
     
     $pattern =  '/\[\[((\w|\d|\s|\/|\-|\.|\#)+)\]\]/';
     $replacement = '\\wikiref{\1}{\1}';
     $text = preg_replace($pattern, $replacement, $text);
     
     $pattern =  '/\[\[((\w|\d|\s|\/|\-|\.|\#)+)\|((\w|\d|\s|\/|\-|\.|\#)+)\]\]/';
     $replacement = '\\wikiref{\1}{\3}';
     $text = preg_replace($pattern, $replacement, $text);
     
     $text = escape($text);
     $res = handleUmlauts($text);
     
    // var_dump($res);
     return $res;  
   }

    public static function toTex($text) {
    
     if($text == '') return '';
     if($text == null) return '';
     //var_dump($text);
     
     $pattern = '/(\[\[(Category:)((\w|\d|\s|\/|\-|\.|\#)+)\]\])/';
     preg_match($pattern, $text, $out);
     if(count($out) > 0){
        $replacement = '';
        $text = preg_replace($pattern, $replacement, $text);
     }
     
     $pattern =  '/\[\[(:)?((\w|\d|\s|\/|\-|\.|\#)+):((\w|\d|\s|\/|\-|\.|\#)+)\|((\w|\d|\s|\/|\-|\.|\#)+)\]\]/';
     $replacement = '\\wikiref{\2:\4}{\6}';
     $text = preg_replace($pattern, $replacement, $text);
    
     //  --- recognize specific links ---
     $pattern =  '/\\[\[(Technology:)((\w|\d|\s|\/|\-|\.|\#)+)\]\]/';
     $replacement = '\\wikitref{\2}';
     $text = preg_replace($pattern, $replacement, $text);
     
     $pattern =  '/\\[\[(Language:)((\w|\d|\s|\/|\-|\.|\#)+)\]\]/';
     $replacement = '\\wikilref{\2}';
     $text = preg_replace($pattern, $replacement, $text);
     
     $pattern =  '/\\[\[(101feature:)((\w|\d|\s|\/|\-|\.|\#)+)\]\]/';
     $replacement = '\\wikifref{\2}';
     $text = preg_replace($pattern, $replacement, $text);
     
     $pattern =  '/\\[\[(101implementation:)((\w|\d|\s|\/|\-|\.|\#)+)\]\]/';
     $replacement = '\\wikiiref{\2}';
     $text = preg_replace($pattern, $replacement, $text);
     
     $pattern =  '/\\[\[(101companies:)((\w|\d|\s|\/|\-|\.|\#)+)\]\]/';
     $replacement = '\\wikicref{\2}';
     $text = preg_replace($pattern, $replacement, $text);
          
     //----------------------------------------------------------
     
     $pattern =  '/\[\[(:)?((\w|\d|\s|\/|\-|\.|\#)+):((\w|\d|\s|\/|\-|\.|\#)+)\]\]/';
     $replacement = '\\wikiref{\2:\4}{\4}';
     $text = preg_replace($pattern, $replacement, $text);
     
     $pattern =  '/\[\[((\w|\d|\s|\/|\-|\.|\#)+)\]\]/';
     $replacement = '\\wikiref{\1}{\1}';
     $text = preg_replace($pattern, $replacement, $text);
     
     $pattern =  '/\[\[((\w|\d|\s|\/|\-|\.|\#)+)\|((\w|\d|\s|\/|\-|\.|\#)+)\]\]/';
     $replacement = '\\wikiref{\1}{\3}';
     $text = preg_replace($pattern, $replacement, $text);
     
     $pattern = '/<pre>((\s*|.|\s)*)<\/pre>/';
     preg_match($pattern, $text, $out);
     if(count($out) > 0){
        $fname = uniqid("f") . ".ext";
        global $filesFolder;
        $f = fopen($filesFolder . $fname, "w+");
        fwrite($f, trim($out[1]));
        fclose($f);
        
        $pattern =  '/<pre>((\s*|.|\s)*)<\/pre>/';
        $replacement = '\lstinputlisting{\texgen/files/' . $fname . "}";
        $text = preg_replace($pattern, $replacement, $text);
     }
     
     
     $pattern = '/<syntaxhighlight lang=\"([a-zA-Z]*)\">((\s*|.|\s)*)<\/syntaxhighlight>/';
     preg_match_all($pattern, $text, $matches, PREG_SET_ORDER);
     foreach($matches as $match){
        var_dump($matches);
        echo PHP_EOL;
        $fname = uniqid("f") . ".ext";
        global $filesFolder;
        $f = fopen($filesFolder . $fname, "w+");
        fwrite($f, trim($match[2]));
        fclose($f);
        
        $pattern = '<syntaxhighlight lang="' . $match[1] .'">' . $match[2] .'</syntaxhighlight>';
        $replacement = '\lstinputlisting[language=' . $match[1] . ']{\texgen/files/' . $fname . "}";
        $text = str_replace($pattern, $replacement, $text);
     }    
  
     $text = str_replace("<references>", "", $text);
     $text = str_replace("<references/>", "", $text);
     
     $text = formatter::nestedList($text);
     $text = formatter::italic2Textit($text);
     
     $text = escape($text);
     $res = handleUmlauts($text);
     
    // var_dump($res);
     return $res;  
   }
    function italic2Textit($text){
     $pattern =  '/\'\'((\w*|\W*|\d*|\s*|\-*)*)\'\'/'; //'/\'\'(.*)\'\'/';
     $replacement = '\\textit{\1}';
     return preg_replace($pattern, $replacement, $text);
    }
    
    function nestedList($text) {
      //$hasCB = false;
      //if (endsWith($text,"}")) {
      //  $text = substr($text, 0, strlen($test) - 1);
      //  $hasCB = true;
      //} 
      $lines = explode(PHP_EOL,$text);
      $newText = '';
      $nestLevel = 0;
      $currentNestLevel = 0;
      $newLines = array();
      foreach($lines as $line) {
        $currentNestLevel = strlen(preg_replace('/(\**)\s*(.*)/','\1',$line));
        $itemText = preg_replace('/(\**)(.*)/','\2',$line);
        
        // nesting gets depper
        if ($currentNestLevel > $nestLevel) {
            for ($i = 0; $i < $currentNestLevel - $nestLevel; $i++)
              array_push($newLines,'\\begin{itemize'.str_repeat('i',$nestLevel + $i + 1).'}');
            array_push($newLines,'\\item'.$itemText);       
        }
        
        // same or less nesting
        if ($currentNestLevel <= $nestLevel) {
            for ($i = 0; $i < $nestLevel - $currentNestLevel; $i++)
              array_push($newLines,'\\end{itemize'.str_repeat('i',$nestLevel - $i).'}');
            if ($currentNestLevel != 0)
              array_push($newLines,'\\item'.$itemText);
            else
              array_push($newLines, $itemText);
        }
        
        $nestLevel = $currentNestLevel;
      }
      
      for ($i = 0; $i < $nestLevel; $i++)
         array_push($newLines,'\\end{itemize'.str_repeat('i',$nestLevel - $i).'}');
        
      $outText = implode(PHP_EOL,$newLines);
      
        return $outText;
        
   }     
 
}
   
  

 