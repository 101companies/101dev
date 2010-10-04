input = sampleCompany.xml
saxon = ../library/saxon/saxon9he.jar

test:
	xsltproc total.xslt ${input} > total.result
	diff total.result total.baseline
	xsltproc cut.xslt ${input} > cut.result.xml
	xsltproc total.xslt cut.result.xml > cut.result
	diff cut.result cut.baseline
	xsltproc depth.xslt ${input} > depth.result
	diff depth.result depth.baseline

test-saxon:
	java -jar ${saxon} -s:${input} -xsl:total.xslt >total.result
	diff total.result total.baseline
	java -jar ${saxon} -s:${input} -xsl:cut.xslt >cut.result.xml
	java -jar ${saxon} -s:cut.result.xml -xsl:total.xslt >cut.result
	diff cut.result cut.baseline
	java -jar ${saxon} -s:${input} -xsl:depth.xslt >depth.result
	diff depth.result depth.baseline

clean:
	rm -f *~ *.result *.result.xml
