package org.softlang.or.testing;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;
import org.softlang.or.*;
import org.softlang.or.company.*;
import org.softlang.plain.MyConnection;

/**
 * Testing Cut and Total. Please execute the scripts "CreateTables.sql" and
 * "PopulateTables.sql" before running this UnitTest.
 * 
 */
public class CutAndTotalTest {

	private MyConnection myConnection = new MyConnection("localhost",
			"company", 3306, "root", "");

	private ObjectFactory factory = new ObjectFactory(myConnection);
	private PersistenceTool persistenceTool = new PersistenceTool(myConnection);
	private Company company = factory.createCompany();

	@Test
	public void testTotal() throws SQLException {
		double total = Total.totalCompany(company);
		assertEquals(399747, total, 0.0);
	}

	@Test
	public void testCut() {
		double preCutTotal = Total.totalCompany(company);
		Cut.cutCompany(company);
		// persist and reload company
		persistenceTool.persistCompany(company);
		double newTotal = Total.totalCompany(factory.createCompany());
		assertEquals(preCutTotal / 2, newTotal, 0.0);
	}

}
