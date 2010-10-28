// (C) 2009 Ralf Laemmel

package structo.types.virtual;

import java.util.HashMap;

public abstract class Statement {
	public abstract void execute(HashMap<String,Integer> memory);
}
