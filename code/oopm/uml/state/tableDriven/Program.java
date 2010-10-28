// (C) 2009--10 Ralf Laemmel

package uml.state.tableDriven;

public class Program {

	public static void main(String[] args) {
		Turnstile t = new Turnstile();
		t.Transition(Event.Pass);
		t.Transition(Event.Ticket);
		t.Transition(Event.Pass);
	}

}
