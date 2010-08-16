package org.softlang.code.dom;

import javax.xml.transform.TransformerException;

import org.apache.xpath.XPathAPI;
import static org.junit.Assert.*;

import org.softlang.code.Constants;
import org.softlang.code.sax.Validator;
import org.w3c.dom.Document; // DOM trees
import org.w3c.dom.Element; // DOM subtrees
import org.w3c.dom.NodeList; // Lists of DOM subtrees

/**
 * Cut all manager salaries of a company by half.
 * We need to employ a non-Core API XPath processor to this end.
 */
public class CutManagers {

    public static boolean transform(Document doc) {
	    	    
		try {
	        // Get the matching elements
		    String xpath = "//manager/salary";
		    NodeList nodelist = XPathAPI.selectNodeList(doc, xpath);
        		    
	        // Process the elements in the nodelist
	        for (int i=0; i<nodelist.getLength(); i++) {
	        	
	            // Get element
	            Element elem = (Element)nodelist.item(i);
	            
	            // Transform content of element
	            double before = Double.parseDouble(elem.getTextContent());
	            double after = before / 2;            
	            elem.setTextContent(Double.toString(after));
	            
	        }

			// Done
            return true;

		} catch (TransformerException e) {
		}
		
		// Return false for any sort of problem
		return false;
            
    }
        
	public static void main(String[] args) {

		// For regression testing
		double total;

		// Load document
	    Document doc = DOMUtilities.loadDocument(Constants.Year2009);
	    
	    // Compute total
	    total = Total.compute(doc);
	    assertEquals(199873.5, total, 0);

	    // Transform and compute total again
	    transform(doc);
	    total = Total.compute(doc);
	    assertEquals(103917.5, total, 0);
	    
	    // Save the file
	    DOMUtilities.saveDocument(doc, Constants.Year2010);

	    // Validation -- just to be sure
	    Validator.validate(Constants.Year2010, Constants.Company);
	    
	}
}
