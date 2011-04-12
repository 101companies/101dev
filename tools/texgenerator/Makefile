all:
	svn up
	cd ../API; svn up
	make clean
	make content
	make ontology
	make matrix
	make dump

content:
	php texgenerator.php --mode=content
	cd tex/content/latex; make

ontology:
	php texgenerator.php --mode=ontology
	cd tex/ontology/latex; make

matrix:
	php texgenerator.php --mode=matrix --whitelist=whitelist.txt
	cd tex/matrix/latex; make
dump:
	php texgenerator.php --mode=dump
	
clean:

