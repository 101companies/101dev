// (C) 2009--10 Ralf Laemmel

package uml.state.tableDriven;

/**
 * The skip action (do nothing; no-op)
 */
public class Skip extends Action {
	public static final Skip singleton = new Skip();
	public void execute() {	
		// This is a sort of a silent action.
		System.out.println("Passed, indeed.");			
	}
}
