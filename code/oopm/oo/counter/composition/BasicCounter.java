// (C) 2009 Ralf Laemmel

package oo.counter.composition;

/**
 *  A simple class of counters 
 */
public class BasicCounter implements Counter {

	// Package-private state of the counter
	/* default */ int count = 0;
	
	/** Increment the counter */
	public void step() { count++; }
	
	/** Return the value of the counter */
	public int read() { return count; }
	
	/** Reset the counter to zero */
	public void reset() { count = 0; }
	
}
