// (C) 2009--10 Ralf Laemmel

package uml.state.switchCase;

public class Program {
	public static void main(String[] args) {
		Turnstile t = new Turnstile();
		t.pass();
		t.ticket();
		t.pass();
	}

}