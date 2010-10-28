// (C) 2008 Ralf Laemmel

package algorithm.evenodd;

/**
 * Determine whether a given number is even or odd.
 * Only subtraction and equality comparison is admitted.
 * That is, division and modulo operations are not admitted.
 * 
 * Tail-recursive definition of even and odd.
 */
public class TailRecursive {

	// Assume n >= 0
	public static boolean even(int n) {
		return (n<=1) ? (n==0) : even(n-2);
	}

	// Assume n >= 0
	public static boolean odd(int n) {
		return (n<=1) ? (n==1) : odd(n-2);
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
