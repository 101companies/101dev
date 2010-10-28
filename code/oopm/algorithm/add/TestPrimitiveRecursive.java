// (C) 2009 Ralf Laemmel

package algorithm.add;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestPrimitiveRecursive {

	@Test
	public void testAdd42and46() {
		assertEquals(88,PrimitiveRecursive.add(42,46));
	}

	@Test
	public void testAdd0and0() {
		assertEquals(0,PrimitiveRecursive.add(0,0));
	}

	@Test
	public void testAdd0and1() {
		assertEquals(1,PrimitiveRecursive.add(0,1));
	}	

	@Test
	public void testAdd1and0() {
		assertEquals(1,PrimitiveRecursive.add(1,0));
	}		
	
	@Test(expected=java.lang.StackOverflowError.class)
	public void testAdd1stNegative() {
		PrimitiveRecursive.add(-1,0);
	}

	@Test
	public void testAdd2ndNegative() {
		assertEquals(-1,PrimitiveRecursive.add(0,-1));
	}			
}
