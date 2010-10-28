package algorithm.search;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestLinear {

	public static int[] a = {1,2,3,4,5,6,7,9,10};	
	
	@Test
	public void testFirst() {
		assertEquals(0,Program.linear(a, 1));
	}

	@Test
	public void testSecond() {
		assertEquals(1,Program.linear(a, 2));
	}
	
	@Test
	public void testLast() {
		assertEquals(8,Program.linear(a, 10));
	}	
	
	@Test
	public void testMissing() {
		assertEquals(-1,Program.linear(a, 8));
	}		
}
