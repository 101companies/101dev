// (C) 2009 Ralf Laemmel

package structo.types.pure;

public class If extends Statement {
	private Expression cond;
	private Statement thenBranch;
	private Statement elseBranch;
	public If(Expression cond, Statement thenBranch, Statement elseBranch) {
		this.cond = cond;
		this.thenBranch = thenBranch;
		this.elseBranch = elseBranch;
	}
	public Expression getCond() {
		return cond;
	}
	public Statement getThenBranch() {
		return thenBranch;
	}
	public Statement getElseBranch() {
		return elseBranch;
	}
}
