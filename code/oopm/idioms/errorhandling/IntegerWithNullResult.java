// (C) 2009-10 Ralf Laemmel

package idioms.errorhandling;

/**
 * A mock-up for a revised java.lang.Integer.
 * It turns off the exception throwing of parseInt.
 * However, it communicates the error by the special result null.
 * To this end, the method needs to return an Integer instead of an int.
 * This class is for illustrative purposes only.
 */
public class IntegerWithNullResult {
	
	public static java.lang.Integer parseInt(String s) {
		try {
        	int value = java.lang.Integer.parseInt(s);
	        return value;
        }
        catch (NumberFormatException e) {
        	return null;
        }
	}
}
