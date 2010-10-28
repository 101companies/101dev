// (C) 2009 Ralf Laemmel

package idioms.precision;

/**
 * Test ints for maximum value
 * Of course, the maximum value is known.
 * The following program merely demonstrates an indirect way of determining the value.
 */
public class MaxValue {

	public static byte MaxByte() {
		byte i = 0;
		byte r;
		do 
			r = i;
		while (++i > 0);
		return r;
	}
	
	public static int MaxInteger() {
		int i = 0;
		int r;
		do 
			r = i;
		while (++i > 0);
		return r;
	}
	
	public static void main(String[] args) {
		System.out.println("MaxByte    (defined):  " + java.lang.Byte.MAX_VALUE);
		System.out.println("MaxByte    (computed): " + MaxByte());
		System.out.println("MaxInteger (defined):  " + java.lang.Integer.MAX_VALUE);
		System.out.println("MaxInteger (computed): " + MaxInteger());
	}

}
