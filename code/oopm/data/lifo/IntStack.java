// (C) 2009 Ralf Laemmel

package data.lifo;

public interface IntStack {
	
	/**
	 * Add an item on top of the stack
	 */
	public void push(int item);
	
	/**
	 * Test a stack to be empty
	 */
	public boolean isEmpty();
	
	/**
	 * Return the item at the top of the stack
	 */
	public int top();

	/**
	 * Pop the top of the stack
	 */
	public void pop();
	
}
