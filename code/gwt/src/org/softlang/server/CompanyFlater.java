package org.softlang.server;

import java.util.HashMap;

import org.softlang.server.company.*;

public class CompanyFlater {

	private HashMap<String, Dept> deptMap;

	private HashMap<String, Employee> employeeMap;

	public CompanyFlater() {
		deptMap = new HashMap<String, Dept>();
		employeeMap = new HashMap<String, Employee>();
	}

	public void flatCompany(Company company) {
		for (Dept dept : company.getDepts())
			flatDept(dept);
	}

	private void flatDept(Dept dept) {
		deptMap.put(dept.getName(), dept);
		flatEmployee(dept.getManager());
		for (Subunit subunit : dept.getSubunits()) {
			if (subunit.getPu() != null)
				flatEmployee(subunit.getPu());
			else
				flatDept(subunit.getDu());
		}
	}

	private void flatEmployee(Employee employee) {
		employeeMap.put(employee.getPerson().getName(), employee);
	}

	public HashMap<String, Dept> getDeptMap() {
		return deptMap;
	}

	public HashMap<String, Employee> getEmployeeMap() {
		return employeeMap;
	}

}
