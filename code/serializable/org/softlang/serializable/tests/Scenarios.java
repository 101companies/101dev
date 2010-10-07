package org.softlang.serializable.tests;

import org.softlang.company.Company;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.softlang.util.SerializationUtilities.*;

public class Scenarios {
	
	@Test
	public void testTotal() {
		Company c = (Company)readObject("sampleCompany.ser");
	    double total = c.total();		
	    assertEquals(399747, total, 0);
	}
	
	@Test
	public void testCut() {
		Company c = (Company)readObject("sampleCompany.ser");
		c.cut();
	    double total = c.total();		
	    assertEquals(199873.5, total, 0);
	}
}
