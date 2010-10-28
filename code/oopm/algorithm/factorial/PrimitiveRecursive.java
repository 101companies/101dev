// (C) 2009 Ralf Laemmel

package algorithm.factorial;

/**
 * Compute factorial
 */
public class PrimitiveRecursive {

	// Assume n >= 0
	public static int factorial(int n) {
		if (n == 0)
			return 1;
		else
			return n * factorial(n-1);
	}

	// Assume n >= 0
	public static int factorial2(int n) {
		return (n == 0) ?
				1
			: 	n * factorial(n-1);
	}	
	
	public static void main(String[] args) {
		System.out.println(factorial(5)); // prints 120
	}

}
