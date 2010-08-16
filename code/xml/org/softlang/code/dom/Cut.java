package org.softlang.code.dom;

import static org.junit.Assert.*;

import org.softlang.code.Constants;
import org.softlang.code.sax.Validator;
import org.w3c.dom.Document; // DOM trees
import org.w3c.dom.Element; // DOM subtrees
import org.w3c.dom.NodeList; // Lists of DOM subtrees

/**
 * Cut all salaries of a company by half
 */
public class Cut {

    public static void transform(Document doc) {
	    	    
        // Get the matching elements
        NodeList nodelist = doc.getElementsByTagName("salary");
        		    
        // Process the elements in the nodelist
        for (int i=0; i<nodelist.getLength(); i++) {
        	
            // Get element
            Element elem = (Element)nodelist.item(i);
            
            // Transform content of element
            double before = Double.parseDouble(elem.getTextContent());
            double after = before / 2;            
            elem.setTextContent(Double.toString(after));
            
        }
    }
        
	public static void main(String[] args) {

		// For regression testing
		double total;

		// Load document
	    Document doc = DOMUtilities.loadDocument(Constants.Year2008);
	    
	    // Compute total
	    total = Total.compute(doc);
	    assertEquals(399747, total, 0);

	    // Transform and compute total again
	    transform(doc);
	    total = Total.compute(doc);
	    assertEquals(199873.5, total, 0);
	    
	    // Save the file
	    DOMUtilities.saveDocument(doc, Constants.Year2009);

	    // Validation -- just to be sure
	    Validator.validate(Constants.Year2009, Constants.Company);
	    
	}
}
