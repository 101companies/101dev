// (C) 2009 Ralf Laemmel

package algorithm.evenodd;

/**
 * Determine whether a given number is even or odd.
 * Only subtraction and equality comparison is admitted.
 * That is, division and modulo operations are not admitted.
 * 
 * Mutually recursive definition of even and odd.
 */
public class MutualRecursive {

	// Assume n >= 0
	public static boolean even(int n) {
		if (n==0)
			return true;
		else
			return odd(n-1);
	}

	// Assume n >= 0
	public static boolean odd(int n) {
		if (n==0)
			return false;
		else
			return even(n-1);		
	}

	// Assume n >= 0
	public static boolean even2(int n) {
		return (n==0) || odd2(n-1);
	}

	// Assume n >= 0
	public static boolean odd2(int n) {
		return !(n==0) && even2(n-1);		
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
