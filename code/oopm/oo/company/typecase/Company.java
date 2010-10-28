// (C) 2009 Ralf Laemmel

package oo.company.typecase;

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

}
