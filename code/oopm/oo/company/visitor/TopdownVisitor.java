// (C) 2009 Ralf Laemmel

package oo.company.visitor;

/**
 * Get to see all subobjects of companies in top-down order
 */
public abstract class TopdownVisitor implements Visitor {
	
	public void visit(Company c) {
		c.getCeo().accept(this);
		c.getCsa().accept(this);
		for (Department d : c.getDepartments())
			d.accept(this);
	}

	public void visit(Department d) {
		d.getManager().accept(this);
		for (Unit u : d.getUnits())
			u.accept(this);		
	}

	public void visit(Employee e) {
	}
}
