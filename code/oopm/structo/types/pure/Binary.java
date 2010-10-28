// (C) 2009 Ralf Laemmel

package structo.types.pure;

public class Binary extends Expression {
	private Op op;
	private Expression left;
	private Expression right;
	public Binary(Op op, Expression left, Expression right) {
		this.op = op;
		this.left = left;
		this.right = right;
	}
	public Op getOp() {
		return op;
	}
	public Expression getLeft() {
		return left;
	}
	public Expression getRight() {
		return right;
	}
}
