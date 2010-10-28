// (C) 2009 Ralf Laemmel

package oo.gui.counter;

/**
 *  A simple class of counters 
 */
public final class Counter {

	/** Private state of the counter */
	private int count = 0;
	
	/** Increment the counter */
	public void step() { count++; }
	
	/** Return the value of the counter */
	public int getCount() { return count; }
	
	/** Reset the counter to zero */
	public void reset() { count = 0; }
	
}