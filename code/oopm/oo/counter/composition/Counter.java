// (C) 2009 Ralf Laemmel

package oo.counter.composition;

/**
 *  The shared interface of counters 
 */
public interface Counter {
	
	/** Increment the counter */
	public void step();
	
	/** Return the value of the counter */
	public int read();
	
	/** Reset the counter to zero */
	public void reset();
	
}
