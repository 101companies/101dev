// (C) 2009 Ralf Laemmel

package uml.state.methodDispatch;

/** 
 * Behavior of turnstile in the locked state
 */
public class Locked extends State {

	public String printState() { return "Locked"; }
	
	/** Event: (Insert a) ticket */
	public void ticket(Turnstile me) {
		super.ticket(me);
		me.collect();
		me.state = unlocked;
	}

	/** Event: Pass (the turnstile) */
	public void pass(Turnstile me) {
		super.pass(me);
		me.alarm();
	}
}
