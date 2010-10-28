// (C) 2009 Ralf Laemmel

package algorithm.fibonacci;

/*
 * Compute nth element of Fibonacci sequence.
 * We show an (efficient) iterative solution.
 */
public class Iterative {

	// Assume n>=0
	public static int fib(int n) {
		int n1 = 0;
		int n2 = 1;
		for (; n != 0; n--) {
			int m1 = n1;
			int m2 = n2;
			n1 = m2;
			n2 = m1 + m2;
		}
		return n1;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++)
			System.out.println(fib(i));
	}

}
