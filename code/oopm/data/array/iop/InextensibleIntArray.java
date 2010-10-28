// (C) 2009 Ralf Laemmel

package data.array.iop;

/**
 * An int array that is essentially just a plain array
 */
public class InextensibleIntArray implements IntArray {

	private int[] a = null;
	
	public InextensibleIntArray(int length) {
		a = new int[length];
	}
	
	public int getLength() {
		return a.length;
	}
		
	/**
	 * Subscript; read access
	 */
	public int getAt(int i) {
		return a[i];
	}

	/**
	 * Subscript; write access
	 */
	public void setAt(int i, int x) {
		a[i] = x;
	}
}
