// (C) 2008 Ralf Laemmel

package data.matrix.iop;

/**
 * Interface of two-dimensional matrices.
 * We can use this interface to implement a sparse representation, for example.
 */
public interface Matrix2D {
	public int getD1();
	public int getD2();
	public int getAt(int i, int j);
	public void setAt(int i, int j, int x);
	public boolean sparse();
}
