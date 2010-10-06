package org.softlang.dom;

import org.junit.Test;
import org.w3c.dom.Document;

import static org.junit.Assert.*;

public class Tests {
		
    @Test
	public void testTotal() throws Exception {
    	Document doc = DOMUtilities.loadDocument("sampleCompany.xml");
    	double total = Total.getTotal(doc);
    	assertEquals(399747, total, 0);
 	}
	
    @Test
    public void testCut() throws Exception {
    	Document doc = DOMUtilities.loadDocument("sampleCompany.xml");
    	Cut.transform(doc);
    	double total = Total.getTotal(doc);
    	assertEquals(199873.5, total, 0);
    }    
}
