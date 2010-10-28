// (C) 2009-10 Ralf Laemmel

package idioms.errorhandling;


/**
 * Read two ints from the command line and print the sum.
 * Do some checking on the return value of the parsing method for error handling.
 * We use a mock-up for java.lang.Integer.parseInt to have it return null on error.
 * This "solution" is listed here for illustrative purposes only.
 */
public class NullBasedErrorHandling {

	public static void main(String[] args) {
		        
        if (args.length != 2) {
        	System.err.println("Incorrect number of arguments");
            System.exit(-1);
        }

        Integer value1 = IntegerWithNullResult.parseInt(args[0]);
        boolean error = value1 == null;
        Integer value2 = IntegerWithNullResult.parseInt(args[1]);
        error |= value2 == null;
        if (!error)  {
            int sum = value1 + value2;
            System.out.println(value1 + " + " + value2 + " = " + sum);
        }
        else {
        	System.err.println("Argument(s) not a number");
            System.exit(-1);        	
        }
	}
}
