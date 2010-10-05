package org.softlang.emf;

import company.*;

/**
 * Total all salaries.
 * 
 */
public class Total {

	public static double totalCompany(Company company) {
		double ttl = 0;
		for (Dept dept : company.getDepts())
			ttl += totalDept(dept);
		return ttl;
	}

	private static double totalDept(Dept dept) {
		double ttl = totalEmployee(dept.getManager());
		for (Subunit subunit : dept.getSubunits())
			ttl += totalSubunit(subunit);
		return ttl;

	}

	private static double totalSubunit(Subunit subunit) {
		if (subunit.getPu() != null)
			return totalEmployee(subunit.getPu());
		else
			return totalDept(subunit.getDu());
	}

	private static double totalEmployee(Employee employee) {
		return employee.getSalary();

	}

}
