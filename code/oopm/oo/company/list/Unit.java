// (C) 2009 Ralf Laemmel

package oo.company.list;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

/**
 * These are the units departments are built from.
 * A unit can be (sub-) department or just an employee. 
 */
public abstract class Unit {

	/**
	 * Return all aggregated employees as a list
	 */
	public Collection<Employee> getEmployees() {
		Collection<Employee> list = new LinkedList<Employee>();
		getEmployees(list);
		return Collections.unmodifiableCollection(list);
	}

	/**
	 * Add all aggregated employees to a given list
	 */
	/* package */ abstract void getEmployees(Collection<Employee> list);

}
