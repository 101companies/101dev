pushAllReadmes:
	cd ../../tools; svn up
	cd ../../implementations; svn up
	php pushAllReadmes.php
	cd ../../implementations; svn commit -m ""

pushDiff:
	php pushDiff.php

ignore:
	svn propedit svn:ignore .

