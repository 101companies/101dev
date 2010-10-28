// (C) 2008 Ralf Laemmel

package algorithm.mcCarthy91;

/**
 * Compute the McCarty 91 function
 */
public class Program {

	/*
	 * The function returns 91 for all integer arguments n <= 101.
	 * It returns n - 10 for n > 101.
	 */
	public static int f(int n) {
		if (n > 100)
			return n - 10;
		else
			return f(f(n+11));
	}
	
	public static void main(String[] args) {
		for (int i = -1000; i <= 101; i++)
			System.out.println(f(i));
	}
	
}
