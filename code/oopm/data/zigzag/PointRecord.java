// (C) 2009 Ralf Laemmel

package data.zigzag;

/**
 * A point class that is more of record type.
 * All operations on points are indeed (unusally) represented as *static* methods.
 */
public class PointRecord {

	public double x, y;

	public static double distance(PointRecord p1, PointRecord p2) {
		double deltax = p1.x - p2.x;
		double deltay = p1.y - p2.y;		
		return Math.sqrt(deltax*deltax + deltay*deltay);
	}

	public static double length(PointRecord[] line) {
		double result = 0;
		for (int i=1; i<line.length && line[i] != null; i++)
			result += distance(line[i-1],line[i]);
		return result;
	}
	
	public static void main(String[] args) {
		PointRecord p1 = new PointRecord();
		p1.x = 3;
		p1.y = 4;
		PointRecord p2 = new PointRecord();
		p2.x = 6;
		p2.y = 8;
		System.out.println(distance(p1, p2)); // prints 5
	}
}
