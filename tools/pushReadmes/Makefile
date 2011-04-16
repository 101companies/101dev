all:

pushReadmes:
	cd ../../tools; svn up
	cd ../../implementations; svn up
	php pushAllReadmes.php
	cd ../../implementations; svn commit -m ""

ignore:
	svn propedit svn:ignore .

