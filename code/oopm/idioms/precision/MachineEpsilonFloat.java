// (C) 2009 Ralf Laemmel

package idioms.precision;

/**
 * Test floats for precision. We actually compute the so-called machine epsilon.
 * http://en.wikipedia.org/wiki/Machine_epsilon The machine epsilon is, for a
 * particular floating-point type, the difference between 1 and the smallest
 * exactly representable number greater than one. It gives an upper bound on the
 * relative error due to rounding of floating point numbers.
 */
public class MachineEpsilonFloat {

	public static float macheps() {
		float r = 1.0f;
		do
			r /= 2.0f;
		while ((1.0f + (r / 2.0f)) != 1.0f);
		return r;
	}

	public static void main(String[] args) {
		System.out.println("Machine epsilon: " + macheps());
	}
}
