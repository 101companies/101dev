// (C) 2009 Ralf Laemmel

package oo.company.visitor;

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
	 * Dispatch to the appropriate visit method
	 */
	public void accept(Visitor v) {
		v.visit(this);
	}	
}
