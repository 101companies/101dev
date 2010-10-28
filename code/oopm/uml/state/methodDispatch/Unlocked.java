// (C) 2009 Ralf Laemmel

package uml.state.methodDispatch;

/**
 * Behavior of turnstiles in unlocked state
 */
public class Unlocked extends State {

	public String printState() { return "Unlocked"; }

	/** Event: (Insert a) ticket */
	public void ticket(Turnstile me) {
		super.ticket(me);
		me.eject();
	}

	/** Event: Pass (the turnstile) */
	public void pass(Turnstile me) {
		super.pass(me);
		me.state = locked;
	}
}
