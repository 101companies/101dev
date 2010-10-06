package org.softlang.emf;

import company.*;

/**
 * Total all salaries.
 * 
 */
public class Total {

	public static double getTotal(Company company) {
		double total = 0;
		for (Dept dept : company.getDepts())
			total += getTotal(dept);
		return total;
	}

	private static double getTotal(Dept dept) {
		double total = getTotal(dept.getManager());
		for (Subunit subunit : dept.getSubunits())
			total += getTotal(subunit);
		return total;

	}

	private static double getTotal(Subunit subunit) {
		if (subunit.getPu() != null)
			return getTotal(subunit.getPu());
		else
			return getTotal(subunit.getDu());
	}

	private static double getTotal(Employee employee) {
		return employee.getSalary();

	}

}
