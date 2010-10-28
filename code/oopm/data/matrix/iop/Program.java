// (C) 2008 Ralf Laemmel

package data.matrix.iop;

public class Program {

	public static void main(String[] args) {
		
		Matrix2D add1 = new Sparse2D(2,3);
		add1.setAt(0, 0, 1);
		add1.setAt(0, 1, 3);		
		add1.setAt(0, 2, 2);		
		add1.setAt(1, 0, 1);
		add1.setAt(1, 1, 2);		
		add1.setAt(1, 2, 2);
		System.out.println(add1.toString());

		Matrix2D add2 = new Sparse2D(2,3);
		add2.setAt(0, 0, 0);
		add2.setAt(0, 1, 0);		
		add2.setAt(0, 2, 5);		
		add2.setAt(1, 0, 2);
		add2.setAt(1, 1, 1);		
		add2.setAt(1, 2, 1);
		System.out.println(add2.toString());
		
		System.out.println(Utilities2D.add(add1, add2).toString());
		
		Matrix2D mult1 = new Dense2D(2,3);
		mult1.setAt(0, 0, 1);
		mult1.setAt(0, 1, 2);		
		mult1.setAt(0, 2, 3);		
		mult1.setAt(1, 0, 4);
		mult1.setAt(1, 1, 5);		
		mult1.setAt(1, 2, 6);
		System.out.println(mult1.toString());

		Matrix2D mult2 = new Dense2D(3,2);
		mult2.setAt(0, 0, 6);
		mult2.setAt(0, 1, -1);		
		mult2.setAt(1, 0, 3);		
		mult2.setAt(1, 1, 2);
		mult2.setAt(2, 0, 0);		
		mult2.setAt(2, 1, -3);
		System.out.println(mult2.toString());

		System.out.println(Utilities2D.mult(mult1, mult2).toString());
			
	}

}
