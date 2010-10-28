// (C) 2009 Ralf Laemmel

package oo.company.visitor;

/**
 * A visitor to compute the total of all salaries for the employees of the company
 */
public class TotalVisitor extends TopdownVisitor {

	public double total = 0;
	
	public void visit(Employee e) {
		total += e.getSalary();
	}
}
