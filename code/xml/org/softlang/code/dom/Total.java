package org.softlang.code.dom;

import static org.junit.Assert.*;
import org.softlang.code.Constants;
import org.w3c.dom.Document; // DOM trees
import org.w3c.dom.Element; // DOM subtrees
import org.w3c.dom.NodeList; // Lists of DOM subtrees

/**
 * Compute the total all salaries of a company
 */
public class Total {

    public static double compute(Document doc) {
	    
	    // The aggregation variable 
	    double total = 0;
	    
        // Get the matching elements
        NodeList nodelist = doc.getElementsByTagName("salary");
        		    
        // Process the elements in the nodelist
        for (int i=0; i<nodelist.getLength(); i++) {
            // Get element
            Element elem = (Element)nodelist.item(i);
            total += Double.parseDouble(elem.getTextContent());
        }
	    return total;
    }
        
	public static void main(String[] args) {
	    Document doc = DOMUtilities.loadDocument(Constants.Year2008);
	    double total = compute(doc);
	    assertEquals(399747, total, 0);
	}
}
