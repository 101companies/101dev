// (C) 2009 Ralf Laemmel

package structo.types.virtual;

import java.util.HashMap;

public abstract class Expression {
	public abstract int evaluate(HashMap<String, Integer> memory);
}
