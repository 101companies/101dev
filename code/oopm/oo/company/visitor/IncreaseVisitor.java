// (C) 2009 Ralf Laemmel

package oo.company.visitor;

/**
 * A visitor to increase the salaries for all employees of the company by a fixed amount
 */
public class IncreaseVisitor extends TopdownVisitor {

	public double increase;
	
	public void visit(Employee e) {
		e.setSalary(e.getSalary() + increase);
	}
}
