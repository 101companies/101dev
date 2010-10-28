// (C) 2009 Ralf Laemmel

package structo.types.virtual;

import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;

public class Sequence extends Statement {
	private LinkedList<Statement> statements =
		new LinkedList<Statement>();

	public List<Statement> getStatements() {
		return statements;
	}
	
	// Execute all statements in a statement sequence
	public void execute(HashMap<String,Integer> memory) {
		for (Statement s : getStatements())
			s.execute(memory);
	}
}
