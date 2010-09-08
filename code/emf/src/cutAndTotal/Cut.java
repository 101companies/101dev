package cutAndTotal;

import company.*;

/**
 * Cut all salaries by half.
 * 
 */
public class Cut {

	public static void cutCompany(Company company) {
		for (Dept dept : company.getDepts())
			cutDept(dept);
	}

	private static void cutDept(Dept dept) {
		cutEmployee(dept.getManager());
		for (Subunit subunit : dept.getSubunits())
			cutSubunit(subunit);
	}

	private static void cutSubunit(Subunit subunit) {
		if (subunit.getPu() != null)
			cutEmployee(subunit.getPu());
		else
			cutDept(subunit.getDu());
	}

	private static void cutEmployee(Employee employee) {
		employee.setSalary(employee.getSalary() / 2);
	}

}
