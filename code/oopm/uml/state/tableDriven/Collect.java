// (C) 2009--10 Ralf Laemmel

package uml.state.tableDriven;

/**
 * The collect action 
 */
public class Collect extends Action {
	public static final Collect singleton = new Collect();
	public void execute() {	
		System.out.println("Ticket collected.");		
	}
}
