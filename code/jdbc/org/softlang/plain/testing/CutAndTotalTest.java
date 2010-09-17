package org.softlang.plain.testing;

import static org.junit.Assert.*;

import java.sql.SQLException;
import org.junit.Test;
import org.softlang.plain.*;

/**
 * Testing Cut and Total. Please execute the scripts "CreateTables.sql" and
 * "PopulateTables.sql" before running this UnitTest.
 * 
 */
public class CutAndTotalTest {

	@Test
	public void testTotalAndCut() throws SQLException {
		
		MyConnection myConnection = new MyConnection("localhost",
				"company", 3306, "root", "");
		
		myConnection.connect();
		double preCutTotal = Total.total(myConnection);
		Cut.cut(myConnection);
		double newTotal = Total.total(myConnection);
		assertEquals(preCutTotal / 2, newTotal, 0.0);
	}

}
