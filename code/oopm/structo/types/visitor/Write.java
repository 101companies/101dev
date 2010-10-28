// (C) 2009 Ralf Laemmel

package structo.types.visitor;

public class Write extends Statement {
	private Expression expr;
	public Write(Expression expr) {
		this.expr = expr;
	}
	public Expression getExpr() {
		return expr;
	}
	public void accept(Visitor v) {
		v.visit(this);
	}
}
