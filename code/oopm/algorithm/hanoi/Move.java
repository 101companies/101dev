// (C) 2009 Ralf Laemmel

package algorithm.hanoi;

public final class Move {
	public int disc;
	public String from;
	public String to;
	public Move(int disc, String from, String to) {
		this.disc = disc;
		this.from = from;
		this.to = to;
	}
	public String toString() {
		return "Move(" + disc + "," + from + "," + to + ")";
	}
	public boolean equals(Object o) {
		if (!(o instanceof Move))
			return false;
		Move m = (Move)o;
		return	this.disc == m.disc
			&& 	this.from.equals(m.from)
			&& 	this.to.equals(m.to);
	}
}
