// (C) 2009 Ralf Laemmel

package data.list.singlylinked;

public class ListEntry<T> {
	public T item = null;
	public ListEntry<T> next = null;
	
	public int length() {
		return (this.next==null) ? 1 : 1 + this.next.length();
	}
	
	public static int sum(ListEntry<Integer> x) {
		return x.item + ((x.next==null) ? 0 : sum(x.next));
	}
	
	public boolean equals(ListEntry<T> that) {
		return
			that != null
		&& 	this.item.equals(that.item)
		&& 	(	(this.next == null && that.next == null) 
			||	(this.next != null && that.next != null && this.next.equals(that.next)));
	}

	public ListEntry<T> clone() {
		ListEntry<T> clone = new ListEntry<T>();
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
		
	public void append(ListEntry<T> rest) { 
		if (this.next == null)
			this.next = rest;
		else
			this.next.append(rest);
	}
	
	public ListEntry<T> reverse() {
		if (this.next == null)
			return this;
		ListEntry<T> head = this;
		ListEntry<T> tail = this.next;
		head.next = null;
		ListEntry<T> result = tail.reverse();
		result.append(head); 
		return result;
	}

	public static void main(String[] args) {
		
		System.out.println("println");
		ListEntry<Integer> e1 = new ListEntry<Integer>();
		ListEntry<Integer> e2 = new ListEntry<Integer>();
		ListEntry<Integer> e3 = new ListEntry<Integer>();
		e1.item = 12; e1.next = e2;
		e2.item = 99; e2.next = e3;
		e3.item = 37;
		e1.println();
		
		System.out.println("length & sum");
		System.out.println(e1.length());
		System.out.println(sum(e1));

		System.out.println("clone");
		ListEntry<Integer> e4 = e1.clone();
		e4.println();				
		System.out.println(e1.equals(e4));
		
		System.out.println("reverse");
		ListEntry<Integer> reversed = e1.reverse();
		reversed.println();		
	}
}
