// (C) 2009-10 Ralf Laemmel

package idioms.errorhandling;

/**
 * A mock-up for a revised java.lang.Integer.
 * It turns off the exception throwing of parseInt.
 * However, it keeps track of the error in a corresponding flag.
 * This class is for illustrative purposes only.
 */
public class IntegerWithFlag {

	public static boolean error = false;
	
	public static int parseInt(String s) {
		int value = 0;
		try {
        	value = IntegerWithFlag.parseInt(s);
        	error = false;
        }
        catch (NumberFormatException e) {
        	error = true;
        }
        return value;
	}
}
