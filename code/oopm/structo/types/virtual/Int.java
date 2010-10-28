// (C) 2009 Ralf Laemmel

package structo.types.virtual;

import java.util.HashMap;

public class Int extends Expression {
	private int value;
	public Int(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}

	// The constant is the immediate result of evaluation
	public int evaluate(HashMap<String, Integer> memory) {
		return getValue();
	}
}
