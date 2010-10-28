// (C) 2009 Ralf Laemmel

package data.zigzag;

/**
 * An inappropriate point class (with public attributes)
 */
public class PointPublic {

	public double x, y;
	
	public double distanceTo(PointPublic p) {
		double deltax = x - p.x;
		double deltay = y - p.y;		
		return Math.sqrt(deltax*deltax + deltay*deltay);
	}
}
