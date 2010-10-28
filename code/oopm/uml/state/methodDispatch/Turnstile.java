// (C) 2009--10 Ralf Laemmel

package uml.state.methodDispatch;

/**
 * Turnstiles as part of a subway system
 */
public class Turnstile {

	State state = new Locked();
	
	/** Action: alarm */
	public void alarm() {
		System.out.println("Alarm!");
	}	
	
	/** Action: collect (a ticket) */
	public void collect() {
		System.out.println("Ticket collected.");
	}
	
	/** Action: eject (a ticket) */
	public void eject() {
		System.out.println("Ticket ejected.");
	}
	
	/** Event: (Insert) a ticket */
	public void ticket() {
		state.print();
		state.ticket(this);
	}

	/** Event: Pass (the turnstile) */
	public void pass() {
		state.print();
		state.pass(this);
	}	
}
