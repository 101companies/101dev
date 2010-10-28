// (C) 2009 Ralf Laemmel

package oo.counter.inheritance;

/**
 * A counter whose count is limited
 */
public class LimitedCounter extends BasicCounter {

	private int limit;
    
	/** Construct a limited counter from the limit */
    LimitedCounter(int i) {
        limit = i;
    }
    
    /** Stop stepping when the limit is reached */
    public void step() {
        if (read() < limit)
            super.step();
    }
}
