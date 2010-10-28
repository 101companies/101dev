// (C) 2009 Ralf Laemmel

package data.zigzag;

/**
 * A class that models zigzag lines (consisting of many points.
 */
public class Line {

	private PointPrivate[] line = new PointPrivate[42];

	public void add(PointPrivate p) {
		int i;
		for (i=0; i<line.length && line[i]!=null; i++) {}
		line[i] = p;
	}

	public double length() {
		double result = 0;
		for (int i=1; i<line.length && line[i] != null; i++)
			result += line[i-1].distanceTo(line[i]);
		return result;
	}	
	
	public static void main(String[] args) {
		// Tests omitted
	}

}
