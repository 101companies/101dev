// (C) 2009 Ralf Laemmel

package algorithm.factorial;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestTailRecursive {
	@Test
	public void testFactorialOf0to10() {
		for (int i = 0; i <= 10; i++)
			assertEquals(PrimitiveRecursive.factorial(i), TailRecursive.factorial(i));
	}
	
	@Test(expected=java.lang.StackOverflowError.class)
	public void testNegative() {
		TailRecursive.factorial(-1);		
	}	
}
