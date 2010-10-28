// (C) 2009--10 Ralf Laemmel

package uml.state.tableDriven;

/**
 * The eject action 
 */
public class Eject extends Action {
	public static final Eject singleton = new Eject();
	public void execute() {	
		System.out.println("Ticket ejected.");		
	}
}
