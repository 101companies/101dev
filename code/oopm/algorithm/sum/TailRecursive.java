// (C) 2009 Ralf Laemmel

package algorithm.sum;

/**
 * Compute sum of n first natural numbers.
 * We use a tail-recursive implementation.
 */
public class TailRecursive {

	public static int sum(int n) {
		return sum(0,n);
	}
	
	public static int sum(int x, int n) {
		if (n==0)
			return x;
		else
			return sum(x+n,n-1);
	}
		
	public static void main(String[] args) {
		System.out.println(sum(10));
	}

}
