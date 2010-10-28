// (C) 2009 Ralf Laemmel

package data.zigzag;

/**
 * A point class that puts to use "this".
 * Indeed, "this" is circumvented in the other variations in this package.
 */
public class PointThis {

	private double x, y;
	
	public PointThis(double x, double y) {
		setX(x);
		setY(y);
	}
	
	public double getX() { return x; }
	public double getY() { return y; }
	public void setX(double x) { this.x = x; }
	public void setY(double y) { this.y = y; }
	
	public double distanceTo(PointThis p) {
		double deltax = x - p.x;
		double deltay = y - p.y;		
		return Math.sqrt(deltax*deltax + deltay*deltay);
	}
	
	public static void main(String[] args) {
		PointThis p = new PointThis(3,4);
		System.out.println(p.distanceTo(p));
	}
}
