// (C) 2009 Ralf Laemmel

package algorithm.add;

/**
 * Define addition for int (or rather nat) in an end-recursive manner. 
 * We do not use general addition of course.
 * We only use the predecessor and successor functions.
 */
public class EndRecursive {

	public static int add(int m, int n) {
		return m==0 ? n : add(m-1,n+1);
	}
	
	public static void main(String[] args) {
		System.out.println(add(3,4)); // prints 7
	}

}
