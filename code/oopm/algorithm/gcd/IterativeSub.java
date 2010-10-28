// (C) 2009 Ralf Laemmel

package algorithm.gcd;

/**
 * Compute greatest common denominator
 */
public class IterativeSub {

	public static int gcd(int x, int y) {
		while (x != y) { 
			if (x > y) 
				x = x - y; 
	        else 
	        	y = y - x; 
	    }
	    return x;
	}
	
	public static void main(String[] args) {
		System.out.println(gcd(15,6)); // prints 3
	}

}
