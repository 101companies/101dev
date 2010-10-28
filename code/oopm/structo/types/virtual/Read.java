// (C) 2009 Ralf Laemmel

package structo.types.virtual;

import java.util.HashMap;
import java.util.Scanner;

public class Read extends Statement {
	public static Scanner scanner;
	private String id;
	public Read(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}

	// Read a value via a given scanner and assign value to variable
	public void execute(HashMap<String,Integer> memory) {
		memory.put(getId(), scanner.nextInt());
	}
}
