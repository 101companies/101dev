package algorithm.sorting;

import java.util.*;

public class ListBased {

	/**
	 * Selection sort with standard linked list.
	 * The input is consumed, i.e., the list is emptied.
	 * Note on complexity: the list is traversed twice for each new minimum.
	 */
	public static List<Integer> selectionSort(List<Integer> input) {
		List<Integer> output = new LinkedList<Integer>();
		while (!input.isEmpty()) {
			Integer x = null;
			for (Integer y : input)
				if (x==null || y < x)
					x = y;
			Iterator<Integer> i = input.iterator();
			while (true)
				if (i.next() == x) {
					i.remove();
					break;
				}
			output.add(x);
		}
		return output;
	}

	public static void main(String[] args) {
		List<Integer> l = new LinkedList<Integer>();
		l.add(1);
		l.add(3);
		l.add(2);
		l.add(-129);
		l.add(128);
		System.out.println(l);
		System.out.println(selectionSort(l));
	}

}
