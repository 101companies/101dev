// (C) 2009-10 Ralf Laemmel

package idioms.errorhandling;


/**
 * Read two ints from the command line and print the sum.
 * Do some checking on a global error flag for error handling.
 * Of course, an error flag is used for illustrative purposes only.
 */
public class FlagBasedErrorHandling {

	public static void main(String[] args) {
		        
        if (args.length != 2) {
        	System.err.println("Incorrect number of arguments");
            System.exit(-1);
        }

        int value1 = IntegerWithFlag.parseInt(args[0]);
        boolean error = IntegerWithFlag.error;
        int value2 = IntegerWithFlag.parseInt(args[1]);
        error |= IntegerWithFlag.error;
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
