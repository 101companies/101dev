// (C) 2009 Ralf Laemmel

package algorithm.sqrt;

/**
 * 
 * Compute square root based on divide-and-average method.
 * http://mathforum.org/library/drmath/view/52644.html
 * http://mathforum.org/dr.math/faq/faq.sqrt.by.hand.html
 * 
 */
public class DivideAndAverage {

	public static double sqrt(double x, double epsilon) {
		if (x < 0)
			return Double.NaN;
		double r = x;
		while (Math.abs(x - r * r) > epsilon)
			r = (x / r + r) / 2.0;
		return r;
	}

	public static void main(String[] args) {
		System.out.println("sqrt(9) = " + sqrt(9, 1e-14));
	}
}
