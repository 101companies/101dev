// (C) 2009 Ralf Laemmel

package algorithm.mod;

/**
 * Compute remainder of division
 */
public class Iterative {

	// Requires x >= 0 and y > 0
	public static int mod(int x, int y) {
		int q = 0;
		int r = x;
		while (r >= y) {
			r = r - y;
			q = q + 1;
		}
		return r;
	}
	
	public static void main(String[] args) {
		System.out.println(mod(22,7)); // prints 1
	}

}
