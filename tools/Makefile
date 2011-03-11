all:

pushReadmes:
	cd ../implementations; svn up
	php pushReadmes.php
	cd ../implementations; svn commit -m ""

ignore:
	svn propedit svn:ignore .

