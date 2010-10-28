# (C) 2009 Ralf Laemmel

grammar = Structo.g
CLASSPATH = -classpath .:../../..:../../antlrworks-1.1.7.jar

build: Test.class

rebuild:
	make clean
	make build

test: build
	make id.test
	make factorial.test
	make fibonacci.test
	make gcd.test

Test.class: Test.java StructoLexer.java StructoParser.java
	javac ${CLASSPATH} Test.java StructoLexer.java StructoParser.java

StructoLexer.java StructoParser.java: Structo.g
	java ${CLASSPATH} org.antlr.Tool Structo.g

clean:
	rm -f *~
	rm -f *.class
	rm -f *.tokens
	rm -f *Lexer.java
	rm -f *Parser.java
	rm -f *__.g

%.test: Test.class
	java \
		-ea \
		${CLASSPATH} \
		structo.processor.visitor.Test \
		../../samples/$*.structo \
		< ../../samples/$*.input \
		> $*.tmp
		diff $*.tmp ../../samples/$*.output
		rm -f $*.tmp

fail:
	-make undeclared.test
	-make uninitialized.test	
	rm -f *.tmp
	
