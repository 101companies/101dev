package org.softlang.antlr;

import java.io.FileInputStream;
import java.io.IOException;
import org.antlr.runtime.*;
import org.junit.Test;
// import static org.junit.Assert.*;

public class Tests {
		
	private static String sampleCompany = "sample.Company";

	@Test
	public void testParse() throws IOException, RecognitionException {
		FileInputStream stream = new FileInputStream(sampleCompany);
        ANTLRInputStream antlr = new ANTLRInputStream(stream);
        CompanyLexer lexer = new CompanyLexer(antlr);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CompanyParser parser = new CompanyParser(tokens);
        parser.company();		
	}
	
//    @Test
//	public void testTotal() throws Exception {
//    	Document doc = DOMUtilities.loadDocument(sampleCompany);
//    	double total = Total.total(doc);
//    	assertEquals(399747, total, 0);
// 	}
//	
//    @Test
//    public void testCut() throws Exception {
//    	Document doc = DOMUtilities.loadDocument(sampleCompany);
//    	Cut.cut(doc);
//    	double total = Total.total(doc);
//    	assertEquals(199873.5, total, 0);
//    }    
}
