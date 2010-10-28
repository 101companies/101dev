// (C) 2009 Ralf Laemmel

package oo.company.list;

import java.util.Collection;

/**
 * An employee has attributes for name and salary.
 */
public class Employee extends Unit {

	private String name;
	private double salary;
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public double getSalary() { return salary; }
	public void setSalary(double salary) { this.salary = salary; }

	/**
	 * Add all aggregated employees to a given list
	 */
	/* package */ void getEmployees(Collection<Employee> list) {
		list.add(this);
	}
}
