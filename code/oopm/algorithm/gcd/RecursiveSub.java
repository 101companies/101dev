// (C) 2009 Ralf Laemmel

package algorithm.gcd;

/**
 * Compute greatest common denominator
 */
public class RecursiveSub {

	// Assume x!=0 || y!=0
	public static int gcd(int x, int y) {
		if (x==y) 
			return x;
		else
			if (x > y) 
				return gcd(x - y,y); 
	        else 
				return gcd(x,y - x); 
	}
	
	public static void main(String[] args) {
		System.out.println(gcd(15,6)); // prints 3
	}

}
