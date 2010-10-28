// (C) 2009 Ralf Laemmel

package data.matrix.iop;

/**
 * Implement the interface for matrices by means of a normal array.
 * This representation is space-efficient for dense matrices.
 * Hence the name of the class.
 */
public class Dense2D implements Matrix2D {

	private int[][] m;

	public Dense2D(int i, int j) {
		m = new int[i][j];
	}
	
	public int getD1() {
		return m.length;
	}
	
	public int getD2() {
		return m[0].length;
	}
	
	public int getAt(int i, int j) {
		return m[i][j];
	}
	
	public void setAt(int i, int j, int x) {
		m[i][j] = x;
	}
	
	public boolean sparse() {
		return false;
	}
	
	public String toString() {
		return Utilities2D.toString(this);
	}
}
