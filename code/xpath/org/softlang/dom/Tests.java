package org.softlang.dom;

import org.junit.Test;
import org.w3c.dom.Document;

import static org.junit.Assert.*;

public class Tests {
		
    @Test
	public void testTotal() throws Exception {
    	Document doc = DOMUtilities.loadDocument("Year2008.xml");
    	double total = Total.aggregate(doc);
    	assertEquals(399747, total, 0);
 	}
	
    @Test
    public void testCut() throws Exception {
    	Document doc = DOMUtilities.loadDocument("Year2008.xml");
    	Cut.transform(doc);
    	double total = Total.aggregate(doc);
    	assertEquals(199873.5, total, 0);
    }    

    @Test
    public void testCutManagers() throws Exception {
    	Document doc = DOMUtilities.loadDocument("Year2008.xml");
    	CutManagers.transform(doc);
    	double total = Total.aggregate(doc);
    	assertEquals(207835.0, total, 0);
	}
}
