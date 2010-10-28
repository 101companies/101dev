// (C) 2009 Ralf Laemmel

package oo.company.visitor;

/**
 * A visitor to cut all top-level manager salaries.
 * These are the following employees:
 * (i) CEO;
 * (ii) CSA;
 * (iii) all managers of top-level departments of the company
 */
public class CutVisitor implements Visitor {

	public void visit(Company c) {
		c.getCeo().accept(this);
		c.getCsa().accept(this);
		for (Department d : c.getDepartments()) {
			d.getManager().accept(this);		
			for (Unit u : d.getUnits())
				u.accept(this);
		}
	}	
	
	public void visit(Department d) {
	}
	
	/**
	 * Cut salary in half
	 */
	public void visit(Employee e) {
		e.setSalary(e.getSalary() / 2);
	}
}
