// (C) 2009 Ralf Laemmel

package algorithm.sum;

/**
 * Compute sum of n first natural numbers.
 * We use the straightforward recursive implementation.
 */
public class PrimitiveRecursive {

	public static int sum(int n) {
		if (n<1)
			return 0;
		else
			return n + sum(n-1);
	}
	
	public static void main(String[] args) {
		System.out.println(sum(10));
	}

}
