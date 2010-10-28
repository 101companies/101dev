// (C) 2009--10 Ralf Laemmel

package uml.state.tableDriven;

/**
 * So that we can pair up actions and states ...
 */
public class ActionState {

	public Action action;
	public State state;

	public ActionState(Action a, State s) {
		action = a;
		state = s;
	}
}
