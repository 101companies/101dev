// (C) 2009 Ralf Laemmel

package structo.types.virtual;

import java.util.HashMap;

public class Assign extends Statement {
	private String lhs;
	private Expression rhs;
	public Assign(String lhs, Expression rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}
	public String getLhs() { return lhs; }
	public Expression getRhs() { return rhs; }
	
	// Evaluate RHS and assign the result to the LHS variable
	public void execute(HashMap<String,Integer> memory) {
		memory.put(getLhs(), getRhs().evaluate(memory));
	}
}
