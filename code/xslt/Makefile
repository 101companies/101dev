input = Year2008.xml

test:
	xsltproc total.xslt ${input} > total.result
	diff total.result total.baseline
	xsltproc cut.xslt ${input} > cut.result.xml
	xsltproc total.xslt cut.result.xml > cut.result
	diff cut.result cut.baseline
	xsltproc depth.xslt ${input} > depth.result
	diff depth.result depth.baseline

test-saxon:
	java -jar ../library/saxon9he.jar -s:${input} -xsl:total.xslt >total.result
	diff total.result total.baseline
	java -jar ../library/saxon9he.jar -s:${input} -xsl:cut.xslt >cut.result.xml
	java -jar ../library/saxon9he.jar -s:cut.result.xml -xsl:total.xslt >cut.result
	diff cut.result cut.baseline
	java -jar ../library/saxon9he.jar -s:${input} -xsl:depth.xslt >depth.result
	diff depth.result depth.baseline

clean:
	rm -f *~ *.result *.result.xml
