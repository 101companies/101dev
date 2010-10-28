// (C) 2009 Ralf Laemmel

package oo.company.visitor;

/**
 * An interface for visitors on company objects
 */
public interface Visitor {
	
	public void visit(Company c);
	public void visit(Department d);
	public void visit(Employee e);

}
