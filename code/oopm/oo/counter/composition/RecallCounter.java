// (C) 2009 Ralf Laemmel

package oo.counter.composition;

/**
 * A counter with recall.
 * Implement the basic functionality by delegation to a basic counter.
 */
public class RecallCounter implements Counter {

	private BasicCounter inner = new BasicCounter();
	private int snap = inner.read();

	/** Increment the counter */
	public void step() {
		inner.step();
	}
	
	/** Return the value of the counter */
	public int read() {
		return inner.read();
	}
	
	/** Reset the counter to zero */
	public void reset() {
		inner.reset();
	}
	
	/** Store the counter state until later */
    public void mark() {
        snap = inner.read();
    }

    /** Reset the counter for recall */
    public void recall() {
        inner.count = snap;
    }
}