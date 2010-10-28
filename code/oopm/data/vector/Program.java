// (C) 2008 Ralf Laemmel

package data.vector;

/**
 * Simple operations on arrays
 */
public class Program {

	/*
	 * Compute the sum of an integer array
	 */
	public static int sum(int[] a) {
		int result = 0;
		for (int i=0; i<a.length; i++) 
			result += a[i];
		return result;
	}

	/*
	 * Add operation on two arrays (vectors)
	 */
	public static int[] add(int[] v1, int[] v2) {
		int[] result = new int[v1.length];
		for (int i=0; i<v1.length; i++) 
			result[i] = v1[i] + v2[i];
		return result;
	}
	
	/*
	 * Test arrays for equality
	 */
	public static boolean equal(int[] a, int[] b) {
		if (a.length != b.length) return false;
		for (int i=0; i<a.length; i++)
			if (a[i]!=b[i])
				return false;
		return true;
	}
	
	/*
	 * Compute the product of an integer array
	 */
	public static int product(int[] a) {
		int result = 1;
		for (int x : a) result *= x;
		return result;
	}
	
	/*
	 * Reverse an array (impure)
	 */
	public static void impureReverse(int[] a) {
		int i = 0;
		int j = a.length - 1;
		while (i<j) {
			int t = a[i];
			a[i] = a[j];
			a[j] = t;
			i++;
			j--;
		}
	}	

	/*
	 * Reverse an array (pure)
	 */
	public static int[] pureReverse(int[] a) {
		int[] b = new int[a.length];
		int i = 0;
		int j = a.length - 1;
		while (i<a.length) {
			b[i] = a[j];
			i++;
			j--;
		}
		return b;
	}	

	public static int[] pureReverse2(int[] a) {
		int[] b = new int[a.length];
		int i = 0;
		int j = a.length - 1;
		while (i<a.length)
			b[i++] = a[j--];
		return b;
	}	
		
	/*
	 * Print an array
	 */
	public static void print(int[] a) {
		for (int i=0; i<a.length; i++)
			System.out.print(a[i] + " ");
		System.out.println();
	}
	
	public static void print2(int[] a) {
		for (int x : a) 
			System.out.print(x + " ");
		System.out.println();
	}	
	
	/*
	 * Clone an array
	 */
	public static int[] clone(int[] a) {
		int[] result = new int[a.length];
		for (int i=0; i<a.length; i++)
			result[i] = a[i];
		return result;
	}
	
	public static void main(String[] args) {
		int[] a = new int[] {1,2,3,4}; // initialization syntax
		int[] b = {}; // different initialization syntax w/o new
		System.out.println(sum(a));
		System.out.println(product(a));
		impureReverse(a);
		impureReverse(b);
		int[] c = pureReverse(a);
		print(a);
		print(b);
		print(c);
		c = pureReverse2(c);
		print(c);
	}

}
