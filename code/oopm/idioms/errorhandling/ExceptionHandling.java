// (C) 2009-10 Ralf Laemmel

package idioms.errorhandling;

/**
 * Read two ints from the command line and print the sum.
 * Do some checking and exception handling for error handling.
 */
public class ExceptionHandling {

	public static void main(String[] args) {
		        
        if (args.length != 2) {
        	System.err.println("Incorrect number of arguments");
            System.exit(-1);
        }

        try {
        	int value1 = Integer.parseInt(args[0]);
        	int value2 = Integer.parseInt(args[1]);
        	int sum = value1 + value2;
            System.out.println(value1 + " + " + value2 + " = " + sum);
        }
        catch (NumberFormatException e) {
        	System.err.println("Argument(s) not a number");
            System.exit(-1);        	
        }
	}
}
