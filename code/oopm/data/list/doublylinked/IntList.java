// (C) 2009 Ralf Laemmel

package data.list.doublylinked;

public interface IntList {

	/**
	 * Test whether list is empty
	 */
	public boolean isEmpty();

	/**
	 * Return length of list
	 */
	public int length();
	
	/**
	 * Get first entry of list
	 */
	public IntListEntry getFirst();

	/**
	 * Get last entry of list
	 */
	public IntListEntry getLast();
	
	/**
	 * Append an entry to the list
	 */
	public void append(IntListEntry e);
	
	/**
	 * Append an item to the list
	 */
	public void append(int item);

	/**
	 * Prepend an entry to the list
	 */
	public void prepend(IntListEntry e);
	
	/**
	 * Prepend an item to the list
	 */
	public void prepend(int item);
	
	/** 
	 * Delete an entry from the list
	 */
	public void delete(IntListEntry e);

	/**
	 * Insert an entry after another entry
	 */
	public void insertBefore(IntListEntry e, IntListEntry x);
	
	/**
	 * Insert an entry after another entry
	 */
	public void insertAfter(IntListEntry e, IntListEntry x);

	/**
	 * Cut before an entry
	 */
	public IntList cutBefore(IntListEntry e);
		
	/**
	 * Print a list forward
	 */
	public void printForward();

	/**
	 * Print a list backward
	 */
	public void printBackward();
		
}
