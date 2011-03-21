<?php
define('BASE_PATH',str_replace('tagcloudgenerator','',dirname(__FILE__)));

require_once(BASE_PATH . "do-not-commit/wikibot.config.php");
require_once(BASE_PATH . "wikibotClasses/wikibot.classes.php");
require_once(BASE_PATH . "botclassesPhp/mediawiki.php");

// Pleaase change this to the right location on your machine
$cloudy_data_home = BASE_PATH . "cloudy/data/";
$cloudy_home = BASE_PATH . "cloudy";

