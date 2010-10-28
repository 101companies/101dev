// (C) 2009 Ralf Laemmel

package structo.types.virtual;

import java.util.HashMap;

public class If extends Statement {
	private Expression cond;
	private Statement thenBranch;
	private Statement elseBranch;
	public If(Expression cond, Statement thenBranch, Statement elseBranch) {
		this.cond = cond;
		this.thenBranch = thenBranch;
		this.elseBranch = elseBranch;
	}
	public Expression getCond() { return cond; }
	public Statement getThenBranch() {	return thenBranch; }
	public Statement getElseBranch() {	return elseBranch; }

	// Evaluate the condition and choose one branch to execute
	public void execute(HashMap<String,Integer> memory) {
		if (getCond().evaluate(memory)!=0)
			getThenBranch().execute(memory);
		else
			getElseBranch().execute(memory);
	}
}
