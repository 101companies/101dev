// (C) 2009 Ralf Laemmel

package data.complex;

/**
 * The data type of complex numbers. 
 * The representation uses the polar form.
 */
public class ComplexPolar implements Complex {

	// Store modulus
	private double modulus;

	// Store angle
	private double angle;

	/**
	 * Construct a complex number from real and imaginary parts
	 */
	public ComplexPolar(double modulus, double angle) {
		this.modulus = modulus;
		this.angle = angle;
	}

	/**
	 * Construct a complex number from real and imaginary parts
	 */
	public static ComplexPolar fromCartesian(double re, double im) {
		return new ComplexPolar(
				Math.hypot(re, im),
				Math.atan2(im, re));
	}

	/**
	 * Getter for real part
	 */
	public double getRe() {
		return modulus * Math.cos(angle);
	}

	/**
	 * Getter for imaginary part
	 */
	public double getIm() {
		return modulus * Math.sin(angle);
	}

	/**
	 * Getter for norm of polar form
	 */
	public double getModulus() {
		return modulus;
	}

	/**
	 * Getter for (radian) angle of polar form
	 */
	public double getRadianAngle() {
		return angle;
	}

	/**
	 * Getter for angle of polar form
	 */
	public double getAngle() {
		return getRadianAngle() * 180 / Math.PI;
	}

	public Complex addTo(Complex c) {
		return fromCartesian(this.getRe() + c.getRe(), this.getIm() + c.getIm());
	}

	public String toString() {
		String result = "Complex(";
		result += Double.toString(getRe());
		result += ",";
		result += Double.toString(getIm());
		result += ")";
		return result;
	}

	public static void main(String[] args) {
		ComplexPolar a = fromCartesian(1, 2);
		System.out.println(a); // prints Complex(1,2)
		ComplexPolar b = fromCartesian(2, 2);
		System.out.println(b); // prints Complex(2,2)
		Complex c = a.addTo(b);
		System.out.println(c); // prints Complex(4,4)
		c = fromCartesian(3, 4);
		System.out.println(c); // prints Complex(4,4)
		System.out.println(c.getModulus()); // prints 5.0
		System.out.println(c.getAngle()); // prints 53.13...
		ComplexPolar d = fromCartesian(100, 100);
		System.out.println(d); // prints Complex(100,100)
		System.out.println(d.getModulus()); // prints 141.42...
		System.out.println(d.getAngle()); // prints 45.0
	}

}
