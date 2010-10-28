// (C) 2009 Ralf Laemmel

package algorithm.sum;

/**
 * Compute sum of n first natural numbers.
 * We use the straightforward iterative implementation.
 */
public class Iterative {

	public static int sum(int n) {
		int result = 0;
		while (n>0)
			result += n--;
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(sum(10));
	}

}
