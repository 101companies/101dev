<?php
  include 'wikibotClasses/wikibot.classes.php'; /* First bot. */
  include 'botclassesPhp/mediawiki.php';
  $base = '../implementations';

  function startsWith($suffix, $text) {
    return (strcmp(substr($text, 0, strlen($suffix)),$suffix)===0);
  }
  
  function endsWith($suffix,$text) {
    return (strcmp(substr($text, strlen($text) - strlen($suffix)),$suffix)===0);
  }

  // Create Implementation:-Page 
  function createPage($pName,$wpapi){
  	echo "Analyzing $pName...\n";
    $text = '';
    $inContributors = false;
    $inSummary = false;
    $contributors = array();
    $lines = file('../implementations/'.$pName.'/README');
    foreach ($lines as $line) {
      if (!(strpos($line,'==') === false))
        $inContributors = false;
      if ($inContributors &&
      	    startsWith('* {{101contributor|',$line) && endsWith('}}',trim($line))) {
       		$contributorText = chop(str_replace('* {{101contributor|', '', $line));
       		$contributorText = str_replace('}}','',$contributorText);
       		if (!($pos = strpos($contributorText, "|")) === false) {
       			$role = substr($contributorText, $pos + 1, strlen($contributorText) - $pos - 1);
       			$contributorName = trim(substr($contributorText, 0, $pos));
       			//echo $contributorName.' -> '.$role;
       			if (!array_key_exists($contributorName, $contributors))
       				$contributors[$contributorName] = array();
       			array_push($contributors[$contributorName], $role);
       		}
      }
      if (!(strpos($line,'== Contributors ==') === false) || !(strpos($line,'== Contributor ==') === false)){
        $inContributors = true;
      }
      $bom = pack("CCC", 0xef, 0xbb, 0xbf);
      $line = str_replace($bom,"",$line);
      $text .= $line;
    } 
    echo "Pushing README...\n";
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
  function createContributorPage($contributor, $roles, $wpapi,$wpq){
  	  $content = $wpq->getpage('101contributor:'.$contributor);
  	  if ($content == null) {
  	   	$content = '__NOTOC__';
  	  }	
  	  echo "Creating contributor page for $contributor";
  	  foreach($roles as $role) {
  	  	$roleText = '[[Category:101'.$role.']]';
  	  	if (strpos($content, $roleText) === false) 
  	  		$content .= PHP_EOL.$roleText;
  	  }
      $result =true;
      $result = $wpapi->edit('101contributor:'.$contributor, $content, 'A bot did this!', false, false, null, null, false );
      if ($result){   
        echo "Success!\n";
      }
      else {
        echo "Failure!\n";
      }    
  }
	
  // Connecting to wiki via first bot
  $bot = 'Wikibot';
  $wiki = '101companies';
  $wpapi	= new wikipediaapi ( '', '', '', $bot, $wiki, true );
  $wpq	= new wikipediaquery ( '', '', '', $bot, $wiki, true );
  $wpi	= new wikipediaindex ( '', '', '', $bot, $wiki, true );
  $user = getWikibotSetting( 'user', $bot, $wiki );
  $pass = getWikibotSetting( 'pass', $bot, $wiki );
  if ( $wpapi->login( $user, $pass ) != 'true' ) {
   	echo "Login failure";
    die();
  }
  sleep( 1 );

  // Scan checkout
  $fileArray = scandir($base);
  // Create implementation and contributor pages
  $validPages = array();
  $contributors = array();
  foreach($fileArray as $file) {
   if ($file != '.' && $file != '..' && file_exists($base.'/'.$file.'/README')){ 
	  $projectContributors = createPage($file,$wpapi);
	  array_push($validPages, '101implementation:'.$file);
      foreach($projectContributors as $projectContributor => $roles){
       	if (!array_key_exists($projectContributor, $contributors))
           	$contributors[$projectContributor] = array();
       	foreach($roles as $role) {
       	if(!in_array($role, $contributors[$projectContributor]))
       		array_push($contributors[$projectContributor],$role);
       	}
 	  }
   }  
  }
  var_dump($contributors);
  foreach($contributors as $contributor => $roles)
    createContributorPage($contributor, $roles,$wpapi,$wpq);
  //Cleaning
  $mw = new mediawiki('http://sl-mac.uni-koblenz.de/~wiki101/wiki/api.php');
  $foo = $mw->login($user,$pass);
  echo "Cleaning...";
  $deletedPages = array();
  foreach($wpapi->listprefix('101implementation') as $page) {
  	if(!in_array($page["title"],$validPages)){
  		$mw->delete($page["title"],"Cleaning.");
  		array_push($deletedPages, $page["title"]);
  	}
  }
  echo "Done.\nDeleted Pages = \n";
  var_dump($deletedPages);
  $mw->logout();
?>