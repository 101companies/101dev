all:
	make up
	make clean
	make content-only
	make onto-only
	make matrix-only
	make dump-only

up:
	svn up
	cd ../API; svn up

content:
	make up
	make content-only

content-only:
	php texgenerator.php --mode=content
	cd tex/content/latex; make

onto:
	make up
	make onto-only

onto-only:
	php texgenerator.php --mode=ontology
	cd tex/ontology/latex; make

matrix:
	make up
	make matrix-only

matrix-only:
	php texgenerator.php --mode=matrix --whitelist=../lists/featuredJavaImplementations.lst
	cd tex/matrix/latex; make

dump:
	make up
	make dump-only

dump-only:
	php texgenerator.php --mode=dump
	cd tex/dump/latex; make
	
clean:
	rm -f *.ext
