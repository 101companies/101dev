// (C) 2009 Ralf Laemmel

package algorithm.sqrt;

/**
 * 
 * Compute square root based on Newton's method.
 * http://en.wikipedia.org/wiki/Newton's_method
 * 
 */
public class Newton {

	public static double sqrt(double x, double epsilon) {
		if (x < 0)
			return Double.NaN;
		double xn = x;
		double xn1;
		while (true) {
			xn1 = xn - (xn * xn - x) / (2 * xn);
			double delta = xn1 - xn;
			xn = xn1;
			if (Math.abs(delta) < epsilon)
				break;
		}
		return xn1;
	}

	public static void main(String[] args) {
		System.out.println("sqrt(9) = " + sqrt(9, 1e-14));
	}
}
