// (C) 2009 Ralf Laemmel

package algorithm.add;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestEndRecursive {
	@Test
	public void test00() {
		assertEquals(0,EndRecursive.add(0,0));
	}
	
	@Test
	public void test01() {
		assertEquals(1,EndRecursive.add(0,1));
	}
	
	@Test
	public void test10() {
		assertEquals(1,EndRecursive.add(1,0));
	}	
	
	@Test
	public void test88() {
		assertEquals(88,EndRecursive.add(42,46));
	}	
}
