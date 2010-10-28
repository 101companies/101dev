// (C) 2009 Ralf Laemmel

package data.complex;

/**
 * The data type of complex numbers.
 * The representation uses the Cartesian form.
 */
public class ComplexCartesian implements Complex {

	// Store real part
	private double re;

	// Store imaginary part
	private double im;

	/**
	 * Construct a complex number from real and imaginary parts
	 */
	public ComplexCartesian(double re, double im) {
		this.re = re;
		this.im = im;
	}

	/**
	 * Construct a complex number from polar form arguments
	 */
	public static ComplexCartesian fromPolar(double magnitude, double angle) {
		double re = magnitude * Math.cos(angle);
		double im = magnitude * Math.sin(angle);
		return new ComplexCartesian(re, im);
	}

	/**
	 * Getter for real part
	 */
	public double getRe() {
		return re;
	}

	/**
	 * Getter for imaginary part
	 */
	public double getIm() {
		return im;
	}

	/**
	 * Getter for norm of polar form
	 */
	public double getModulus() {
		return Math.hypot(re, im);
	}

	/**
	 * Getter for (radian) angle of polar form
	 */
	public double getRadianAngle() {
		return Math.atan2(im, re);
	}

	/**
	 * Getter for angle of polar form
	 */
	public double getAngle() {
		return getRadianAngle() * 180 / Math.PI;
	}

	public Complex addTo(Complex c) {
		return new ComplexCartesian(this.getRe() + c.getRe(), this.getIm()
				+ c.getIm());
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
		ComplexCartesian a = new ComplexCartesian(1, 2);
		System.out.println(a); // prints Complex(1,2)
		ComplexCartesian b = new ComplexCartesian(2, 2);
		System.out.println(b); // prints Complex(2,2)
		Complex c = a.addTo(b);
		System.out.println(c); // prints Complex(4,4)
		c = new ComplexCartesian(3, 4);
		System.out.println(c); // prints Complex(4,4)
		System.out.println(c.getModulus()); // prints 5.0
		System.out.println(c.getAngle()); // prints 53.13...
		ComplexCartesian d = new ComplexCartesian(100, 100);
		System.out.println(d); // prints Complex(100,100)
		System.out.println(d.getModulus()); // prints 141.42...
		System.out.println(d.getAngle()); // prints 45.0
	}

}
