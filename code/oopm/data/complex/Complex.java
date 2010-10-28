// (C) 2009 Ralf Laemmel

package data.complex;

public interface Complex {
	double getRe();
	double getIm();
	double getModulus();
	double getRadianAngle();
	double getAngle();
	Complex addTo(Complex c);
	// Further operations omitted
}
