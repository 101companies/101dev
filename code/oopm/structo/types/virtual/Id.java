// (C) 2009 Ralf Laemmel

package structo.types.virtual;

import java.util.HashMap;

public class Id extends Expression {
	private String value;
	public Id(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}

	// The variable is looked up in the memory
	public int evaluate(HashMap<String, Integer> memory) {
		return memory.get(getValue());
	}
}
