package org.softlang.jdbc;

import java.sql.SQLException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testing the company scenarios.
 * See the README. 
 */
public class Tests {

	@Test
	public void testTotalAndCut() throws SQLException {

		MyConnection myConnection =
			new MyConnection(
					"localhost",
					"company",
					3306,
					"root", "");

		myConnection.connect();
		
		// Test scenario "total"
		double preCutTotal = Total.getTotal(myConnection);
		assertEquals(399747, preCutTotal, 0.0);
		
		// Test scenario "cut"
		Cut.cut(myConnection);
		double newTotal = Total.getTotal(myConnection);
		assertEquals(preCutTotal / 2, newTotal, 0.0);
	}

}
