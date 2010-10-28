// (C) 2009 Ralf Laemmel

package oo.counter.inheritance;

/**
 * A counter with recall
 */
public class RecallCounter extends BasicCounter {

	private int snap = read();
    
	/** Store the counter state until later */
    public void mark() {
        snap = read();
    }

    /** Reset the counter for recall */
    public void recall() {
        count = snap;
    }
}