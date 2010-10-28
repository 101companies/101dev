// (C) 2008 Ralf Laemmel

package data.range;

public class Program
{
    public static void main(String args[]) {

    	// Create an initial range
    	FloatRange r1 = new FloatRange(1.0f,5.0f);

    	// If we get to this point, then construction has succeeded.
    	System.out.println("r1: valid construction succeeded.");

    	// Make a valid attempt to set the max
    	r1.setMax(4.0f);
        System.out.println("r1: valid setMax succeeded.");

        // Make an invalid attempt to set the min
        try {
            r1.setMin(6.0f);
            System.out.println("r1: invalid setMin succeeded.");
        }
        catch (AssertionError ae) {
            System.out.println("r1: invalid setMin failed.");
        }
    }
}
