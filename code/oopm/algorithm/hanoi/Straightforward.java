// (C) 2009 Ralf Laemmel

package algorithm.hanoi;

/**
 * Recursive solution of Towers of Hanoi
 */
public class Straightforward {

	/*
	 *  Solves the Towers of Hanoi problem on N discs. The discs are labeled
     *  in increasing order of size from 1 to N and the poles are labeled
     *  A, B, and C. Here is a solution sequence for 3 discs:
     *
     *  Move disc 1 from A to C
     *  Move disc 2 from A to B
     *  Move disc 1 from C to B
     *  Move disc 3 from A to C
     *  Move disc 1 from B to A
     *  Move disc 2 from B to C
     *  Move disc 1 from A to C
     *
	 */
	public static void move(int n, String from, String temp, String to) {
		if (n == 0)
			return;
		move(n - 1, from, to, temp);
		System.out.println("Move disc " + n + " from " + from + " to " + to);
		move(n - 1, temp, from, to);
	}

	public static void main(String[] args) {
		move(3, "A", "B", "C");
	}

}
