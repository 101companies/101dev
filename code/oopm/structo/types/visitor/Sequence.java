// (C) 2009 Ralf Laemmel

package structo.types.visitor;

import java.util.LinkedList;
import java.util.List;

public class Sequence extends Statement {

	private LinkedList<Statement> statements =
		new LinkedList<Statement>();

	public List<Statement> getStatements() {
		return statements;
	}
	public void accept(Visitor v) {
		v.visit(this);
	}
}
