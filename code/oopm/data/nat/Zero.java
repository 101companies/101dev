package data.nat;

public class Zero implements Nat {
	public Zero() { }
	public Nat add(Nat m) { return m; }
	public Nat mult(Nat m) { return new Zero(); }
	public Nat factorial() { return new Succ(new Zero()); }
	public int toInt() { return 0; }
	public String toString() { return toInt() + ""; }
}
