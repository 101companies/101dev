// (C) 2009 Ralf Laemmel

package algorithm.evenodd;

/**
 * Determine whether a given number is even or odd.
 * Only subtraction and equality comparison is admitted.
 * That is, division and modulo operations are not admitted.
 * 
 * Iterative definition of even and odd.
 */
public class Iterative {

	// Assume n >= 0
	public static boolean even(int n) {
		boolean result = true;
		for (; n>0; n--)
			result = !result;
		return result;
	}

	public static boolean even2(int n) {
		while (!(n<=1))
			n -= 2;
		return n==0;
	}
	
	// Assume n >= 0
	public static boolean odd(int n) {
		boolean result = false;
		for (; n>0; n--)
			result = !result;
		return result;
	}

	public static void main(String[] args) {
		System.out.println(even(0)); 	// prints true
		System.out.println(even(1)); 	// prints false
		System.out.println(even(2)); 	// prints true
		System.out.println(odd(0));  	// prints false
		System.out.println(odd(1));  	// prints true
		System.out.println(odd(2));  	// prints false
	}

}
