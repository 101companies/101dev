// (C) 2009 Ralf Laemmel

package structo.types.virtual;

import java.util.HashMap;

public class Binary extends Expression {
	private Op op;
	private Expression left;
	private Expression right;
	public Binary(Op op, Expression left, Expression right) {
		this.op = op;
		this.left = left;
		this.right = right;
	}
	public Op getOp() {	return op; }
	public Expression getLeft() { return left; }
	public Expression getRight() { return right; }

	public int evaluate(HashMap<String, Integer> memory) {
		int v1 = getLeft().evaluate(memory);
		int v2 = getRight().evaluate(memory);
		switch (getOp()) {
		case Plus : return v1 + v2;
		case Minus : return v1 - v2;
		case Times : return v1 * v2;
		case Div : return v1 / v2;
		case Equal : return v1 == v2 ? 1 : 0;
		case NotEqual : return v1 == v2 ? 0 : 1;
		case Below : return v1 < v2 ? 1 : 0;
		case Greater : return v1 > v2 ? 1 : 0;		
		case BelowEq : return v1 <= v2 ? 1 : 0;
		case GreaterEq : return v1 >= v2 ? 1 : 0;
		default : throw new RuntimeException();
		}
	}
}
