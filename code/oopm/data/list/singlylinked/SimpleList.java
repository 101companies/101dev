package data.list.singlylinked;

import java.util.Iterator;

public class SimpleList<T> implements List<T> {

	private ListEntry<T> first = null;
	private ListEntry<T> last = null;

	/**
	 * Add an item to the list (append it)
	 */
	public void add(T item) {
		ListEntry<T> e = new ListEntry<T>();
		e.item = item;
		e.next = null;
		if (first == null)
			first = e;
		if (last != null)
			last.next = e;
		last = e;
	}

	/**
	 * Remove an item from the list (identity-based)
	 */
	public void remove(T item) {
		ListEntry<T> i = first;
		ListEntry<T> previous = null;
		for (; i != null; i = i.next)
			if (i.item == item)
				break;
			else
				previous = i;
		if (i != null)
			if (first == last)
				first = (last = null);
			else if (i == first)
				first = first.next;
			else {
				previous.next = i.next;
				if (i == last)
					last = previous;
			}
	}

	/**
	 * Return an iterator for the list at hand
	 */
	public Iterator<T> iterator() {
		return new ListIterator<T>(first);
	}

	public String toString() {
		Iterator<T> i = this.iterator();
		String result = "[";
		if (i.hasNext()) {
			result += i.next().toString();
			while (i.hasNext()) {
				result += ", ";
				result += i.next().toString();
			}
		}
		result += "]";
		return result;
	}

	public static void main(String[] args) {
		List<String> l1 = new SimpleList<String>();
		String s = "2";
		l1.add("1");
		l1.add(s);
		l1.add("3");
		System.out.println(l1);
		l1.remove(s);
		System.out.println(l1);

		List<Integer> l2 = new SimpleList<Integer>();
		l2.add(1);
		l2.add(2);
		l2.add(3);
		int sum = 0;
		for (int i : l2) 
			sum += i;
		System.out.println(sum);

		List<Integer> l3 = new SimpleList<Integer>();
		l3.add(1);
		l3.add(2);
		l3.add(3);
		int sum2 = 0;
		Iterator<Integer> i = l3.iterator();
		while (i.hasNext())
			sum2 += i.next();
		System.out.println(sum2);
				
		List<Object> l4 = new SimpleList<Object>();
		l4.add(1);
		l4.add(2);
		l4.add(3);
		int sum3 = 0;
		for (Object o : l4) 
			sum3 += (Integer)o;
		System.out.println(sum3);

		List<Object> l5 = new SimpleList<Object>();
		l5.add(1);
		l5.add(2);
		l5.add(3);
		l5.add("4");
		int sum4 = 0;
		for (Object o : l5) 
			if (o instanceof Integer)
				sum4 += (Integer)o;
		System.out.println(sum4);
		
	}
}
