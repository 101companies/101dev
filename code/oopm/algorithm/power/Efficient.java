// (C) 2009 Ralf Laemmel

/**
 * Fast exponentiation; exponentiation by squaring
 * http://en.wikipedia.org/wiki/Exponentiation_by_squaring
 */

package algorithm.power;

public class Efficient {

	public static int power(int x, int n) {
		int k = n;
		int p = x;
		int y = 1;
		while (k>0) 
			if (k % 2 == 0) {
				p = p * p;
				k = k / 2;
			}
			else {
				y = y * p;
				k = k - 1;
			}
		return y;
	}
	
	public static void main(String[] args) {
		System.out.println(power(2,1)); // prints 2
		System.out.println(power(2,2)); // prints 4
		System.out.println(power(2,3)); // prints 8
		System.out.println(power(2,4)); // prints 16
		System.out.println(power(3,1)); // prints 3
		System.out.println(power(3,2)); // prints 9
		System.out.println(power(3,3)); // prints 27
		System.out.println(power(3,4)); // prints 81
	}

}
