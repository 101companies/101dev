// (C) 2009 Ralf Laemmel

package structo.types.virtual;

import java.util.HashMap;

public class Declare extends Statement {
	private String id;
	public Declare(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}

	// A declaration has no runtime effect
	public void execute(HashMap<String,Integer> memory) {
	}
}
