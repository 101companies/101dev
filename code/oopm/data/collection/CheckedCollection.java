// (C) 2008 Ralf Laemmel

package data.collection;

import java.util.Collection;
import java.util.Iterator;

public class CheckedCollection<T> implements Collection<T> {

	private Collection<T> unchecked;
	
	public CheckedCollection(Collection<T> c) {
		unchecked = c;
	}
	
	public boolean add(T o) {
		
		// Housekeeping
		int oldSize = size(); 		
	
		boolean result = unchecked.add(o);

		// Postcondition
		assert contains(o)
		    && (!result || size() == oldSize + 1);
		
		return result;
	}

	public boolean addAll(Collection<? extends T> c) {
		
		// Housekeeping
		int oldSize = size(); 
		
		boolean result = unchecked.addAll(c);
		
		// Postcondition
		assert containsAll(c)
		    && (!result || size() > oldSize);
		
		return result;
	}
	
	public void clear() {
		
		unchecked.clear();
		
		// Postcondition
		assert size() == 0;
	}

	public boolean contains(Object o) {

		boolean result = unchecked.contains(o);
		
		// Postcondition
		assert !result || size() >= 1;
		
		return result;
	}
	
	public boolean containsAll(Collection<?> c) {

		boolean result = unchecked.containsAll(c);
		
		// Postcondition
		assert !result || size() >= c.size();		
		
		return result;
	}

	public boolean isEmpty() {
		
		boolean result = unchecked.isEmpty();
		
		// Postcondition
		assert !result || size() == 0;
		
		return result;
	}

	public Iterator<T> iterator() {
		return unchecked.iterator();
	}
	
	public boolean remove(Object o) {

		boolean result = unchecked.remove(o);
		
		// Postcondition
		assert !contains(o);

		return result;
	}
	
	public boolean removeAll(Collection<?> c) {
		return unchecked.removeAll(c);
	}

	public boolean retainAll(Collection<?> c) {
		
		boolean result = unchecked.retainAll(c);
		
		// Postconditon
		for (Object o : c)
			assert !contains(o);
		
		return result;
	}
	
	public int size() {
		return unchecked.size();
	}
	
	public Object[] toArray() {
		return unchecked.toArray();
	}
	
	public <U extends Object> U[] toArray(U[] a) {
		return unchecked.toArray(a);
	}
	
}
