// (C) 2009 Ralf Laemmel

package data.array;

/**
 * An extensible array of ints.
 * One can access arbitrary subscripts.
 */
public class IntArray {

	// We use a normal array in the end.
	// We start from an empty array.
	// In practice, one should use some small size > 0.
	private int[] a = new int[0];

	// We store the actual length.
	// This is the largest index+1 that was used so far.
	private int length = 0;
	
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
	
	/**
	 * Return the de-facto length of the array.
	 * This is the largest index+1 used so far in a subscript.
	 */
	public int getLength() {
		return length;
	}
	
	/**
	 * This member tells us the length of the private array.
	 * Normally, this property should not be of concern.
	 */
	public int getCapacity() {
		return a.length;
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
	
	/**
	 * Extend the array by a new entry
	 */
	public void append(int x) {
		setAt(getLength(),x);
	}
	
}
