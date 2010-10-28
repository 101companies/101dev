// (C) 2009 Ralf Laemmel

package algorithm.fibonacci;

/*
 * Compute nth element of Fibonacci sequence.
 * We show a naive recursive solution.
 */
public class RecursiveNaive {

	// Assume n>=0
	public static int fib(int n) {
		if (n == 0)
			return 0;
		else if (n == 1)
			return 1;
		else
			return fib(n - 1) + fib(n - 2);
	}

	// Assume n>=0
	public static int fib2(int n) {
		return n == 0 ? 0 : n == 1 ? 1 : fib2(n - 1) + fib2(n - 2);
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++)
			System.out.println(fib(i) + ", " + fib2(i));
	}

}
