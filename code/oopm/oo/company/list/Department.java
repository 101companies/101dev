// (C) 2009 Ralf Laemmel

package oo.company.list;

import java.util.Collection;
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
	 * Add all aggregated employees to a given list
	 */
	/* package */ void getEmployees(Collection<Employee> list) {
		list.add(getManager());
		for (Unit u : getUnits())
			u.getEmployees(list);
	}
}
