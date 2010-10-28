// (C) 2009 Ralf Laemmel

package algorithm.factorial;

/**
 * Compute factorial
 */
public class BoehmJacopini2 {

	public static int factorial(int n) {
		int r = 1;
		int v = 1;
		while (true)
			switch (v) {
			case 1: // start
				if (n == 0)
					v = 2; // goto end
				else {
					r = r * n;
					n = n - 1;
					v = 1; // goto start
				}
				break;
			case 2: // end
				return r;
			}
	}
	
	public static void main(String[] args) {
		System.out.println(factorial(5)); // prints 120
	}

}
