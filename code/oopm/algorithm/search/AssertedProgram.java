// (C) 2009 Ralf Laemmel

package algorithm.search;

/**
 * Search algorithms on arrays
 */
public class AssertedProgram {

	/**
	 * @param a an array to search for x
	 * @param x the element to search in a
	 * @param from the start index of a for the window of searching
	 * @param to the end index of a for the window of searching
	 * @return Boolean to say whether x is a member of a
	 */
	public static boolean member(int[] a, int x, int from, int to) {
		for (int i=from; i<=to; i++)
			if (a[i]==x) 
				return true;
		return false;
	}
	
	/*
	 * Test an array to be sorted (in ascending order)
	 */
	public static boolean isSorted(int[] a) {
		for (int i=1; i<a.length; i++)
			if (a[i-1] > a[i])
				return false;
		return true;
	}

	/*
	 * Find the index of x in an array a.
	 * Return -1 to mean that x was not found.
	 * We implement a simple linear search.
	 * Hence, the array does not need to be order.
	 * In return, runtime complexity the worst.
	 */
	public static int linear(int[] a, int x) {
		for (int i=0; i<a.length; i++) {
			assert !member(a,x,0,i-1);
			if (a[i] == x) {
				assert member(a,x,i,i);		
				assert member(a,x,0,a.length-1);				
				return i;
			}
			assert !member(a,x,0,i);
		}
		assert !member(a,x,0,a.length-1);
		return -1;
	}
	
	/*
	 * Find the index of x in an array a.
	 * Return -1 to mean that x was not found.
	 * For the sake of efficiency we implement a binary search.
	 * Hence, the array must be ordered.
	 * The assertions further assume that all elements are distinct.
	 */
	public static int binary(int[] a, int x) {
		int first = 0; // first index in range
		int last = a.length - 1; // last index in range

		while (first <= last) {
			int middle = first + ((last - first) / 2);
			if (a[middle] < x) {
				assert !member(a,x,first,middle);
				first = middle + 1; // go to the right
			} else if (a[middle] > x) {
				assert !member(a,x,middle,last);
				last = middle - 1; // go to the left
			} else
				return middle;
		}
		assert !member(a,x,0,a.length-1);
		return -1; // not found
	}
	
	public static void main(String[] args) {
		int[] a = {1,2,3,4,5,6,7,9,10};
		System.out.println(linear(a,5));
		System.out.println(linear(a,8));
		System.out.println(isSorted(a));
		System.out.println(binary(a,5));
		System.out.println(binary(a,8));
	}

}
