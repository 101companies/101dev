// (C) 2009--10 Ralf Laemmel

package uml.state.tableDriven;

import java.util.HashMap;

/**
 * Turnstiles as part of a subway system
 */
public class Turnstile {

	private State state = State.Locked;
	
	private HashMap<State, HashMap<Event, ActionState>> table = 
		new HashMap<State, HashMap<Event,ActionState>>();

	private void print() {
		System.out.println("Current state: \"" + state + "\"");		
	}
		
	public Turnstile() {

		//
		// Assume we are in the locked state
		//
		HashMap<Event, ActionState> atLocked = 
			new HashMap<Event, ActionState>();
		
		atLocked.put(Event.Ticket,
				new ActionState(
						Collect.singleton,
						State.Unlocked));
		
		atLocked.put(Event.Pass,
				new ActionState(
						Alarm.singleton,
						State.Locked));
		
		table.put(State.Locked, atLocked);

		//
		// Assume we are in the unlocked state
		//
		HashMap<Event, ActionState> atUnlocked = 
			new HashMap<Event, ActionState>();
		
		atUnlocked.put(Event.Ticket,
				new ActionState(
						Eject.singleton,
						State.Unlocked));
		
		atUnlocked.put(Event.Pass,
				new ActionState(
						Skip.singleton,
						State.Locked));

		table.put(State.Unlocked, atUnlocked);
	}
	
	public void Transition(Event event) {
		
		print();
		System.out.println("Someone trying to \"" + event + "\"");
		HashMap<Event, ActionState> atState = table.get(state);
		ActionState pair = atState.get(event);
		pair.action.execute();
		state = pair.state;
		
	}
	
}
