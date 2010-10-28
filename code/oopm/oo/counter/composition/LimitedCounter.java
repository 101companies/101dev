// (C) 2009 Ralf Laemmel

package oo.counter.composition;

/**
 * A counter whose count is limited.
 * Implement the basic functionality by delegation to a basic counter.
 * Except for skip: we need to constrain the invocation of the delegate.
 */
public class LimitedCounter implements Counter {

	private BasicCounter inner = new BasicCounter();
	private int limit;
    	
	/** Return the value of the counter */
	public int read() {
		return inner.read();
	}
	
	/** Reset the counter to zero */
	public void reset() {
		inner.reset();
	}
	
	/** Construct a limited counter from the limit */
    LimitedCounter(int i) {
        limit = i;
    }
    
    /** Stop stepping when the limit is reached */
    public void step() {
        if (read() < limit)
            inner.step();
    }
}
