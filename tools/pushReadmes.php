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
      	   startsWith('* [[Contributor:',$line) &&
      	   (endsWith(']]'.PHP_EOL,trim($line)) || endsWith(']]',trim($line)))) {
       		$contributorName = chop(str_replace('* [[Contributor:', '', $line));
       		$contributorName = str_replace(']]','',$contributorName);
        	array_push($contributors,trim($contributorName));
      }
      if (!(strpos($line,'== Contributors ==') === false)){
        $inContributors = true;
      }
      $bom = pack("CCC", 0xef, 0xbb, 0xbf);
      $line = str_replace($bom,"",$line);
      $text .= $line;
    } 
    $text .= PHP_EOL.'[[Category:101implementation]]';
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
  function createContributorPage($contributor, $pNames, $wpapi){
  	  echo "Creating contributor page for $contributor";
      $text = '';
      //foreach ($pNames as $pName){
      //    $text .= '* [[Implementation:'.$pName.']]'.PHP_EOL;
      //}
      $result = $wpapi->edit('Contributor:'.$contributor, $text, 'A bot did this!', false, false, null, null, false );
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
  $projectsPerContributor = array();
  foreach($fileArray as $file) {
   if ($file != '.' && $file != '..' && file_exists($base.'/'.$file.'/README')){ 
	  $contributors = createPage($file,$wpapi);
	  	array_push($validPages, '101implementation:'.$file);
      	foreach($contributors as $contributor){
        	if (!array_key_exists($contributor, $projectsPerContributor))
            	$projectsPerContributor[$contributor] = array();
        	array_push($projectsPerContributor[$contributor], $file);
 	  }
   }  
  }
  var_dump($projectsPerContributor);
  foreach($projectsPerContributor as $contributor => $projects)
    createContributorPage($contributor, $projects,$wpapi);
  
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