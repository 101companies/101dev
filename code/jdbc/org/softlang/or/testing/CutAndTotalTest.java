package org.softlang.or.testing;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
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

		// get expected total from database
		ResultSet salaries = myConnection.getConn().prepareStatement(
				"SELECT salary FROM employee").executeQuery();
		double expectedTotal = 0.0;
		while (salaries.next())
			expectedTotal += salaries.getDouble("salary");

		assertEquals(expectedTotal, total, 0.0);
	}

	@Test
	public void testCut() {
		Cut.cutCompany(company);
		double newTotal = Total.totalCompany(company);
		// persist the company
		persistenceTool.persistCompany(company);
		// reload it
		company = factory.createCompany();
		double loadedTotal = Total.totalCompany(company);

		assertEquals(newTotal, loadedTotal, 0.0);
	}

}
