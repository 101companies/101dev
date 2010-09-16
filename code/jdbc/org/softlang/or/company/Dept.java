package org.softlang.or.company;

import org.softlang.or.util.SimpleFlaggedList;

/**
 * A department has a name, a manager and a list of subunits
 * 
 */
public class Dept {

	private int deptid;
	private String name;
	private Employee manager;
	private SimpleFlaggedList<Dept> subDepartments;
	private SimpleFlaggedList<Employee> employees;
	private boolean changed;

	public Dept() {
		deptid = 0;
		subDepartments = new SimpleFlaggedList<Dept>();
		employees = new SimpleFlaggedList<Employee>();
		changed = true;
	}

	public Dept(int deptid) {
		this.deptid = deptid;
		subDepartments = new SimpleFlaggedList<Dept>();
		employees = new SimpleFlaggedList<Employee>();
	}

	public int getDeptid() {
		return deptid;
	}

	public void setDeptid(int deptid) {
		if (this.deptid == 0)
			this.deptid = deptid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		changed = true;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
		changed = true;
	}

	public SimpleFlaggedList<Dept> getSubDepartments() {
		return subDepartments;
	}

	public void setSubDepartments(SimpleFlaggedList<Dept> subDepartments) {
		this.subDepartments = subDepartments;
		changed = true;
	}

	public SimpleFlaggedList<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(SimpleFlaggedList<Employee> employees) {
		this.employees = employees;
		changed = true;
	}

	public void setUnchanged() {
		changed = false;
	}

	public boolean wasChanged() {
		boolean employeesChanged = employees.wasChanged();
		for (Employee employee : employees)
			employeesChanged |= employee.wasChanged();
		boolean subDeptsChanged = subDepartments.wasChanged();
		for (Dept dept : subDepartments)
			subDeptsChanged |= dept.wasChanged();
		return manager.wasChanged() || employeesChanged || subDeptsChanged
				|| this.changed;
	}

	@Override
	public boolean equals(Object o) {
		boolean isEqual = true;
		Dept otherDept = (Dept) o;
		isEqual &= this.getSubDepartments().size() == otherDept
				.getSubDepartments().size();
		isEqual &= this.getEmployees().size() == otherDept.getEmployees()
				.size();
		for (int i = 0; i < this.getSubDepartments().size() && isEqual; i++)
			isEqual &= this.getSubDepartments().get(i).equals(
					otherDept.getSubDepartments().get(i));
		for (int i = 0; i < this.getEmployees().size() && isEqual; i++)
			isEqual &= this.getEmployees().get(i).equals(
					otherDept.getEmployees().get(i));
		isEqual &= this.getManager().equals(otherDept.getManager());
		return isEqual;
	}
}
