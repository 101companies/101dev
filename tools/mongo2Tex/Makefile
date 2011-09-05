all:
	make coveragetex
	make tableDoc

coveragetex:
	echo "createCoverageTable" | ghci -v0 CoverageTable.hs > coverage.tex
	
tableDoc:
	pdflatex CoverageDoc.tex
    