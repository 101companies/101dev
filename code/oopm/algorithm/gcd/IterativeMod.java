// (C) 2009 Ralf Laemmel

package algorithm.gcd;

/**
 * Compute greatest common denominator
 */
public class IterativeMod {

	public static int gcd(int x, int y) {
		while (true) { 
			if (x < y) {
				int t = x;
				x = y;
				y = t; 
			}
//			int q = x / y;
		    int r = x % y;
		    if (r == 0)
		    	return y;
		    else
		    	x = r;
	    }
	}
	
	public static void main(String[] args) {
		System.out.println(gcd(15,6)); // prints 3
	}

}
