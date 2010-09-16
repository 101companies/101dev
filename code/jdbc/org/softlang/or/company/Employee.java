package org.softlang.or.company;

/**
 * An Employee has a salary and some person information
 * 
 */
public class Employee {

	private int employeeid;
	private String name;
	private String address;
	private double salary;
	private boolean changed;

	public Employee() {
		employeeid = 0;
		changed = true;
	}

	public Employee(int employeeid) {
		this.employeeid = employeeid;
	}

	public int getEmployeeid() {
		return employeeid;
	}

	public void setEmploeeid(int employeeid) {
		if (this.employeeid == 0)
			this.employeeid = employeeid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		changed = true;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
		changed = true;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
		changed = true;
	}

	public void setUnchanged() {
		changed = false;
	}

	public boolean wasChanged() {
		return changed;
	}

	public boolean equals(Object o) {
		Employee otherEmployee = (Employee) o;
		return this.getSalary() == otherEmployee.getSalary()
				&& this.getName().equals((otherEmployee).getName())
				&& this.getAddress().equals((otherEmployee).getAddress());
	}
}
