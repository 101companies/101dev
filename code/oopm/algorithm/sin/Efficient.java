// (C) 2009 Ralf Laemmel

package algorithm.sin;

/**
 * Compute sine function by a Taylor series.
 * http://en.wikipedia.org/wiki/Trigonometric_function
 */
public class Efficient {

	public static double MAX_ERROR = 10e-7;
		
	public static double sin(double x) {
		double x2 = x * x;
		double delta = x;
		double result = x;
		int i = 1;
		while (Math.abs(delta) > MAX_ERROR) {
			delta = -delta * x2 / ((i+1)*(i+2));
			result = result + delta;
			i = i + 2;
		}
		return result;
	}
	
	public static void main(String[] args) {	
		System.out.println(Math.sin(3.1415/2));		
		System.out.println(sin(3.1415/2));
	}
}
