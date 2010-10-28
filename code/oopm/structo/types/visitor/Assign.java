// (C) 2009 Ralf Laemmel

package structo.types.visitor;

public class Assign extends Statement {
	private String lhs;
	private Expression rhs;
	public Assign(String lhs, Expression rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}
	public String getLhs() {
		return lhs;
	}
	public Expression getRhs() {
		return rhs;
	}
	public void accept(Visitor v) {
		v.visit(this);
	}
}
