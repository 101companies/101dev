// (C) 2009 Ralf Laemmel

package structo.types.pure;

public class Declare extends Statement {
	private String id;
	public Declare(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
}
