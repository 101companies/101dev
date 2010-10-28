// (C) 2009 Ralf Laemmel

package algorithm.fibonacci;

/*
 * Compute nth element of Fibonacci sequence.
 * We show an efficient recursive solution.
 * It is inspired by the iterative approach to the problem.
 */
public class RecursiveEfficient {

	// Assume n>=0
	public static int fib(int n) {
		return fib(n,0,1);
    }
	
	// Helper function invoked with appropriate initial values
	public static int fib(int n, int n1, int n2) {
        return n == 0 ? n1 : fib(n-1, n2, n1 + n2);
	}
	
	public static void main(String[] args) {
		for (int i=0; i<10; i++)
			System.out.println(fib(i));
	}
	
}
