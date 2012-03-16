all:
	mkdir -p data
	php jsongenerator.php

clean: 
	rm -rf data

deploy:
	scp data/all.json wiki101@sl-mac.uni-koblenz.de:/Users/wiki101/Sites/
