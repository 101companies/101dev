// (C) 2009 Ralf Laemmel

package data.tree;

import data.list.singlylinked.*;

public class Tree<T> {

	public T node = null;
	public ListEntry<Tree<T>> subtrees = null;

	public void print() {
		print(0);
	}

	public void print(int indent) {
		System.out.print('|');
		for (int i=0; i<indent; i++) System.out.print('-'); 
		System.out.print("- ");
		System.out.println(node);
		indent++;
		indent++;
		for (ListEntry<Tree<T>> j=subtrees; j != null; j = j.next)
			j.item.print(indent);
	}	
	
	public static void main(String[] args) {
		Tree<String> lisa = new Tree<String>();
		Tree<String> dette = new Tree<String>();
		Tree<String> ellen = new Tree<String>();
		Tree<String> ralf = new Tree<String>();
		lisa.node = "Lisa";
		dette.node = "Dette";
		ellen.node = "Ellen";
		ralf.node = "Ralf";
		lisa.subtrees = new ListEntry<Tree<String>>();
		lisa.subtrees.item = dette;
		dette.subtrees = new ListEntry<Tree<String>>();
		dette.subtrees.item = ellen;
		dette.subtrees.next = new ListEntry<Tree<String>>();
		dette.subtrees.next.item = ralf;
		lisa.print();
	}

}
