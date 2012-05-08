<?php
require_once '../../../../configs/main.config.local.php' ;
require_once '../../../../../megalib/YEdGraphBrowser.php' ;

define('ROOTDIR','.') ;
define('TARGETDIR',ROOTDIR.'/docs') ;
define('YEDGRAPHFULLNAME',ROOTDIR.'/xsdClassesArtefactView.megal.graphml') ;
$sourceFileDirectory = ROOTDIR.'/repo' ;
$SourceDefinitions = array(
    array('Company.xsd','xml', array('Company'=>'4-11', 'Department'=>'13-20', 'Employee'=>'22-28')),
    array('Company.cs','csharp', array('Company'=>'25-51', 'Department'=>'59-110', 'Employee'=>'118-155')),
    array('ACMECorp.xml','xml'),
    array('ACMECorpCut.xml','xml'),
    array('CompanyXSD2CS.bat','batch'),
    array('Demo.cs','csharp', array('CutAcmeCorp'=>'38-43')),
    array('Operations.cs','csharp'),
    array('Serialization.cs','csharp')
);

$generator =
new YEdGraphBrowserGenerator(
    YEDGRAPHFULLNAME,
    $sourceFileDirectory,
    $SourceDefinitions,
    TARGETDIR) ;