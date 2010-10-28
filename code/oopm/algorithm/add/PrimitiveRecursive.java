// (C) 2009 Ralf Laemmel

package algorithm.add;

/**
 * Define addition for int (or rather nat) in a primitive recursive manner. 
 * We do not use general addition of course.
 * We only use the predecessor and successor functions.
 */
public class PrimitiveRecursive {

	/**
	 * @param m first operand of addition (non-negative)
	 * @param n second operand of addition
	 * @return the sum of the two operands
	 * @throws java.lang.StackOverflowError if m is negative
	 */
	public static int add(int m, int n) {
		return m==0 ? n : add(m-1,n)+1;
	}
	
	public static void main(String[] args) {
		System.out.println(add(3,4)); // prints 7
	}

}
