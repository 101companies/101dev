// (C) 2009 Ralf Laemmel

package data.matrix;

/**
 * Matrix arithmetics using two-dimensional arrays
 */
public class Program {

	/*
	 * Add two matrixes
	 */
	public static int[][] add(int[][] a, int[][] b) {
		if (a.length != b.length
		||  a[0].length != b[0].length)
			return null;
	    int[][] c = new int[a.length][a[0].length];
	    for (int i=0; i<a.length; i++) 
	    	for (int j=0; j < a[0].length; j++)
	    		c[i][j] = a[i][j] + b[i][j];
	    return c;
	}
	
	/*
	 * Print a matrix
	 */
	public static void print(int[][] m) {
	    for (int i=0; i<m.length; i++)
	    	data.vector.Program.print(m[i]);
	}
	
	public static void main(String[] args) {
		int[][] m1 = 
			{ { 1, 2, 3 },
			  { 4, 5, 6 },
   		      { 7, 8, 9 } };

		int[][] m2 = 
		{ { 1, 0, 0 },
		  { 0, 1, 0 },
		  { 0, 0, 1 } };
		
		print(m1);
		print(m2);
		print(add(m1,m2));
		
	}

}
