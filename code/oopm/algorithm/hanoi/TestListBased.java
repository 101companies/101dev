// (C) 2009 Ralf Laemmel

package algorithm.hanoi;

import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestListBased {
	@Test
	public void testNormal() {
		Iterator<Move> i = ListBased.move(3,"A","B","C").iterator();
		assertEquals(new Move(1,"A","C"),i.next());
		assertEquals(new Move(2,"A","B"),i.next());
		assertEquals(new Move(1,"C","B"),i.next());		
		assertEquals(new Move(3,"A","C"),i.next());		
		assertEquals(new Move(1,"B","A"),i.next());		
		assertEquals(new Move(2,"B","C"),i.next());		
		assertEquals(new Move(1,"A","C"),i.next());		
		assertFalse(i.hasNext());		
	}
}
