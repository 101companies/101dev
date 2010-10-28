// (C) 2009--10 Ralf Laemmel

package uml.state.tableDriven;

/** 
 * The alarm action 
 */
public class Alarm extends Action {
	public static final Alarm singleton = new Alarm();
	public void execute() { 
		System.out.println("Alarm!");		
	}
}
