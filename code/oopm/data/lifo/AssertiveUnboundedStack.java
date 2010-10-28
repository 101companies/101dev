// (C) 2009 Ralf Laemmel

package data.lifo;

import data.list.singlylinked.*;

public class AssertiveUnboundedStack<T> implements Stack<T> {
	
	private ListEntry<T> top = null;
	
	public void push(T item) {
		// Housekeeping for state
		int oldSize = size();

		// Precondition
		assert item != null;

		ListEntry<T> e = new ListEntry<T>();
		e.item = item;
		e.next = top;
		top = e;
		
		// Postcondition
		assert 		!isEmpty()
				&& 	top() == item
				&& size() - 1 == oldSize;				
	}
	
	public boolean isEmpty() {
		return top == null;
	}
	
	public T top() {
		// Precondition
		assert !isEmpty();
		
		return top.item;
	}
	
	public void pop() {
		// Housekeeping for state
		int oldSize = size();
		
		// Precondition
		assert !isEmpty();

		top = top.next;
		
		// Postcondition
		assert size() + 1 == oldSize;
	}
	
	public int size() {
		ListEntry<T> i = top;
		int size = 0;
		while (i!=null) {
			size++;
			i = i.next;
		}
		return size;
	}
	
	public static void main(String[] args) {
		AssertiveUnboundedStack<Integer> s = new AssertiveUnboundedStack<Integer>();
		s.push(1);
		s.push(2);
		s.push(3);
		while (!s.isEmpty()) {
			System.out.println(s.top());
			s.pop();
		}
	}
}
