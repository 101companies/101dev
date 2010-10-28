// (C) 2009 Ralf Laemmel

package structo.types.virtual;

import java.util.HashMap;

public class Skip extends Statement {

	// The empty statement is a no-op
	public void execute(HashMap<String,Integer> memory) {
	}
}
