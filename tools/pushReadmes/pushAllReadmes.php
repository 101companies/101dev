<?php
  $base = '../../implementations';

  include 'pushReadme.php';
	
  // Connecting to wiki via first bot
  $bot = 'Wikibot';
  $wiki = '101companies';
  $wpapi	= new wikipediaapi ( '', '', '', $bot, $wiki, true );
  $wpq	= new wikipediaquery ( '', '', '', $bot, $wiki, true );
  $wpi	= new wikipediaindex ( '', '', '', $bot, $wiki, true );
  $user = getWikibotSetting( 'user', $bot, $wiki );
  $pass = getWikibotSetting( 'pass', $bot, $wiki );
  while ( $wpapi->login( $user, $pass ) != 'true' ) {
   	echo 'Login failure.Retrying...'.PHP_EOL;
   	sleep(1);
  }
  sleep(1);

  // Scan checkout
  $fileArray = scandir($base);
  // Create implementation and contributor pages
  $validPages = array();
  $contributors = array();
  foreach($fileArray as $file) {
   if ($file != '.' && $file != '..' && file_exists($base.'/'.$file.'/README')){
   	  echo 'Pushing README for '.$file.'...';	
	  $projectContributors = createPage($file,$wpapi);
	  array_push($validPages, '101implementation:'.$file);
      foreach($projectContributors as $projectContributor ){
       	if (!in_array($projectContributor, $contributors))
           	array_push($contributors, $projectContributor);
 	  }
   }  
  }
  var_dump($contributors);
  foreach($contributors as $contributor)
    createContributorPage($contributor, $wpapi,$wpq);
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