package algorithm.sin;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestEfficient {

	@Test
	public void testNormal() {
		assertTrue(
				Math.abs(Math.sin(3.1415/2)
			- 	Efficient.sin(3.1415/2))
			< 	1e-6);
	}
	
}
