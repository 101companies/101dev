package data.lifo;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestUnboundedStack {

	/*
	 * A newly created stack is empty.
	 */
	@Test
	public void testEmpty() {
		Stack<String> s = new UnboundedStack<String>();
		assertTrue(s.isEmpty());		
	}
	
	/*
	 * An empty stack throws on pop.
	 */
	@Test(expected=java.lang.NullPointerException.class)
	public void testPopWhenEmpty() {
		Stack<String> s = new UnboundedStack<String>();
		s.pop();
	}

	/*
	 * An empty stack throws on top.
	 */
	@Test(expected=java.lang.NullPointerException.class)
	public void testTopWhenEmpty() {
		Stack<String> s = new UnboundedStack<String>();
		s.top();
	}	
		
	/*
	 * A stack is non-empty after push.
	 * Also, top returns the pushed item.
	 */
	@Test()
	public void testPush() {
		Stack<String> s = new UnboundedStack<String>();
		String item = "item";
		s.push(item);
		assertFalse(s.isEmpty());
		assertSame(item,s.top());
	}
	
	/*
	 * When an item is popped off the stack, 
	 * then the previously pushed item becomes top-of-stack.
	 */
	@Test()
	public void testPop() {
		Stack<String> s = new UnboundedStack<String>();
		String item1 = "1";
		String item2 = "2";
		s.push(item1);
		s.push(item2);
		assertNotSame(item1,s.top());
		s.pop();
		assertSame(item1,s.top());
	}	
}
