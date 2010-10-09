package org.softlang.util;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.softlang.company.Loadable;

public class SimpleFlaggedList<T extends Loadable> implements Iterable<T> {

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
		T t = inner.get(index);
		t.load();
		return t;
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
		T t = inner.remove(index);
		t.load();
		return t;
	}

	@Override
	public Iterator<T> iterator() {
		for (T t : inner)
			t.load();
		return inner.iterator();
	}

	public boolean wasChanged() {
		return changed;
	}

	public void setUnchanged() {
		changed = false;
	}

}
