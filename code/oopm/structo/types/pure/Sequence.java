// (C) 2009 Ralf Laemmel

package structo.types.pure;

import java.util.LinkedList;
import java.util.List;

public class Sequence extends Statement {

	private LinkedList<Statement> statements =
		new LinkedList<Statement>();

	public List<Statement> getStatements() {
		return statements;
	}
}
