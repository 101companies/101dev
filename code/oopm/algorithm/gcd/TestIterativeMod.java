// (C) 2009 Ralf Laemmel

package algorithm.gcd;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestIterativeMod {
	@Test
	public void testGcdNormal() {
		assertEquals(3, IterativeMod.gcd(15,6));
	}
	
	@Test(expected=java.lang.ArithmeticException.class)
	public void testGcdOf1and0() {
		IterativeMod.gcd(1,0);
	}	
	
	@Test(expected=java.lang.ArithmeticException.class)
	public void testGcdOf0and1() {
		IterativeMod.gcd(0,1);
	}	
}
