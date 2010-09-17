package org.softlang.or.testing;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;
import org.softlang.or.*;
import org.softlang.or.company.*;
import org.softlang.plain.MyConnection;

/**
 * Testing loading and storing a company. Please execute the scripts
 * "CreateTables.sql" and "PopulateTables.sql" before running this UnitTest.
 * 
 */
public class PersistenceTest {

	private MyConnection myConnection = new MyConnection("localhost",
			"company", 3306, "root", "");

	private ObjectFactory factory = new ObjectFactory(myConnection);
	private PersistenceTool persistenceTool = new PersistenceTool(myConnection);

	@Test
	public void testPersistency() throws FileNotFoundException, IOException,
			ClassNotFoundException {

		// add a new small testing department
		Company company = factory.createCompany();
		Dept testing = new Dept();
		testing.setName("Testing");

		Employee ross = new Employee();
		ross.setName("Ross");
		ross.setAddress("London");
		ross.setSalary(34567.0);
		testing.setManager(ross);
		testing.getEmployees().add(ross);

		Dept testing1 = new Dept();
		testing1.setName("Testing1");
		Employee dan = new Employee();
		dan.setName("Dan");
		dan.setAddress("New York");
		dan.setSalary(4567.0);
		testing1.setManager(dan);
		testing1.getEmployees().add(dan);
		Employee bob = new Employee();
		bob.setName("Bob");
		bob.setAddress("Chicago");
		bob.setSalary(4566.0);
		testing1.getEmployees().add(bob);

		testing.getSubDepartments().add(testing1);
		company.getDepts().add(testing);

		// persist the company
		persistenceTool.persistCompany(company);
		// reload it
		Company loadedCompany = factory.createCompany();
		// shows us whether the PersitenceTool works correctly
		assertTrue(company.equals(loadedCompany));

	}

}
