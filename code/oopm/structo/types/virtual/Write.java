// (C) 2009 Ralf Laemmel

package structo.types.virtual;

import java.util.HashMap;

public class Write extends Statement {
	private Expression expr;
	public Write(Expression expr) {
		this.expr = expr;
	}
	public Expression getExpr() {
		return expr;
	}

	// Evaluate the expression and print value on standard output
	public void execute(HashMap<String,Integer> memory) {
		System.out.println(getExpr().evaluate(memory));
	}
}
