// (C) 2009 Ralf Laemmel

package uml.state.methodDispatch;

/**
 * The base class of behavioral states of turnstiles
 */
public abstract class State {

	static protected Locked locked = new Locked();
	static protected Unlocked unlocked = new Unlocked();
	
	public void print() {
		System.out.println("Current state: \"" + printState() + "\"");		
	}
	
	protected abstract String printState();
	
	/** Event: (Insert) a ticket */
	public void ticket(Turnstile me) {
		System.out.println("Someone trying to \"Ticket\".");
	}

	/** Event: Pass (the turnstile) */
	public void pass(Turnstile me) {
		System.out.println("Someone trying to \"Pass\".");
	}
}
