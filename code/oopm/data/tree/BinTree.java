// (C) 2009 Ralf Laemmel

package data.tree;

public class BinTree<T> {

	public T info;
	public BinTree<T> left, right;
	
	public void print() {
		System.out.println(this.info);
		if (left != null)
			left.print();
		if (right != null)
			right.print();
	}
	
	public static int sum(BinTree<Integer> x) {
		return 	x == null ? 0 : x.info
				+ (x.left != null ? sum(x.left) : 0)
				+ (x.right != null ? sum(x.right) : 0);
	}
	
	public static void main(String[] args) {
		BinTree<Integer> t1 = new BinTree<Integer>();
		BinTree<Integer> t11 = new BinTree<Integer>();
		BinTree<Integer> t12 = new BinTree<Integer>();
		BinTree<Integer> t121 = new BinTree<Integer>();
		t1.info = 1;
		t1.left = t11;
		t1.right = t12;
		t11.info = 2;
		t12.info = 3;		
		t12.left = t121;
		t121.info = 4;
		t1.print();
		System.out.println(sum(t1));
	}
}
