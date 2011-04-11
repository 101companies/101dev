all:
	make $(patsubst %.txt,%.png,$(wildcard *.txt))

%.png: %.txt
	java \
		-jar ../ibm-word-cloud/ibm-word-cloud.jar \
		-c ../ibm-word-cloud/list.config \
		-i $*.txt -o $*.png

clean: 
	rm -f *.png

ignore:
	svn propedit svn:ignore .

