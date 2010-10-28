// (C) 2009 Ralf Laemmel

package data.list.doublylinked;

/**
 * Entries of a double-linked list
 */
public interface IntListEntry {

	/** 
	 * Return item at current entry
	 */
	public int getItem();

	/**
	 * Test whether there is a previous entry
	 */
	public boolean hasPrevious();
	
	/**
	 * Return previous entry
	 */
	public IntListEntry getPrevious();

	/**
	 * Test whether there is a next entry
	 */
	public boolean hasNext();
	
	/**
	 * Return next entry
	 */
	public IntListEntry getNext();
		
}
