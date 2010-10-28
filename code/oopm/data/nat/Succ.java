package data.nat;

public class Succ implements Nat {
	private Nat n;
	public Succ(Nat n) { this.n = n; }
	public Nat add(Nat m) { return new Succ(n.add(m)); }
	public Nat mult(Nat m) { return m.add(n.mult(m)); }
	public Nat factorial() { return mult(n.factorial()); }
	public int toInt() { return n.toInt()+1; }
	public String toString() { return toInt() + ""; }
}
