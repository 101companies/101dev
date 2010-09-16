package org.softlang;

import org.softlang.om.*;

public class PrintCompany {

	public static void print(Company company) {
		for (Dept dept : company.getDepts()) {
			print(dept, "- ");
			System.out.println();
		}
	}

	public static void print(Dept dept, String pre) {
		System.out.println(pre + "DEPT");
		System.out.println(" " + pre + " Name: " + dept.getName());
		System.out.println(" " + pre + " Manager: " + dept.getManager().getName());
		for (Employee employee : dept.getEmployees())
				print(employee, " " + pre);
		for (Dept subDepartment : dept.getSubDepartments()) {
			print(subDepartment, "    " + pre);
		}
	}

	public static void print(Employee employee, String pre) {
		System.out.println(pre + " Employee:");
		System.out.println(" " + pre + "  Name: " + employee.getName());
		System.out.println(" " + pre + "  Address: " + employee.getAddress());
		System.out.println(" " + pre + "  Salary: " + employee.getSalary());
	}
}
