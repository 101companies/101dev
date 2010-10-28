// (C) 2009 Ralf Laemmel

package algorithm.sorting;

import data.list.doublylinked.IntList;
import data.list.doublylinked.IntListEntry;
import data.list.doublylinked.SimpleIntList;

public class DoublyLinkedListBased {
	
	/**
	 * SelectionSort.
	 * Consume (destruct) input list; produce (construct) output list.
	 */
	public static IntList selectionSort1(IntList input) {
		IntList output = new SimpleIntList();
		while (!input.isEmpty()) {
			IntListEntry min = input.getFirst();
			for (IntListEntry e = input.getFirst(); e != null; e = e.getNext())
				if (e.getItem()<min.getItem())
					min = e;
			input.delete(min);
			output.append(min);
		}
		return output;
	}

	/**
	 * SelectionSort.
	 * Move elements around in the input list.
	 */
	public static void selectionSort2(IntList l) {
		IntListEntry input = l.getFirst();
		IntListEntry output = null;
		while (input != null) {
			IntListEntry min = input;
			for (IntListEntry e = input; e != null; e = e.getNext())
				if (e.getItem()<min.getItem())
					min = e;
			if (min != input) {
				l.delete(min);
				if (output == null)
					l.prepend(min);
				else
					l.insertAfter(min,output);
			}
			output = min;
			input = min.getNext();
		}
	}	

	/**
	 * SelectionSort.
	 * Consume (destruct) input list; produce (construct) output list.
	 */
	public static IntList insertionSort1(IntList input) {
		IntList output = new SimpleIntList();
		l1: while (!input.isEmpty()) {
			IntListEntry first = input.getFirst();
			input.delete(first);
			for (IntListEntry e = output.getFirst(); e != null; e = e.getNext())
				if (e.getItem() > first.getItem()) {
					output.insertBefore(first, e);
					continue l1;					
				}
			output.append(first);
		}
		return output;
	}	
	
	/**
	 * InsertionSort.
	 * Move elements around in the input list.
	 */
	public static void insertionSort2(IntList l) {
		IntListEntry input = l.getFirst();
		l1:	while (input != null) {
			for (IntListEntry e = l.getFirst(); e != input; e = e.getNext())
				if (e.getItem() > input.getItem()) {
					IntListEntry next = input.getNext();
					l.delete(input);
					l.insertBefore(input, e);
					input = next;
					continue l1;
				}
			input = input.getNext();
		}
	}
	
	/**
	 * BubbleSort.
	 * Move elements around in the input list.
	 */
	public static void bubbleSort(IntList l) {
		if (l.getFirst() == null)
			return;
		l1: while (true) {
			for (	IntListEntry e = l.getFirst().getNext();
					e != null;
					e = e.getNext()) {
				IntListEntry d = e.getPrevious();
				if (d.getItem() > e.getItem()) {
					l.delete(e);
					l.insertBefore(e, d);
					continue l1;
				}
			}
			break;
		}
	}
		
	/**
	 * MergeSort.
	 * Create many intermediate lists for parts.
	 * Consume input list.
	 * The purity and aliasing contact of this implementation is a mess.
	 * This implementation is merely here to bring up these issues.
	 */
	public static IntList mergeSort(IntList input) {
		if (input.length() <= 1)
			return input;
		IntList part1 = new SimpleIntList();
		IntList part2 = new SimpleIntList();
		divide(input,part1,part2);
		part1 = mergeSort(part1);
		part2 = mergeSort(part2);
		return merge(part1,part2);
	}
	
	public static void divide(IntList input, IntList part1, IntList part2) {
		while (!input.isEmpty()) {
			IntListEntry x;
			x = input.getFirst();
			input.delete(x);
			part1.append(x);
			if (input.isEmpty()) 
				break;
			x = input.getFirst();
			input.delete(x);
			part2.append(x);
		}
	}

	public static IntList merge(IntList part1, IntList part2) {
		IntList result = new SimpleIntList();
		while (!part1.isEmpty() || !part2.isEmpty()) {
			IntListEntry x = part1.getFirst();
			IntListEntry y = part2.getFirst();
			if ((x != null && y != null && x.getItem() < y.getItem()) || y == null) {
				part1.delete(x);
				result.append(x);
			}
			else {
				part2.delete(y);
				result.append(y);				
			}
		}
		return result;
	}
	
	/**
	 * A sample list
	 */
	public static IntList myList() {
		SimpleIntList l = new SimpleIntList();
		l.append(3);
		l.append(0);
		l.append(5);
		l.append(2);
		l.append(1);
		l.append(6);
		l.append(8);
		l.append(4);
		l.append(7);
		return l;
	}
	
	public static void main(String[] args) {
		IntList l;
		l = myList();
		l = selectionSort1(l);
		System.out.println("selectionSort1");
		l.printForward();
		l = myList();
		selectionSort2(l);
		System.out.println("selectionSort2");
		l.printForward();
		l = myList();
		l = insertionSort1(l);
		System.out.println("insertionSort1");
		l.printForward();	
		l = myList();
		insertionSort2(l);
		System.out.println("insertionSort2");
		l.printForward();	
		l = myList();
		bubbleSort(l);
		System.out.println("bubbleSort");
		l.printForward();	
		l = myList();
		l = mergeSort(l);
		System.out.println("mergeSort");
		l.printForward();	
	}
}