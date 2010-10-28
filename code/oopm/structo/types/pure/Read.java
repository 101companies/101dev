// (C) 2009 Ralf Laemmel

package structo.types.pure;

public class Read extends Statement {
	private String id;
	public Read(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
}
