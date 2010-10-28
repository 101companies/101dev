// (C) 2009 Ralf Laemmel

package algorithm.factorial;

/**
 * Compute factorial
 */
public class For {

	/**
	 * @param n non-negative operand
	 * @return factorial (1 * ... * n)
	 */
	public static int factorial(int n) {
		int r = 1;
		for (int i=1; i <= n; i++)
			r *= i;
		return r;
	}
	
	public static void main(String[] args) {
		System.out.println(factorial(5)); // prints 120
	}

}
