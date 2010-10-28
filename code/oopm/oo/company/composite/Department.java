// (C) 2009 Ralf Laemmel

package oo.company.composite;

import java.util.LinkedList;
import java.util.List;

/**
 * A department has a name (say, a descriptor).
 * There should be a manager for the department.
 * The department consists of units otherwise.
 * A unit can be (sub-) department or just an employee.
 */
public class Department extends Unit {

	private String descriptor;
	private Employee manager;
	private List<Unit> units = new LinkedList<Unit>();
	
	public String getDescriptor() { return descriptor; }
	public void setDescriptor(String descriptor) { this.descriptor = descriptor; }
	public Employee getManager() { return manager; }
	public void setManager(Employee manager) { this.manager = manager; }
	public List<Unit> getUnits() { return units; }

	/**
	 * Compute the total of all salaries for the employees of a department
	 */
	public double getTotal() {
		double total = 0;
		total += getManager().getSalary();
		for (Unit u : getUnits())
			total += u.getTotal();
		return total;
	}
		
	/**
	 * Increase the salaries for all employees of a department by a fixed amount
	 */
	public void increase(double x) {
		getManager().increase(x);
		for (Unit u : getUnits())
			u.increase(x);
	}
}
