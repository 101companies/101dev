// (C) 2009-10 Ralf Laemmel

package idioms.errorhandling;

/**
 * Read two ints from the command line and print the sum.
 * Any parsing exception leads to unhandled exit of the program.
 */
public class NoErrorHandling {

	public static void main(String[] args) {
        int value1 = Integer.parseInt(args[0]);
        int value2 = Integer.parseInt(args[1]);
        int sum = value1 + value2;
        System.out.println(value1 + " + " + value2 + " = " + sum);
	}

}
