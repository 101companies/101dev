ssh-user = softlang@linux.uni-koblenz.de
www-dir = /home/softlang/www/101companies

all:
	pdflatex implementationFeatureMatrix
	pdflatex implementationSpaceMatrix

upload: all
	ssh ${ssh-user} 'mkdir -p ${www-dir}; mkdir -p ${www-dir}/views; chmod -R go+rX ${www-dir}'
	scp -r -p *.pdf ${ssh-user}:${www-dir}/views

ignore:
	svn propedit svn:ignore .

clean:
	rm -f *.pdf
	rm -f *.log
	rm -f *.out
	rm -f *.aux


