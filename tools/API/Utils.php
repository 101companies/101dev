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
 $t = str_replace("ä", "\\\"{a}", $txt);
 return $t;
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
  $txt = str_replace(' ', '',$txt);
  $res = handleUmlauts($txt);
  return $res;
}


function startsWith($suffix, $text) {
    return (strcmp(substr($text, 0, strlen($suffix)),$suffix)===0);
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

    public static function toTex($text) {
    
     $pattern =  '/\[\[(:)?((\w|\d|\s|\/|\-|\.|\#)+):((\w|\d|\s|\/|\-|\.|\#)+)\|((\w|\d|\s|\/|\-|\.|\#)+)\]\]/';
     $replacement = '\\wikiref{\2:\4}{\6}';
     $text = preg_replace($pattern, $replacement, $text);
    
     $pattern =  '/\[\[(:)?((\w|\d|\s|\/|\-|\.|\#)+):((\w|\d|\s|\/|\-|\.|\#)+)\]\]/';
     $replacement = '\\wikiref{\2:\4}{\4}';
     $text = preg_replace($pattern, $replacement, $text);
     
     $pattern =  '/\[\[((\w|\d|\s|\/|\-|\.|\#)+)\]\]/';
     $replacement = '\\wikiref{\1}{\1}';
     $text = preg_replace($pattern, $replacement, $text);
     
     $pattern =  '/\[\[((\w|\d|\s|\/|\-|\.|\#)+)\|((\w|\d|\s|\/|\-|\.|\#)+)\]\]/';
     $replacement = '\\wikiref{\1}{\3}';
     $text = preg_replace($pattern, $replacement, $text);
     
     $pattern  = '/\\\item\s*\*\s*\\\wikiref/';
     $replacement = '\\\item \\\wikiref';
     $text = preg_replace($pattern, $replacement, $text);
   
     //$text = handleUmlauts($text);	
     return $text;  
   }
    
}
   
  
