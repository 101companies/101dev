// (C) 2009 Ralf Laemmel

package algorithm.sum;

/**
 * Compute sum of n first natural numbers.
 * We use the Gaussian closed form.
 */
public class Gauss {

	public static int sum(int n) {
		return n/2 * (n+1);
	}
	
	public static void main(String[] args) {
		System.out.println(sum(1));
		System.out.println(sum(2));
		System.out.println(sum(3));
		System.out.println(sum(4));
		System.out.println(sum(5));
		System.out.println(sum(6));
		System.out.println(sum(7));
		System.out.println(sum(8));
		System.out.println(sum(9));
		System.out.println(sum(10));
	}

}
