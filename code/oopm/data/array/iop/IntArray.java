// (C) 2009 Ralf Laemmel

package data.array.iop;

/**
 * An interface for array-like datatypes.
 * W.l.o.g., the elements are of type int.
 */
public interface IntArray {
	
	/**
	 * Return the length of the array.
	 */
	public int getLength();
		
	/**
	 * Subscript; read access
	 */
	public int getAt(int i);

	/**
	 * Subscript; write access
	 */
	public void setAt(int i, int x);	
}
