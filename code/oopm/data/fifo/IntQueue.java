// (C) 2009 Ralf Laemmel

package data.fifo;

public interface IntQueue {
	
	/**
	 * Add an item to the queue
	 */
	public void enqueue(int item);

	/**
	 * Test a queue to be empty
	 */
	public boolean isEmpty();

	/**
	 * Remove an item from the queue and return it
	 */
	public int dequeue();
		
}
