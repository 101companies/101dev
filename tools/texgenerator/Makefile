all:
	make up
	make clean
	make content
	make ontology
	make matrix
	make dump-only

up:
	svn up
	cd ../API; svn up

content:
	php texgenerator.php --mode=content
	cd tex/content/latex; make

ontology:
	make up
	make ontology-only

ontology-only:
	php texgenerator.php --mode=ontology
	cd tex/ontology/latex; make

matrix:
	php texgenerator.php --mode=matrix --whitelist=whitelist.txt
	cd tex/matrix/latex; make

dump:
	make up
	make dump-only

dump-only:
	php texgenerator.php --mode=dump
	
clean:

