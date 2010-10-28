// (C) 2009 Ralf Laemmel

package algorithm.sorting;

import java.util.Random;

/**
 * Implement simple sorting algorithms as well as some tools. This development,
 * without loss of generality, uses arrays of ints.
 */
public class ArrayBased {

	/*
	 * Test an array to be sorted (in ascending order)
	 */
	public static boolean isSorted(int[] a) {
		for (int i = 1; i < a.length; i++)
			if (a[i - 1] > a[i])
				return false; // inversion found
		return true; // no inversion found
	}

	// Test one array to be a permutation of another array
	// Assume all elements of the arrays to be distinct
	public static boolean isPermutation(int[] a, int[] b) {
		if (a.length != b.length)
			return false;
		for (int x : a) {
			boolean found = false;
			for (int y : b)
				if (x == y) {
					found = true;
					break;
				}
			if (!found)
				return false;
		}
		return true;
	}

	// Test one array to be a permutation of another array
	// Assume all elements of the arrays to be distinct
	public static boolean isPermutation2(int[] a, int[] b) {
		if (a.length != b.length)
			return false;
		l1: for (int x : a) {
			for (int y : b)
				if (x == y)
					continue l1;
			return false;
		}
		return true;
	}

	// Test one array to be a permutation of another array
	public static boolean isPermutation3(int[] a, int[] b) {
		if (a.length != b.length)
			return false;
		boolean[] c = new boolean[a.length];
		for (int i = 0; i < c.length; i++)
			c[i] = false;
		l1: for (int x : a) {
			for (int i = 0; i < b.length; i++)
				if (x == b[i] && !c[i]) {
					c[i] = true;
					continue l1;
				}
			return false;
		}
		for (boolean y : c)
			if (!y)
				return false;
		return true;
	}

	// Generate a field drawing values from argument
	public static int[] generateField(int[] a) {
		int[] b = new int[a.length];
		Random r = new Random();
		for (int i = 0; i < b.length; i++)
			b[i] = a[r.nextInt(a.length)];
		return b;
	}

	public static int[] sort(int[] a) {
		int[] b;
		while (true) {
			b = generateField(a);
			if (isSorted(b) && isPermutation3(a, b))
				break;
		}
		return b;
	}

	// Generate a field as a permutation of argument
	public static int[] generateField2(int[] a) {
		int[] b = new int[a.length];
		boolean[] c = new boolean[a.length];
		for (int i = 0; i < c.length; i++)
			c[i] = false;
		Random r = new Random();
		for (int i = 0; i < b.length; i++) {
			int j;
			while (true) {
				j = r.nextInt(a.length);
				if (c[j] == false) {
					c[j] = true;
					break;
				}
			}
			b[i] = a[j];
		}
		return b;
	}

	public static int[] sort2(int[] a) {
		int[] b;
		while (true) {
			b = generateField2(a);
			if (isSorted(b) && isPermutation3(a, b))
				break;
		}
		return b;
	}

	/*
	 * Sort an array by (simple) "BubbleSort". That is, have a nested loop over
	 * the array, compare all pairs of elements and swap them, if needed, where
	 * the loop terminates once all inversions have bubbled up.
	 */
	public static void bubbleSort1(int[] a) {
		int[] b = a.clone();
		boolean swapped; // to notice swaps during a pass
		do {
			swapped = false;
			for (int i = 1; i < a.length; i++)
				if (a[i - 1] > a[i]) {
					// Swap!
					int swap = a[i];
					a[i] = a[i - 1];
					a[i - 1] = swap;
					swapped = true;
				}
		} while (swapped); // another pass if swaps happened
		assert isSorted(a) && isPermutation3(a,b);
	}

	/**
	 * A variation on BubbleSort
	 */
	public static void bubbleSort2(int[] a) {
		l1: while (true) {
			for (int i = 1; i < a.length; i++)
				if (a[i - 1] > a[i]) {
					int swap = a[i];
					a[i] = a[i - 1];
					a[i - 1] = swap;
					continue l1;
				}
			break;
		}
	}

	/*
	 * Sort an array by "InsertionSort". That is, iterate over array from left
	 * to right, and insert the element at hand in the appropriate position,
	 * where insertion requires shifting all greater elements to the right.
	 */
	public static void insertionSort(int[] a) {
		for (int i = 1; i < a.length; i++) {
			int x = a[i]; // Element to be inserted
			// Insert elements in their prefix
			// Shift right elements if necessary
			int j = i;
			while (j > 0 && a[j - 1] > x) {
				a[j] = a[j - 1];
				a[j - 1] = x;
				j--;
			}
		}
	}

	/*
	 * Determine index of least element. This is an essential part of
	 * SelectionSort. If elements can be recurring, some representative is
	 * chosen. If the array is of zero-length, then return -1.
	 */
	public static int indexLeast(int[] a) {
		if (a.length == 0)
			return -1; // zero-length arrays have no least element
		int least = 0;
		for (int i = 1; i < a.length; i++)
			if (a[i] < a[least])
				least = i;
		return least;
	}

	/*
	 * Sort an array by "SelectionSort". That is, construct the sorted result
	 * from left to right. In each iteration, find the least element among those
	 * remaining. Elements must be shifted to the right to make space for each
	 * new element.
	 */
	public static void selectionSort(int[] a) {
		for (int i = 0; i < a.length; i++) {
			// Find least element among the remaining ones
			int least = i;
			for (int j = i + 1; j < a.length; j++)
				if (a[j] < a[least])
					least = j;
			// Swap current element with least one
			if (least != i) {
				int swap = a[i];
				a[i] = a[least];
				a[least] = swap;
			}
		}
	}

