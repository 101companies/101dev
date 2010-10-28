// (C) 2009 Ralf Laemmel

package data.list.doublylinked;

public class SimpleIntList implements IntList {

	/**
	 * Entries of a double-linked list
	 */
	public class IntListEntryImpl implements IntListEntry {
		int item;
		IntListEntryImpl previous = null;
		IntListEntryImpl next = null;
		
		/**
		 * Construct an entry
		 */
		IntListEntryImpl(int item) {
			this.item = item;
		}
		
		/** 
		 * Return item at current entry
		 */
		public int getItem() {
			return item;
		}

		/**
		 * Test whether there is a previous entry
		 */
		public boolean hasPrevious() {
			return previous != null;
		}
		
		/**
		 * Return previous entry
		 */
		public IntListEntry getPrevious() {
			return previous;
		}

		/**
		 * Test whether there is a next entry
		 */
		public boolean hasNext() {
			return next != null;
		}	
		
		/**
		 * Return next entry
		 */
		public IntListEntry getNext() {
			return next;
		}
		
		/**
		 * Delete this entry
		 */
		void delete() {
			if (previous != null)
				previous.next = next;
			if (next != null)
				next.previous = previous;
			previous = null;
			next = null;
		}

		/**
		 * Insert an entry before this entry
		 */
		void insertBeforeThis(IntListEntry e) {
			IntListEntryImpl f = (IntListEntryImpl)e;
			f.previous = this.previous;
			f.next = this;
			if (this.previous != null)
				this.previous.next = f;
			this.previous = f;
		}
		
		/**
		 * Insert an item before this entry
		 */
		void insertBeforeThis(int item) {
			IntListEntryImpl e = new IntListEntryImpl(item);
			insertBeforeThis(e);
		}

		/**
		 * Insert an entry after this entry
		 */
		void insertAfterThis(IntListEntry e) {
			IntListEntryImpl f = (IntListEntryImpl)e;
			f.previous = this;
			f.next = this.next;
			if (this.next != null)
				this.next.previous = f;
			this.next = f;
		}	

		/**
		 * Insert an item after this entry
		 */
		void insertAfterThis(int item) {
			IntListEntryImpl e = new IntListEntryImpl(item);
			insertAfterThis(e);
		}	
		
	}	
	
	private IntListEntryImpl first = null;
	private IntListEntryImpl last = null;
	private int length = 0;

	/**
	 * Test whether list is empty
	 */
	public boolean isEmpty() {
		return first == null;
	}

	/**
	 * Return length of list
	 */
	public int length() {
		return length;
	}
		
	/**
	 * Get first entry of list
	 */
	public IntListEntry getFirst() {
		return first;
	}

	/**
	 * Get last entry of list
	 */
	public IntListEntry getLast() {
		return last;
	}
	
	/**
	 * Append an entry to the list
	 */
	public void append(IntListEntry e) {
		IntListEntryImpl f = (IntListEntryImpl)e;
		f.previous = null;
		f.next = null;
		if (last!=null)
			last.insertAfterThis(f);
		last = f;
		if (first==null)
			first = f;
		length++;
	}
	
	/**
	 * Append an item to the list
	 */
	public void append(int item) {
		IntListEntryImpl e = new IntListEntryImpl(item);
		append(e);
	}

	/**
	 * Append two lists retiring the second
	 */
	public void append(IntList l) {
		if (l.isEmpty())
			return;
		SimpleIntList m = (SimpleIntList)l;
		if (this.isEmpty()) {
			this.first = m.first;
			this.last = m.last;
			this.length = m.length;
		}
		else 
		{
			this.last.next = m.first;
			m.first.previous = this.last;
			this.length += m.length;
		}
		m.first = null;
		m.last = null;
		m.length = 0;
	}	
	
	/**
	 * Prepend an entry to the list
	 */
	public void prepend(IntListEntry e) {
		IntListEntryImpl f = (IntListEntryImpl)e;
		f.previous = null;
		f.next = null;
		if (first!=null)
			first.insertBeforeThis(f);
		first = f;
		if (last==null)
			last = f;
		length++;
	}
	
	/**
	 * Prepend an item to the list
	 */
	public void prepend(int item) {
		IntListEntryImpl e = new IntListEntryImpl(item);
		prepend(e);
	}
	
	/** 
	 * Delete an entry from the list
	 */
	public void delete(IntListEntry e) {
		IntListEntryImpl f = (IntListEntryImpl)e;
		if (first == f)
			first = f.next;
		if (last == f)
			last = f.previous;
		f.delete();
		length--;
	}

	/**
	 * Insert an entry after another entry
	 */
	public void insertBefore(IntListEntry e, IntListEntry x) {
		if (x==first)
			prepend(e);
		else {
			IntListEntryImpl y = (IntListEntryImpl)x;
			y.insertBeforeThis(e);
			length--;
		}
	}
	
	/**
	 * Insert an entry after another entry
	 */
	public void insertAfter(IntListEntry e, IntListEntry x) {
		if (x==last)
			append(e);
		else {
			IntListEntryImpl y = (IntListEntryImpl)x;
			y.insertAfterThis(e);
			length--;
		}
	}

	/**
	 * Cut before an entry
	 */
	public IntList cutBefore(IntListEntry e) {
		IntListEntryImpl f = (IntListEntryImpl)e;
		SimpleIntList l = new SimpleIntList();
		l.first = f;
		l.last = this.last;
		this.last = f.previous;
		f.previous = null;
		this.updateLength();
		l.updateLength();
		return l;
	}	
	
	private void updateLength() {
		int i = 0;
		IntListEntry e = first;
		while (e != null) {
			i++;
			e = e.getNext();
		}
	}	
	
	/**
	 * Print a list forward
	 */
	public void printForward() {
		IntListEntry current = first;
		while (current != null) {
			System.out.println(current.getItem());
			current = current.getNext();
		}
	}

	/**
	 * Print a list backward
	 */
	public void printBackward() {
		IntListEntry current = last;
		while (current != null) {
			System.out.println(current.getItem());
			current = current.getPrevious();
		}
	}
		
	public static void main(String[] args) {
		SimpleIntList l = new SimpleIntList();
		l.append(0);
		l.append(1);
		l.append(2);
		l.append(3);
		l.printForward();
		l.printBackward();
	}	
}
