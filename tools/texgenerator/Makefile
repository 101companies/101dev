all:
        svn up
        make clean
        make extract
        make latex

extract:
	php texgenerator.php --mode=content
	php texgenerator.php --mode=ontology
	php texgenerator.php --mode=matrix

latex:
        cd tex/content/latex; make
        cd tex/matrix/latex; make
        cd tex/ontology/latex; make

clean:

