<?php

require_once(BASE_PATH . "API/Utils.php");

function formatTex(){
 $fileName = BASE_PATH . "texgenerator/tex/content/data/macros_raw.tex";
 $file_array = file($fileName);
 $fOutput = fopen("tex/content/data/macros.tex", "w+");
 //var_dump($file_array);
 foreach ($file_array as $line)
 {
   if(startsWith("\\item \\wikiref", $line)){
     $line = formatter::toTex($line);
  }
  else{
    $line = formatter::toPlainText($line);
  }
  fwrite($fOutput, $line);
 }

 fclose($fOutput);
}
?>
