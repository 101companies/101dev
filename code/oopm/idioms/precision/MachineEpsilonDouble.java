// (C) 2009 Ralf Laemmel

package idioms.precision;

/**
 * Test doubles for precision. We actually compute the so-called machine epsilon.
 * http://en.wikipedia.org/wiki/Machine_epsilon The machine epsilon is, for a
 * particular floating-point type, the difference between 1 and the smallest
 * exactly representable number greater than one. It gives an upper bound on the
 * relative error due to rounding of floating point numbers.
 */
public class MachineEpsilonDouble {

	public static double macheps() {
		double r = 1.0;
		do
			r /= 2.0;
		while ((1.0 + (r / 2.0)) != 1.0);
		return r;
	}

	public static void main(String[] args) {
		System.out.println("Machine epsilon: " + macheps());
	}
}
