// (C) 2009 Ralf Laemmel

package structo.types.pure;

public class Int extends Expression {
	private int value;
	public Int(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
}
