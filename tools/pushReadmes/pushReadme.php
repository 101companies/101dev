<?php
  include '../wikibotClasses/wikibot.classes.php'; /* First bot. */
  include '../botclassesPhp/mediawiki.php';

  function startsWith($suffix, $text) {
    return (strcmp(substr($text, 0, strlen($suffix)),$suffix)===0);
  }
  
  function endsWith($suffix,$text) {
    return (strcmp(substr($text, strlen($text) - strlen($suffix)),$suffix)===0);
  }

  // Create Implementation:-Page 
  function createPage($pName,$wpapi){
    $text = '';
    $inContributors = false;
    $inSummary = false;
    $contributors = array();
    $lines = file('../../implementations/'.$pName.'/README');
    foreach ($lines as $line) {
      if (!(strpos($line,'==') === false))
        $inContributors = false;
      if ($inContributors &&
      	    startsWith('* {{101contributor|',$line) && endsWith('}}',trim($line))) {
       		$contributorText = chop(str_replace('* {{101contributor|', '', $line));
       		$contributorText = str_replace('}}','',$contributorText);
       		if (!($pos = strpos($contributorText, "|")) === false) {
       			//$role = substr($contributorText, $pos + 1, strlen($contributorText) - $pos - 1);
       			$contributorName = trim(substr($contributorText, 0, $pos));
       			//echo $contributorName.' -> '.$role;
       			if (!in_array($contributorName, $contributors))
       				array_push($contributors, $contributorName);
       		}
      }
      if (!(strpos($line,'== Contributors ==') === false) || !(strpos($line,'== Contributor ==') === false)){
        $inContributors = true;
      }
      $bom = pack("CCC", 0xef, 0xbb, 0xbf);
      $line = str_replace($bom,"",$line);
      $text .= $line;
    } 
    $result = true;
    $result = $wpapi->edit('101implementation:'.$pName, $text, 'A bot did this!', false, false, null, null, false );
    if ($result){   
      echo "Success!\n";
      return $contributors;
    }
    else {
      echo "Failure!\n";       
      die();
    } 
  }

  // Create Contributor:-Page
  function createContributorPage($contributor, $wpapi,$wpq){
  	  $content = $wpq->getpage('101contributor:'.$contributor);
  	  if ($content != null) {
  	   	echo 'Contributorpage for '.$contributor.' already exists'.PHP_EOL;
  	   	return true;
  	  }
  	  $content = '__NOTOC__';
  	  echo 'Creating contributor page for '.$contributor.PHP_EOL;
      $result =true;
      $result = $wpapi->edit('101contributor:'.$contributor, $content, 'A bot did this!', false, false, null, null, false );
      if ($result){   
        echo "Success!\n";
      }
      else {
        echo "Failure!\n";
      }    
  }
	
?>