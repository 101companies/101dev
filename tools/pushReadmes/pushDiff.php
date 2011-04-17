<?php
  	include 'pushReadme.php';

   	function getRev($projectName){
   		shell_exec('svn info ../../implementations/'.$projectName.'/README --xml > svnInfo.xml');
   		$commit= simplexml_load_file('svnInfo.xml')->xpath('//commit');
   		$commitAttr = $commit[0]->attributes();
   		return intval($commitAttr['revision']);
   	}
   	
   	function getRevs(){
   		$revNums = array();
   		$base = '../../implementations';
   		$fileArray = scandir($base);
   		foreach($fileArray as $projectName)
   			if ($projectName != '.' && $projectName != '..' && file_exists($base.'/'.$projectName.'/README'))
   				$revNums[$projectName] = getRev($projectName);
   		return $revNums;
   		
   	}
   	
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
  	
  	$mw = new mediawiki('http://sl-mac.uni-koblenz.de/~wiki101/wiki/api.php');
  	$mw->login($user,$pass);
   
   
   
	echo 'Getting pre-up revision numbers...';
	$preUpRevs = getRevs();
	echo 'Done.'.PHP_EOL;
	echo 'Updating...';
	shell_exec("svn up ../../implementations/");
	echo 'Done.'.PHP_EOL;
	echo 'Getting post-up revision numbers...';
	$postUpRevs = getRevs();
	echo 'Done.'.PHP_EOL;
	echo 'Looking for new/changed READMEs...'.PHP_EOL;
	$contributors = array();
	$new = 0;
	$changed = 0;
	foreach($postUpRevs as $pName => $postRevNum){
		if (array_key_exists($pName, $preUpRevs) && $preUpRevs[$pName] == $postRevNum) {
				echo 'README for '.$pName.' did not change.'.PHP_EOL;
		} 
		else {
			if (array_key_exists($pName, $preUpRevs) && $preUpRevs[$pName] != $postRevNum){
				echo 'README for '.$pName.' did change! Pushing...';
				$changed++;
			}
			if (!array_key_exists($pName, $preUpRevs)) {
				echo 'README for '.$pName.' is new! Pushing...';
				$new ++;
			}
			$projectContributors = createPage($pName,$wpapi);
			foreach($projectContributors as $projectContributor )
       			if (!in_array($projectContributor, $contributors))
           			array_push($contributors, $projectContributor);
           	echo PHP_EOL;
		}
		
	}
	echo PHP_EOL;
	echo $new.' new READMEs pushed. '.$changed.' changed READMEs pushed.'.PHP_EOL.PHP_EOL;
	if ($new + $changed != 0){
    	echo 'Pushing new contributorpages...'.PHP_EOL;
    	foreach($contributors as $contributor)
    		createContributorPage($contributor, $wpapi,$wpq);
	}
	echo PHP_EOL;
	echo 'Looking for deleted READMEs...'.PHP_EOL;
	$deleted = 0;
	foreach($preUpRevs as $pName => $preRevNum) {
		if (!array_key_exists($pName, $postUpRevs)) {
			echo 'README for '.$pName.' was deleted! Deleting page...';
			$mw->delete($pName,"Cleaning.");
			$deleted++;	
		}
	}
	echo $deleted.' READMEs deleted.'.PHP_EOL.
    
	shell_exec('rm svnInfo.xml');
?>