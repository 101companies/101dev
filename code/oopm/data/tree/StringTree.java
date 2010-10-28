// (C) 2009 Ralf Laemmel

package data.tree;

public class StringTree {

	public String node = null;
	public StringSubtrees subtrees = null;

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
		for (StringSubtrees j=subtrees; j != null; j = j.rest)
			j.first.print(indent);
	}	
	
	public static void main(String[] args) {
		StringTree lisa = new StringTree();
		StringTree dette = new StringTree();
		StringTree ellen = new StringTree();
		StringTree ralf = new StringTree();
		lisa.node = "Lisa";
		dette.node = "Dette";
		ellen.node = "Ellen";
		ralf.node = "Ralf";
		lisa.subtrees = new StringSubtrees();
		lisa.subtrees.first = dette;
		dette.subtrees = new StringSubtrees();
		dette.subtrees.first = ellen;
		dette.subtrees.rest = new StringSubtrees();
		dette.subtrees.rest.first = ralf;
		lisa.print();
	}

}
