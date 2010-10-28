// (C) 2009 Ralf Laemmel

package oo.company.composite;

import java.util.List;
import java.util.LinkedList;

/**
 * A company has a CEO, a CSA, and possibly other TLAs that we skip here.
 * A company consists of departments otherwise.
 */
public class Company extends Base {
	private Employee ceo;
	private Employee csa;
	private List<Department> departments = new LinkedList<Department>();
	
	public Employee getCeo() { return ceo; }
	public void setCeo(Employee ceo) { this.ceo = ceo; }
	public Employee getCsa() { return csa; }
	public void setCsa(Employee csa) { this.csa = csa; }
	public List<Department> getDepartments() { return departments; }

	/**
	 * Compute the total of all salaries for the employees of the company
	 */
	public double getTotal() {
		double total = 0;
		total += getCeo().getSalary();
		total += getCsa().getSalary();
		for (Department d : getDepartments())
			total += d.getTotal();
		return total;
	}	
	
	/**
	 * Increase the salaries for all employees of the company by a fixed amount
	 */
	public void increase(double x) {
		getCeo().increase(x);
		getCsa().increase(x);
		for (Department d : getDepartments())
			d.increase(x);
	}
}
