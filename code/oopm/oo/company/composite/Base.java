// (C) 2009 Ralf Laemmel

package oo.company.composite;

/**
 * The base class for all company objects
 */
public abstract class Base {

	/**
	 * Compute the total of all salaries for all employees of a unit
	 */
	public abstract double getTotal();
	
	/**
	 * Increase the salaries for all employees of a unit by a fixed amount
	 */
	public abstract void increase(double x);

}
