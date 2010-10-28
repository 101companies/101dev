// (C) 2009 Ralf Laemmel

package structo.types.visitor;

public class Skip extends Statement {
	public void accept(Visitor v) {
		v.visit(this);
	}
}
