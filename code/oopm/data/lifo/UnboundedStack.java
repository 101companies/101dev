// (C) 2009 Ralf Laemmel

package data.lifo;

import data.list.singlylinked.*;

public class UnboundedStack<T> implements Stack<T> {
	
	private ListEntry<T> top = null;
	
	public void push(T item) {
		ListEntry<T> e = new ListEntry<T>();
		e.item = item;
		e.next = top;
		top = e;
	}
	
	public boolean isEmpty() {
		return top == null;
	}
	
	public T top() {
		return top.item;
	}
	
	public void pop() {
		top = top.next;
	}
	
	public static void main(String[] args) {
		UnboundedStack<Integer> s = new UnboundedStack<Integer>();
		s.push(1);
		s.push(2);
		s.push(3);
		while (!s.isEmpty()) {
			System.out.println(s.top());
			s.pop();
		}
	}
}
