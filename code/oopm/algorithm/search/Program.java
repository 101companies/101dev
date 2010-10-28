// (C) 2009 Ralf Laemmel

package algorithm.search;

/**
 * Search algorithms on arrays
 */
public class Program {

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
		for (int i=0; i<a.length; i++)
			if (a[i] == x) 
				return i;
		return -1;
	}
	
	/*
	 * Find the index of x in an array a.
	 * Return -1 to mean that x was not found.
	 * For the sake of efficiency we implement a binary search.
	 * Hence, the array must be ordered.
	 */
	public static int binary(int[] a, int x) {
		int first = 0; // first index in range
		int last = a.length - 1; // last index in range

		while (first <= last) {
			int middle = first + ((last - first) / 2);
			if (a[middle] < x) {
				first = middle + 1; // go to the right
			} else if (a[middle] > x) {
				last = middle - 1; // go to the left
			} else
				return middle;
		}
		return -1; // not found
	}
	
	public static void main(String[] args) {
		int[] a = {1,2,3,4,5,6,7,9,10};
		System.out.println(linear(a,5));
		System.out.println(isSorted(a));
		System.out.println(binary(a,5));
	}

}
