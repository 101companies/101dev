// (C) 2009 Ralf Laemmel

package oo.company.list;

import java.util.Collections;
import java.util.Collection;
import java.util.List;
import java.util.LinkedList;

/**
 * A company has a CEO, a CSA, and possibly other TLAs that we skip here.
 * A company consists of departments otherwise.
 */
public class Company {
	private Employee ceo;
	private Employee csa;
	private List<Department> departments = new LinkedList<Department>();
	
	public Employee getCeo() { return ceo; }
	public void setCeo(Employee ceo) { this.ceo = ceo; }
	public Employee getCsa() { return csa; }
	public void setCsa(Employee csa) { this.csa = csa; }
	public List<Department> getDepartments() { return departments; }

	/**
	 * Return all aggregated employees as a list
	 */
	public Collection<Employee> getEmployees() {
		LinkedList<Employee> list = new LinkedList<Employee>();
		list.add(getCeo());
		list.add(getCsa());
		for (Department d : getDepartments())
			d.getEmployees(list);
		return Collections.unmodifiableCollection(list);
	}
}
