// (C) 2009 Ralf Laemmel

package data.array.iop;

/**
 * An int array that is essentially just a plain array
 */
public class ExtensibleIntArray implements IntArray {

	private int length = 0;
	private int[] a = new int[length];
	
	public int getLength() {
		return length;
	}
		
	/**
	 * Subscript; read access
	 */
	public int getAt(int i) {
		resizeIfNecessary(i);
		return a[i];
	}

	/**
	 * Subscript; write access
	 */
	public void setAt(int i, int x) {
		resizeIfNecessary(i);
		a[i] = x;
	}
	
	// We replace the current array by a bigger one if needed.
	// We always double size when a new array is needed.
	private void resizeIfNecessary(int i) {
		if (i>=length)
			length = i+1;
		if (i>=a.length) {
			int[] b = new int[2*length];
			for (int j=0; j<a.length; j++)
				b[j] = a[j];
			a = b;
		}
	}	
}
