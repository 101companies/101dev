// (C) 2009 Ralf Laemmel

package structo.types.pure;

public class Id extends Expression {
	private String value;
	public Id(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}
}
