// (C) 2009 Ralf Laemmel

package algorithm.gcd;

/**
 * Compute greatest common denominator
 */
public class RecursiveMod {

	//
	// Assume x!=0 || y!=0
	// The formulation exploits the law that gcd(x,y) = gcd(y,x % y)
	//
	public static int gcd(int x, int y) {
		if (x==0) return y;
		if (y==0) return x;
		return gcd(y,x % y);
	}
	
	public static void main(String[] args) {
		System.out.println(gcd(15,6)); // prints 3
	}

}
