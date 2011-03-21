all:

pushReadmes:
	cd ../tools; svn up
	cd ../implementations; svn up
	php pushReadmes.php
	cd ../implementations; svn commit -m ""

ignore:
	svn propedit svn:ignore .

