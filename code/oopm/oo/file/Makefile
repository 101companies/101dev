root = ../..
prefix = oo/file

all:

test:
	make test-bc
	make test-more
	make test-wget
	make test-ls
	make test-split

test-bc:
	cd ${root}; java ${prefix}/Bc1 ${prefix}/input-bc.txt
	cd ${root}; java ${prefix}/Bc2

test-more:
	cd ${root}; java ${prefix}/More LICENSE
	
test-wget:
	cd ${root}; java ${prefix}/Wget http://java.sun.com/docs/books/tutorial/networking/urls/readingURL.html
	
test-ls:
	cd ${root}; java ${prefix}/Ls
	
test-split:
	cd ${root}; java ${prefix}/Split ${prefix}/input-split.txt ${prefix}/output-spit txt
