input = Year2008.xml

test:
	xsltproc total.xslt ${input} > total.result
	diff total.result total.baseline
	xsltproc cut.xslt ${input} > cut.result
	diff cut.result cut.baseline
	xsltproc depth.xslt ${input} > depth.result
	diff depth.result depth.baseline

clean:
	rm -f *~ *.result
