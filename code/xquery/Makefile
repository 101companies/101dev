input = Year2008.xml
basex = ../library/basex/BaseX61.jar
javasix = /System/Library/Frameworks/JavaVM.framework/Versions/1.6.0/Commands/java

test:
	${javasix} -classpath ${basex} org.basex.BaseX total.xq -i${input} > total.result.xml
	xpath total.result.xml "/result/text()" 2> /dev/null > total.result
	diff total.result total.baseline
	${javasix} -classpath ${basex} org.basex.BaseX cut.xq -i${input} > cut.result.xml
	${javasix} -classpath ${basex} org.basex.BaseX total.xq -icut.result.xml | xpath "/result/text()" 2> /dev/null > cut.result
	diff cut.result cut.baseline

clean:
	rm -f *~ *.result *.result.xml
