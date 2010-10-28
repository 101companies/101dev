// (C) 2009 Ralf Laemmel

package data.fifo;

import data.list.singlylinked.*;

public class SimpleQueue<T> implements Queue<T> {
	
	private ListEntry<T> first = null;
	private ListEntry<T> last = null;
	
	public void enqueue(T item) {
		ListEntry<T> e = new ListEntry<T>();
		e.item = item;
		e.next = null;
		if (first==null)
			first = e;
		if (last!=null)
			last.next = e;
		last = e;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public T dequeue() {
		T result = first.item;
		if (first==last)
			last = null;
		first = first.next;
		return result;
	}
		
	public static void main(String[] args) {
		SimpleQueue<Integer> q = new SimpleQueue<Integer>();
		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(3);
		while (!q.isEmpty())
			System.out.println(q.dequeue());
	}
}
