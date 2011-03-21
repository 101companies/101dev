all:
	make $(patsubst %.txt,%.png,$(wildcard *.txt))

%.png:
	java \
		-jar ../ibm-word-cloud/ibm-word-cloud.jar \
		-c ../ibm-word-cloud/list.config \
		-i $*.txt -o $*.png

ignore:
	svn propedit svn:ignore .

