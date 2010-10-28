// (C) 2009 Ralf Laemmel

package algorithm.factorial;

/**
 * Compute factorial
 */
public class While {

	public static int factorial(int n) {
		int r = 1;
		while (n > 0) {
			r = r * n;
			n = n - 1;
		}
		return r;
	}
	
	public static void main(String[] args) {
		System.out.println(factorial(5)); // prints 120
	}

}
