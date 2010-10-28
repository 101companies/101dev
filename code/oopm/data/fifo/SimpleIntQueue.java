// (C) 2009 Ralf Laemmel

package data.fifo;

import data.list.singlylinked.*;

public class SimpleIntQueue implements IntQueue {
	
	private IntListEntry first = null;
	private IntListEntry last = null;
	
	public void enqueue(int item) {
		IntListEntry e = new IntListEntry();
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
	
	public int dequeue() {
		int result = first.item;
		if (first==last)
			last = null;
		first = first.next;
		return result;
	}
		
	public static void main(String[] args) {
		SimpleIntQueue q = new SimpleIntQueue();
		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(3);
		while (!q.isEmpty())
			System.out.println(q.dequeue());
	}
}
