// (C) 2009 Ralf Laemmel

package data.tree;

public class BinIntTree {

	public int info;
	public BinIntTree left, right;
	
	public void print() {
		System.out.println(this.info);
		if (left != null)
			left.print();
		if (right != null)
			right.print();
	}
	
	public int sum() {
		return 	  this.info
				+ (this.left != null ? this.left.sum() : 0)
				+ (this.right != null ? this.right.sum() : 0);
	}
	
	public static void main(String[] args) {
		BinIntTree t1 = new BinIntTree();
		BinIntTree t11 = new BinIntTree();
		BinIntTree t12 = new BinIntTree();
		BinIntTree t121 = new BinIntTree();
		t1.info = 1;
		t1.left = t11;
		t1.right = t12;
		t11.info = 2;
		t12.info = 3;		
		t12.left = t121;
		t121.info = 4;
		t1.print();
		System.out.println(t1.sum());
	}
}
