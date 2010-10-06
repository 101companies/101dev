package org.softlang.util;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SimpleFlaggedList<T> implements Iterable<T> {

	/**
	 * Simple list with a flag for changes
	 */
	private static final long serialVersionUID = 1L;

	private List<T> inner;

	private boolean changed;

	public SimpleFlaggedList() {
		inner = new LinkedList<T>();
		changed = true;
	}

	public int size() {
		return inner.size();
	}

	public T get(int index) {
		return inner.get(index);
	}

	public T set(int index, T element) {
		changed = true;
		return inner.set(index, element);
	}

	public boolean add(T t) {
		changed = true;
		return inner.add(t);
	}

	public T remove(int index) {
		changed = true;
		return inner.remove(index);
	}

	@Override
	public Iterator<T> iterator() {
		return inner.iterator();
	}

	public boolean wasChanged() {
		return changed;
	}

	public void setUnchanged() {
		changed = false;
	}

}
