pushAllReadmes:
	make before
	php pushAllReadmes.php
	make after

pushDiff:
	make before
	php pushDiff.php
	make after

before:
	cd ../../tools; svn up
	cd ../../implementations; svn up

after:
	cd ../../implementations; svn commit -m ""

ignore:
	svn propedit svn:ignore .

