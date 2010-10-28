// (C) 2008 Ralf Laemmel

package data.matrix.iop;

import data.array.Array;

/**
 * Implement the interface for matrices by means of a list of non-zero entries.
 * This representation is more space-efficient for sparse matrices.
 * Hence the name of the class.
 * The use of a plain list is computationally (very) inefficient though.
 * This is a good exercise: improve the access efficiency for entries.
 */
public class Sparse2D implements Matrix2D {

	// Use a dynamic array to store matrix.
	private Array<Entry> a = new Array<Entry>();
	
	// Maintain numbers of rows and columns
	private int l1, l2;
	
	public Sparse2D(int i, int j) {
		l1 = i;
		l2 = j;
	}
	
	public int getD1() {
		return l1;
	}
	
	public int getD2() {
		return l2;
	}
	
	public int getAt(int i, int j) {
		for (int k = 0; k < a.getLength(); k++) {
			Entry e = (Entry)a.getAt(k);
			if (e.i == i && e.j == j)
				return e.x;
		}
		return 0;
	}
	
	public void setAt(int i, int j, int x) {
		Entry e = new Entry();
		e.i = i;
		e.j = j;
		e.x = x;
		for (int k = 0; k < a.getLength(); k++) {
			Entry f = (Entry)a.getAt(k);
			if (f.i == i && f.j == j) {
				a.setAt(k, e); // One could compact array.
				return;
			}
		}
		a.append(e);
	}

	public boolean sparse() {
		return true;
	}
	
	public String toString() {
		return Utilities2D.toString(this);
	}
}
