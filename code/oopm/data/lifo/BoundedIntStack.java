// (C) 2009 Ralf Laemmel

package data.lifo;

import data.list.singlylinked.*;

public class BoundedIntStack implements IntStack {
	
	private IntListEntry top = null;
	private int length = 0;
	private int limit = 0;
	
	public BoundedIntStack(int limit) {
		this.limit = limit;
	}
	
	public void push(int item) {
		if (length==limit)
			return;
		IntListEntry e = new IntListEntry();
		e.item = item;
		e.next = top;
		top = e;
		length++;
	}
	
	public boolean isEmpty() {
		return top == null;
	}
	
	public int top() {
		return top.item;
	}
	
	public void pop() {
		length--;
		top = top.next;
	}
	
	public static void main(String[] args) {
		BoundedIntStack s = new BoundedIntStack(42);
		s.push(1);
		s.push(2);
		s.push(3);
		while (!s.isEmpty()) {
			System.out.println(s.top());
			s.pop();
		}
	}
}
