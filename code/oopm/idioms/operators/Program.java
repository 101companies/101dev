// (C) 2009 Ralf Laemmel

package idioms.operators;

/**
 * Illustrate the Java operators for number types and booleans
 */
public class Program {

	/* 
	 * An implementation of logical negation
	 */
	public static boolean not(boolean x) {
		if (x) 
			return false;
		else
			return true;
	}
	
	/*
	 * An implementation of logical and
	 */
	public static boolean and(boolean x, boolean y) {
		if (x)
			if (y)
				return true;
		return false;
	}

	/*
	 * An implementation of logical or
	 */
	public static boolean or(boolean x, boolean y) {
		if (!x)
			if (!y)
				return false;
		return true;
	}
	
	/*
	 * An implementation of logical xor
	 */
	public static boolean xor(boolean x, boolean y) {
		if (x && !y || !x && y)
			return false;
		else
			return true;
	}	

	/*
	 * An eager implementation of the ternary operator on ints
	 */
	public int ternary(boolean b, int x, int y) {
		if (b) return x; else return y;
	}
	
	public static void main(String[] args) {

		// Declaration with initialization
		int x = 2;
		int y = 3;

		// Unary sign operators (prefix)
		System.out.println("Plus sign:  " + (+x) );
		System.out.println("Minus sign: " + (-x) );
		
		// Basic binary operators on ints
		System.out.println("Addition:       " + (x+y));
		System.out.println("Subtraction:    " + (x-y));
		System.out.println("Multiplication: " + (x*y));
		System.out.println("Division:       " + (x/y));
		System.out.println("Modulo:         " + (x%y)); 
		
		// Relational operators
		System.out.println("Equality:   " + (x==y));
		// System.out.println("Equality: " + (x=y)); // Oops!
		System.out.println("Inequality: " + (x!=y));
		System.out.println("LT:         " + (x<y));
		System.out.println("GT:         " + (x>y));
		System.out.println("LTE:        " + (x<=y));
		System.out.println("GTE:        " + (x>=y));
		
		// Boolean operators
		System.out.println("Logical negation:  " + (!(x > x)));
		System.out.println("Logical and:       " + (1 < x & x < y));
		System.out.println("Logical or:        " + (x == 1 | x == 2));
		System.out.println("Logical xor:       " + (x == y ^ x != y));
		System.out.println("Short-circuit and: " + (1 < x && x < y));
		System.out.println("Short-circuit or:  " + (x == 1 || x == 2));
		
		// Ternary operator
		System.out.println( x > y ? x - y : y - x );
		
		// Bitwise and bitshift operators
		System.out.println("Bitwise negation: " + (~x));
		System.out.println("Bitwise and:      " + (x&y));
		System.out.println("Bitwise or:       " + (x|y));
		System.out.println("Shift right:      " + (x >> 1));
		System.out.println("Shift left:       " + (x << 2));

		// Other bit... operators omitted!
		
		// Increment operators
		System.out.println("Prefix  increment: " + ++x );
		System.out.println("Postfix increment: " + x++ );
		System.out.println("Prefix  decrement: " + --x );
		System.out.println("Postfix decrement: " + x-- );
		
		// Assignment operators
		x += 2; System.out.println( x );			// x = x + 2;
		x =+ 3; System.out.println( x );			// Oops!
		x -= 1; System.out.println( x );			// x = x - 1;

		// Other assignment operators omitted!
	}

}
