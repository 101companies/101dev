ssh-user = softlang@linux.uni-koblenz.de
www-dir = /home/softlang/www/101companies

all:
	pdflatex featureMatrix
	pdflatex spacesMatrix
	make upload

upload:
	ssh ${ssh-user} 'mkdir -p ${www-dir}; mkdir -p ${www-dir}/views; chmod -R go+rX ${www-dir}'
	scp -r -p *.pdf ${ssh-user}:${www-dir}/views

clean:
	rm -f *.pdf
	rm -f *.log
	rm -f *.out
	rm -f *.aux


