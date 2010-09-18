package org.softlang.sax;

import org.junit.Test;
import static org.junit.Assert.*;

public class Tests {

    @Test
	public void testTrace() throws Exception {
		Trace handler = new Trace();
		SAXUtilities.parse(handler, "Year2008.xml");
	}
		
    @Test
	public void testTotal() throws Exception {
		Total handler = new Total();
		SAXUtilities.parse(handler, "Year2008.xml");
	    assertEquals(399747, handler.getTotal(), 0);		
	}
	
    @Test
    public void testCut() throws Exception {
    	Cut handler1 = new Cut("Year2009.xml");
    	SAXUtilities.parse(handler1, "Year2008.xml");
		Total handler2 = new Total();
		SAXUtilities.parse(handler2, "Year2009.xml");
	    assertEquals(199873.5, handler2.getTotal(), 0);		
    }    
    
    @Test
    public void testValidate() {
     	assertTrue(Validator.validate("Year2008.xml", "Company.xsd"));
     	assertTrue(Validator.validate("Year2009.xml", "Company.xsd")); 	
    }    
}
