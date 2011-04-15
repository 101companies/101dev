ssh-user = softlang@linux.uni-koblenz.de
www-dir = /home/softlang/www/101companies

all:
	svn up
	cd ../API; svn up
	php TagCloudGenerator.php
	cd data; make

upload: all
	ssh ${ssh-user} 'mkdir -p ${www-dir}; mkdir -p ${www-dir}/views; chmod -R go+rX ${www-dir}'
	scp -r -p data/*.png ${ssh-user}:${www-dir}/views

