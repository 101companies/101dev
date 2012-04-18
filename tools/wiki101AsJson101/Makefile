all:
	mkdir -p ../data/generated
	php jsongenerator.php

deploy:
	scp ../data/generated/*.json wiki101@sl-mac.uni-koblenz.de:/Users/wiki101/Sites/
