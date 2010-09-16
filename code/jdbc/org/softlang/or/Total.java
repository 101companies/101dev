package org.softlang.or;

import org.softlang.or.company.*;

/**
 * Total all salaries
 * 
 */
public class Total {

	public static double totalCompany(Company company) {
		double ttl = 0;
		// total all salaries in all top departments
		for (Dept dept : company.getDepts())
			ttl += totalDept(dept);
		return ttl;
	}

	public static double totalDept(Dept dept) {
		double ttl = 0;
		// total all department's employees' salaries
		for (Employee employee : dept.getEmployees())
			ttl += totalEmployee(employee);
		// total all salaries in all sub departments
		for (Dept subDepartment : dept.getSubDepartments())
			ttl += totalDept(subDepartment);
		return ttl;
	}

	public static double totalEmployee(Employee employee) {
		return employee.getSalary();
	}

}
