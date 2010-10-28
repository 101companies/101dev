// (C) 2009--10 Ralf Laemmel

package uml.state.switchCase;

/**
 * Turnstiles as part of a subway system
 */
public class Turnstile {

	private State state = State.Locked;

	public Turnstile() {
		print();
	}
	
	private void print() {
		System.out.println("Current state: \"" + state + "\"");		
	}
	
	/** Action: alarm */
	protected void alarm() {
		System.out.println("Alarm!");
	}	
	
	/** Action: collect (a ticket) */
	protected void collect() {
		System.out.println("Ticket collected.");
	}
	
	/** Action: eject (a ticket) */
	protected void eject() {
		System.out.println("Ticket ejected.");
	}
	
	/** Event: (insert) a ticket */
	public void ticket() {
		System.out.println("Someone trying to \"Ticket\".");
		switch (state) {
			case Locked : 
				collect();
				state = State.Unlocked;
				break;
			case Unlocked :
				eject();
				break;
		}
		print();
	}

	/** Event: pass (the turnstile) */
	public void pass() {
		System.out.println("Someone trying to \"Pass\".");
		switch (state) {
			case Locked : 
				alarm();
				break;
			case Unlocked :
				state = State.Locked;
				break;
		}
		print();
	}
}
