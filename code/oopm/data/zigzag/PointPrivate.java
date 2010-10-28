// (C) 2009 Ralf Laemmel

package data.zigzag;

/**
 * A point class that does proper encapsulation
 */
public class PointPrivate {

	private double x, y;
		
	public double getX() { return x; }
	public double getY() { return y; }
	public void setX(double newx) { x = newx; }
	public void setY(double newy) { y = newy; }
	
	public double distanceTo(PointPrivate p) {
		double deltax = x - p.x;
		double deltay = y - p.y;		
		return Math.sqrt(deltax*deltax + deltay*deltay);
	}
}
