// (C) 2008 Ralf Laemmel

package data.matrix.iop;

import data.array.StringBuilder;

public class Utilities2D {
	
	/**
	 * Define a nice layout for printing any sort of matrix.
	 */
	public static String toString(Matrix2D m) {
		StringBuilder s = new StringBuilder();
		for (int i=0; i<m.getD1(); i++) {
			for (int j=0; j<m.getD2(); j++)
				s.append(m.getAt(i, j) + " ");
			s.append("\n");
		}
		return s.toString();
	}
	
	/**
	 * Addition for matrices
	 */
	public static Matrix2D add(Matrix2D a, Matrix2D b) {
		if (   a.getD1() != b.getD1()
			|| a.getD2() != b.getD2())
			return null;
		Matrix2D c = 
			(a.sparse() && b.sparse()) ?
					new Sparse2D(a.getD1(),a.getD2())
		          : new Dense2D(a.getD1(),a.getD2());
		for (int i=0; i<a.getD1(); i++)
			for (int j=0; j<a.getD2(); j++)
				c.setAt(i, j, a.getAt(i, j) + b.getAt(i, j));
		return c;
	}
	
	/*
	 * Multiplication for matrices
	 */
	public static Matrix2D mult(Matrix2D a, Matrix2D b) {
		if (a.getD2() != b.getD1())
			return null;		
		Matrix2D c = 
			(a.sparse() && b.sparse()) ?
					new Sparse2D(a.getD1(),b.getD2())
		          : new Dense2D(a.getD1(),b.getD2());
		for (int i=0; i<a.getD1(); i++)
			for (int j=0; j<b.getD2(); j++) {
				c.setAt(i, j, 0);
				for (int k=0; k<a.getD2(); k++)
					c.setAt(i, j, c.getAt(i,j) + a.getAt(i, k) * b.getAt(k, j));
			}
		return c;
	}
}
