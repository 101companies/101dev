package data.nat;

public class Program {

	public static void main(String[] args) {
		Nat nat5 = new Succ(new Succ(new Succ(new Succ(new Succ(new Zero())))));
		System.out.println(nat5.factorial());
	}

}
