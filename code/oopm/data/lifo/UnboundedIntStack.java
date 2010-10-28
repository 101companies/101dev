// (C) 2009 Ralf Laemmel

package data.lifo;

import data.list.singlylinked.*;

public class UnboundedIntStack implements IntStack {
	
	private IntListEntry top = null;
	
	public void push(int item) {
		IntListEntry e = new IntListEntry();
		e.item = item;
		e.next = top;
		top = e;
	}
	
	public boolean isEmpty() {
		return top == null;
	}
	
	public int top() {
		return top.item;
	}
	
	public void pop() {
		top = top.next;
	}
	
	public static void main(String[] args) {
		UnboundedIntStack s = new UnboundedIntStack();
		s.push(1);
		s.push(2);
		s.push(3);
		while (!s.isEmpty()) {
			System.out.println(s.top());
			s.pop();
		}
	}
}
