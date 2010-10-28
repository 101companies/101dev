// (C) 2009 Ralf Laemmel

package algorithm.sin;

/**
 * Compute sine function by a Taylor series.
 * http://en.wikipedia.org/wiki/Trigonometric_function
 */
public class Inefficient {

	public static double MAX_ERROR = 10e-7;
	
	public static double power(double x, int k) {
		double result = 1;
		for (int i=1; i<=k; i++)
			result *= x;
		return result;
	}

	public static long power(int x, int k) {
		long result = 1;
		for (int i=1; i<=k; i++)
			result *= x;
		return result;
	}	
	
	public static long factorial(int n) {
		long result = 1;
		while (n > 1) 
			result *= n--;
		return result;
	}
	
	public static double sin1(double x) {
		double result = 0;
		int n = 0;
		double delta;
		do {
			delta = power(-1,n) * power(x,2*n + 1) / factorial(2*n + 1);
			result += delta;
			n++;
		} while (Math.abs(delta) > MAX_ERROR);
		return result;
	}

	// Variation using Break for mini improvement
	public static double sin2(double x) {
		double result = 0;
		int n = 0;
		while (true) {
			double delta = power(-1,n) * power(x,2*n + 1) / factorial(2*n + 1);
			result += delta;
			if (Math.abs(delta) < MAX_ERROR)
				break;
			n++;
		}
		return result;
	}
		
	public static void main(String[] args) {	       
		System.out.println(Math.sin(3.1415/2));
		System.out.println(sin1(3.1415/2));
		System.out.println(sin2(3.1415/2));
	}
}
