// (C) 2009 Ralf Laemmel

package oo.company.composite;

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
	 * The total salary for an employee coincides with the regular salary
	 */	
	public double getTotal() {
		return getSalary();
	}
	
	/**
	 * Increase the salary of an employee by a fixed amount
	 */
	public void increase(double x) {
		setSalary(getSalary() + x);
	}
}
