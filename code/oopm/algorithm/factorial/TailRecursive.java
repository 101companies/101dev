// (C) 2009 Ralf Laemmel

package algorithm.factorial;

/**
 * Compute factorial
 */
public class TailRecursive {

	// Assume n >= 0
	public static int factorial(int n) {
		return factorial(1,n);
	}

	private static int factorial(int x, int n) {
		return (n==0) ? x : factorial(n*x,n-1);
	}	
	
	public static void main(String[] args) {
		System.out.println(factorial(5)); // prints 120
	}

}
