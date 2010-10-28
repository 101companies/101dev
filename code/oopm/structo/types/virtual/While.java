// (C) 2009 Ralf Laemmel

package structo.types.virtual;

import java.util.HashMap;

public class While extends Statement {
	private Expression cond;
	private Statement body;
	public While(Expression cond, Statement body) {
		this.cond = cond;
		this.body = body;
	}
	public Expression getCond() { return cond; }
	public Statement getBody() { return body; }

	// Execute the body as long as the condition evaluates != 0
	public void execute(HashMap<String,Integer> memory) {
		do {
			int v = getCond().evaluate(memory);
			if (v==0) break;
			getBody().execute(memory);
		} while (true);
	}
}
