all:
	make coveragetex
	make tableDoc

coveragetex:
	echo "main" | ghci -v0 CoverageTable.hs > coverage.tex
	
tableDoc:
	pdflatex CoverageDoc.tex
	pdflatex CoverageDoc.tex
	
clean:
	rm -f *.aux
	rm -f *.bbl
	rm -f *.blg
	rm -f *.log
	rm -f *.nav
	rm -f *.out
	rm -f *.pdf
	rm -f *.snm
	rm -f *.toc
	rm -f coverage.tex
	