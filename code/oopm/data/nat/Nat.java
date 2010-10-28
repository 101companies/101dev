package data.nat;

public interface Nat {
	Nat add(Nat m);
	Nat mult(Nat m);
	Nat factorial();
	int toInt();
}
