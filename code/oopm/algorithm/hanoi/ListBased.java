// (C) 2009 Ralf Laemmel

package algorithm.hanoi;

import java.util.*;

/**
 * Iterator-based approach to the towers of Hanoi
 */
public class ListBased {

	/*
	 *  Solves the Towers of Hanoi problem on N discs. The discs are labeled
     *  in increasing order of size from 1 to N and the poles are labeled
     *  A, B, and C. Here is a solution sequence for 3 discs:
     *
     * [ 	Move(1,A,C),
     *  	Move(2,A,B),
     *  	Move(1,C,B),
     *  	Move(3,A,C),
     *  	Move(1,B,A),
     *  	Move(2,B,C),
     *  	Move(1,A,C)		]
     *
	 */
	public static List<Move> move(int n, String from, String temp, String to) {
		List<Move> l = new LinkedList<Move>();
		move(l, n, from, temp, to);
		return l;
	}

	private static void move(List<Move> l, int n, String from, String temp,
			String to) {
		if (n == 0)
			return;
		move(l, n - 1, from, to, temp);
		Move m = new Move(n, from, to);
		l.add(m);
		move(l, n - 1, temp, from, to);
	}

	public static void main(String[] args) {
		List<Move> l = move(3, "A", "B", "C");
		System.out.println(l);
	}

}
