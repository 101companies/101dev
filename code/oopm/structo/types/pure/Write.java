// (C) 2009 Ralf Laemmel

package structo.types.pure;

public class Write extends Statement {
	private Expression expr;
	public Write(Expression expr) {
		this.expr = expr;
	}
	public Expression getExpr() {
		return expr;
	}
}
