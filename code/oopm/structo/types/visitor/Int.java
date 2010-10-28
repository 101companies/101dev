// (C) 2009 Ralf Laemmel

package structo.types.visitor;

public class Int extends Expression {
	private int value;
	public Int(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
	public void accept(Visitor v) {
		v.visit(this);
	}
}
