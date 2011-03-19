all:
	make LanguagesAnywhere.cloud
	make LanguagesInImplementations.cloud
	make TechnologiesAnywhere.cloud
	make TechnologiesInImplementations.cloud

clean:
	rm -f data/*.png

%.cloud:
	java \
		-jar ibm-word-cloud/ibm-word-cloud.jar \
		-c ibm-word-cloud/list.config \
		-i data/$*.txt -o data/$*.png