	/*
	 * Sort an array by "MergeSort".
	 */
	public static void mergeSort(int[] a) {
		int[] temp = new int[a.length];
		mergeSort(a, temp, 0, a.length - 1);
	}

	public static void mergeSort(int[] a, int[] temp, int min, int max) {
		// Cease on trivial sorting problem
		if (!(min < max))
			return;

		// Divide
		int middle = (min + max) / 2;

		// Solve smaller problems recursively
		mergeSort(a, temp, min, middle);
		mergeSort(a, temp, middle + 1, max);

		// Merge via temporary array
		merge(a, temp, min, middle, max);
	}

	/*
	 * Merge two sorted parts of an array while using a temporary array
	 */
	public static void merge(int[] a, int[] temp, int min, int middle, int max) {
		int i = min; // loop over left half
		int j = middle + 1; // loop over right half
		int k = min; // loop over merged result
		while (k <= max)
			temp[k++] = (i <= middle && (j > max || a[i] < a[j])) ? a[i++] // copy
																			// from
																			// left
																			// half
					: a[j++]; // copy from right half

		// Commit temporary result
		for (k = min; k <= max; k++)
			a[k] = temp[k];
	}

	// For local access to an external function
	private static int[] clone(int[] a) {
		return data.vector.Program.clone(a);
	}

	// For local access to an external function
	private static boolean equal(int[] a, int[] b) {
		return data.vector.Program.equal(a, b);
	}

	public static void main(String[] args) {

		int[] a = { 3, 2, 1 };
		int[] b = a.clone();
		bubbleSort1(b);
		assert isSorted(b) && isPermutation(a, b);

		// Test data
		int[] sorted0 = new int[0];
		int[] unsorted0 = new int[0];
		int[] sorted1 = { 42 }; // array of length 1
		int[] unsorted1 = { 42 }; // array of length 1
		int[] sorted2 = { 1, 2 }; // sorted array of length 2
		int[] unsorted2 = { 2, 1 }; // unsorted array of length 2
		int[] sorted3 = { 1, 2, 3 }; // sorted array of length 3
		int[] unsorted3a = { 3, 2, 1 }; // unsorted array of length 3
		int[] unsorted3b = { 2, 3, 1 }; // (ya) unsorted array of length 3
		int[] unsorted3c = { 1, 3, 2 }; // (and ya) unsorted array of length 3

		// Testing: all test should compute true
		boolean result = true;

		// Testing isSorted
		result &= isSorted(sorted0) && isSorted(unsorted0) && isSorted(sorted1)
				&& isSorted(unsorted1) && isSorted(sorted2)
				&& !isSorted(unsorted2) && isSorted(sorted3)
				&& !isSorted(unsorted3a) && !isSorted(unsorted3b)
				&& !isSorted(unsorted3c);

		// Reference for clones for in-place sorting
		int[] x;

		// Testing BubbleSort
		x = clone(unsorted0);
		bubbleSort1(x);
		result &= equal(x, sorted0);
		x = clone(unsorted1);
		bubbleSort1(x);
		result &= equal(x, sorted1);
		x = clone(unsorted2);
		bubbleSort1(x);
		result &= equal(x, sorted2);
		x = clone(unsorted3a);
		bubbleSort1(x);
		result &= equal(x, sorted3);
		x = clone(unsorted3b);
		bubbleSort1(x);
		result &= equal(x, sorted3);
		x = clone(unsorted3c);
		bubbleSort1(x);
		result &= equal(x, sorted3);

		// Testing InsertionSort
		x = clone(unsorted0);
		insertionSort(x);
		result &= equal(x, sorted0);
		x = clone(unsorted1);
		insertionSort(x);
		result &= equal(x, sorted1);
		x = clone(unsorted2);
		insertionSort(x);
		result &= equal(x, sorted2);
		x = clone(unsorted3a);
		insertionSort(x);
		result &= equal(x, sorted3);
		x = clone(unsorted3b);
		insertionSort(x);
		result &= equal(x, sorted3);
		x = clone(unsorted3c);
		insertionSort(x);
		result &= equal(x, sorted3);

		// Testing SelectionSort
		x = clone(unsorted0);
		selectionSort(x);
		result &= equal(x, sorted0);
		x = clone(unsorted1);
		selectionSort(x);
		result &= equal(x, sorted1);
		x = clone(unsorted2);
		selectionSort(x);
		result &= equal(x, sorted2);
		x = clone(unsorted3a);
		selectionSort(x);
		result &= equal(x, sorted3);
		x = clone(unsorted3b);
		selectionSort(x);
		result &= equal(x, sorted3);
		x = clone(unsorted3c);
		selectionSort(x);
		result &= equal(x, sorted3);

		// Testing MergeSort
		x = clone(unsorted0);
		mergeSort(x);
		result &= equal(x, sorted0);
		x = clone(unsorted1);
		mergeSort(x);
		result &= equal(x, sorted1);
		x = clone(unsorted2);
		mergeSort(x);
		result &= equal(x, sorted2);
		x = clone(unsorted3a);
		mergeSort(x);
		result &= equal(x, sorted3);
		x = clone(unsorted3b);
		mergeSort(x);
		result &= equal(x, sorted3);
		x = clone(unsorted3c);
		mergeSort(x);
		result &= equal(x, sorted3);

		// Better be true
		System.out.println(result);

		// Testing inefficient sorting
		int[] input8 = { 1, 6, 4, 7, 3, 8, 5, 2 };
		// int[] input9 = {1,6,4,7,3,8,9,5,2};
		int[] input11 = { 1, 10, 6, 4, 7, 3, 11, 8, 9, 5, 2 };
		data.vector.Program.print(sort(input8));
		data.vector.Program.print(sort2(input11));
	}
}
