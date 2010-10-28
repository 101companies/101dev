// (C) 2009 Ralf Laemmel

package oo.company.virtual;

/**
 * These are the units departments are built from.
 * A unit can be (sub-) department or just an employee. 
 */
public abstract class Unit {

	/**
	 * Compute the total of all salaries for all employees of a unit
	 */
	public abstract double getTotal();
	
	/**
	 * Increase the salaries for all employees of a unit by a fixed amount
	 */
	public abstract void increase(double x);

	/**
	 * See Company.cut
	 */
	public void cut() {
	}
}
