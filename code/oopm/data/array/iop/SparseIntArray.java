// (C) 2009 Ralf Laemmel

package data.array.iop;

/**
 * An int array that is prepared for sparse population.
 * That is, we assume that many entries may be zero.
 * We improve memory footprint at the cost of access complexity.
 * The shown access method is naive!
 */
public class SparseIntArray implements IntArray {

	private Entry[] a = new Entry[0];
	
	public int getLength() {
		// Compute the length as the largest index (+1) ever present.
		// Assume that the first null terminates an array.
		int result = 0;
		for (Entry e : a) {
			if (e==null) 
				break;
			if (e.i+1>result) 
				result = e.i+1;
		}
		return result;
	}
		
	/**
	 * Subscript; read access
	 */
	public int getAt(int i) {
		// Look up subscript.
		// Assume that the first null terminates an array.
		for (Entry e : a) {
			if (e==null) 
				break;
			if (e.i==i) 
				return e.x;
		}
		return 0;
	}

	/**
	 * Subscript; write access
	 */
	public void setAt(int i, int x) {
		// Look up subscript.
		// Assume that the first null terminates an array.
		// Extend underlying array if necessary.
		int j = 0;
		for (; j<a.length; j++) {
			if (a[j]==null)
				break;
			if (a[j].i==i) {
				a[j].x = x;
				return;
			}
		}
		if (j>=a.length) {
			Entry[] b = new Entry[2*a.length+1];
			for (int k=0; k<a.length; k++)
				b[k] = a[k];
			a = b;
		}
		Entry e = new Entry();
		e.i = i;
		e.x = x;
		a[j] = e;
	}
}
