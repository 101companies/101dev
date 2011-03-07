<?php
  include 'wikibotClasses/wikibot.classes.php'; /* First bot. */
  include 'botclassesPhp/mediawiki.php';
  $base = '../implementations';

  // Create Implementation:-Page 
  function createPage($pName,$wpapi){
  	echo "Analyzing $pName...";
    $text = '__FORCETOC__';
    $inContributors = false;
    $inSummary = false;
    $contributors = array();
    $lines = file('../implementations/'.$pName.'/README');
    foreach ($lines as $line) {
      if (!(strpos($line,'==') === false))
        $inContributors = false;
      if ($inContributors && (substr($line,0,2) == '* ')){
        $contributorName = chop(str_replace('* ', '', $line));
        array_push($contributors, $contributorName);
        $line = str_replace($contributorName,'[[Contributor:'.$contributorName.'|]]', $line);
      }
      if ($inSummary) {
      	$line = '::\'\'\''.$line.'\'\'\'';
      	$inSummary = false;
      }
      if (!(strpos($line,'== Contributors ==') === false)){
        $inContributors = true;
      }  
      if (!(strpos($line,'== Summary ==') === false)){
        $inSummary = true;
      }
      $text .= $line;
    }  
    echo "Pushing README...";
    $result = true;
    $result = $wpapi->edit('Implementation:'.$pName, $text, 'A bot did this!', false, false, null, null, false );
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
        die();
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
	  	array_push($validPages, 'Implementation:'.$file);
      	foreach($contributors as $contributor){
        	if (!array_key_exists($contributor, $projectsPerContributor))
            	$projectsPerContributor[$contributor] = array();
        	array_push($projectsPerContributor[$contributor], $file);
 	  }
   }  
  }
  foreach($projectsPerContributor as $contributor => $projects)
    createContributorPage($contributor, $projects,$wpapi);
  
  //Cleaning
  $mw = new mediawiki('http://sl-mac.uni-koblenz.de/~wiki101/wiki/api.php');
  $foo = $mw->login($user,$pass);
  echo "Cleaning...";
  $deletedPages = array();
  foreach($wpapi->listprefix('Implementation') as $page) {
  	if(!in_array($page["title"],$validPages)){
  		$mw->delete($page["title"],"Cleaning.");
  		array_push($deletedPages, $page["title"]);
  	}
  }
  echo "Done.\nDeleted Pages = \n";
  var_dump($deletedPages);
  $mw->logout();
?>