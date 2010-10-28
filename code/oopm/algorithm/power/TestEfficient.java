package algorithm.power;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestEfficient {
	@Test
	public void testNormal() {
		assertEquals(Inefficient.power(2,1),Efficient.power(2,1));
		assertEquals(Inefficient.power(2,2),Efficient.power(2,2));
		assertEquals(Inefficient.power(2,3),Efficient.power(2,3));
		assertEquals(Inefficient.power(2,4),Efficient.power(2,4));
		assertEquals(Inefficient.power(3,1),Efficient.power(3,1));
		assertEquals(Inefficient.power(3,2),Efficient.power(3,2));
		assertEquals(Inefficient.power(3,3),Efficient.power(3,3));
		assertEquals(Inefficient.power(3,4),Efficient.power(3,4));
	}
}
