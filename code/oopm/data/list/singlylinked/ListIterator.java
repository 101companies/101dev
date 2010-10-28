package data.list.singlylinked;

import java.util.Iterator;

class ListIterator<T> implements Iterator<T> {

	private ListEntry<T> current = null;

	ListIterator(ListEntry<T> root) {
		current = root;
	}

	public boolean hasNext() {
		return current != null;
	}

	public T next() {
		T item = current.item;
		current = current.next;
		return item;
	}

	/**
	 * This operation is optional per interface's documentation.
	 * UnsupportedOperationException is thrown hence.
	 */
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
