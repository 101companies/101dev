// (C) 2009 Ralf Laemmel

package algorithm.factorial;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestFor {
	@Test
	public void testFactorialOf1to4() {
		assertEquals(1, For.factorial(1));
		assertEquals(2, For.factorial(2));
		assertEquals(6, For.factorial(3));
		assertEquals(24, For.factorial(4));
	}
	
	@Test
	public void testFactorialOf0() {
		assertEquals(1, For.factorial(0));		
	}
	
	@Test
	public void testFactorialForNegativeOperand() {
		assertEquals(1, For.factorial(-1));
	}
	
	@Test
	public void testFactorialWithRangeError() {
		assertEquals(-288522240, For.factorial(17));
	}
}
