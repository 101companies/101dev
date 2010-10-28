// (C) 2009 Ralf Laemmel

package data.list.singlylinked;

public class IntListEntry {
	public int item = 0;
	public IntListEntry next = null;
	
	// Iterative algorithm for length of list
	public int length() {
		int i = 0;
		for (IntListEntry j = this; j != null; j = j.next) i++;
		return i;
	}

	// Recursive algorithm for length of list
	public int length2() {
		return (this.next==null) ? 1 : 1 + this.next.length2();
	}
	
	// Iterative algorithm for sum of list
	public int sum() {
		int result = 0;
		for (IntListEntry j = this; j != null; j = j.next)
			result += j.item;
		return result;
	}

	// Recursive algorithm for sum of list
	public int sum2() {
		return this.item + ((this.next==null) ? 0 : this.next.sum2());
	}
	
	public boolean equals(IntListEntry that) {
		return
			that != null
		&& 	this.item == that.item
		&& 	(	(this.next == null && that.next == null) 
			||	(this.next != null && that.next != null && this.next.equals(that.next)));
	}
	
	// Recursive algorithm for finding an entry
	public boolean findReferenceBased(IntListEntry e) {
		return 		this == e
				||	(	this.next != null
					&& 	this.next.findReferenceBased(e));
	}
	
	// Recursive algorithm for finding an entry
	public boolean findValueBased(int i) {
		return 		this.item == i
				||	(	this.next != null
					&& 	this.next.findValueBased(i));
	}
		
	public IntListEntry clone() {
		IntListEntry clone = new IntListEntry();
		clone.item = this.item;
		if (this.next != null)
			clone.next = this.next.clone();
		return clone;
	}
	
	public void println() {
		System.out.println(this.item);
		if (this.next != null)
			this.next.println();
	}
		
	// Iterative algorithm for appending two lists
	public void append(IntListEntry rest) {
		IntListEntry i;
		for (i = this; i.next != null; i = i.next) {}
		i.next = rest;
	}
	
	// Recursive algorithm for appending two lists
	public void append2(IntListEntry rest) { 
		if (this.next == null)
			this.next = rest;
		else
			this.next.append(rest);
	}
	
	// Recursive algorithm for reversal of list
	public IntListEntry reverse() {
		if (this.next == null)
			return this;
		IntListEntry head = this;
		IntListEntry tail = this.next;
		head.next = null;
		IntListEntry result = tail.reverse();
		result.append(head); 
		return result;
	}

	// Composition for rotating to the left
	public IntListEntry rotateLeft() {
		if (this.next == null)
			return this;
		IntListEntry head = this;
		IntListEntry tail = this.next;
		head.next = null;
		tail.append(head);
		return tail;
	}

	public static void main(String[] args) {
		
		System.out.println("println");
		IntListEntry e1 = new IntListEntry();
		IntListEntry e2 = new IntListEntry();
		IntListEntry e3 = new IntListEntry();
		e1.item = 12; e1.next = e2;
		e2.item = 99; e2.next = e3;
		e3.item = 37;
		e1.println();
		
		System.out.println("length & sum");
		System.out.println(e1.length());
		System.out.println(e1.length2());
		System.out.println(e1.sum());
		System.out.println(e1.sum2());

		System.out.println("clone");
		IntListEntry e4 = e1.clone();
		e4.println();				
		System.out.println(e1.equals(e4));
		
		System.out.println("reverse");
		IntListEntry reversed = e1.reverse();
		reversed.println();

		System.out.println("rotate");
		IntListEntry rotated = reversed.rotateLeft();
		rotated.println();		

		System.out.println("equal");
		System.out.println(rotated.equals(e4));		
		
		System.out.println("sharing");
		IntListEntry l1a = new IntListEntry();
		IntListEntry l2a = new IntListEntry();
		IntListEntry b = new IntListEntry();
		l1a.item = 1;
		l2a.item = 2;
		b.item = 42;
		l1a.next = b;
		l2a.next = b;
		b.item = 88;
		l1a.println();		
		l2a.println();		
		
	}
}
