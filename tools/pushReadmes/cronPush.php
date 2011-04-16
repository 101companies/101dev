<?php

   function getRev(){
   		shell_exec("svn info ../../implementations/ --xml > svnInfo.xml");
   		$commit= simplexml_load_file('svnInfo.xml')->xpath('//commit');
   		$commitAttr = $commit[0]->attributes();
   		return intval($commitAttr['revision']);
   }
   
   $preUpRev = getRev();
   echo 'Pre up revision:'.$preUpRev.PHP_EOL;
   echo 'Updating...';
   shell_exec("svn up ../../implementations/");
   echo 'Done.'.PHP_EOL;
   $postUpRev = getRev();
   echo 'Post up revision:'.$postUpRev.PHP_EOL;
   if ($preUpRev != $postUpRev) {
   		echo 'New revision!'.PHP_EOL.'Pushing READMES...';
   		echo shell_exec('php pushReadmes.php');
   }
   else {
   	echo 'Checkout up-to-date!'.PHP_EOL;
   }
   
?>