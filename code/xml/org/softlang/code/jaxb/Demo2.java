// A variation that involves subtyping

package org.softlang.code.jaxb;

import org.softlang.company2.*;
import static org.junit.Assert.*;
import static org.softlang.code.Constants.*;

import java.io.*;
import javax.xml.bind.*;

public class Demo2 {
	
	public static void main (String[] args) throws Exception {

		// Unmarshal company 
		JAXBContext jaxbContext = JAXBContext.newInstance("org.softlang.company2");
		Unmarshaller unMarshaller = jaxbContext.createUnmarshaller();
		Company c = (Company) unMarshaller.unmarshal(new File(Year2008));
	
		// Total salaries, and cut them
	    double total = Total2.compute(c);
	    assertEquals(399747, total, 0);
	    Cut2.visit(c);
	    total = Total2.compute(c);
	    assertEquals(199873.5, total, 0);
		
	    // Unmarshaling omitted; see Demo.java.
	    
    }
}
