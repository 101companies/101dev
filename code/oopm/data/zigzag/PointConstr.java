// (C) 2009 Ralf Laemmel

package data.zigzag;

/**
 * A point class with a useful constructor
 */
public class PointConstr {

	private double x, y;
	
	public PointConstr(double x, double y) {
		setX(x);
		setY(y);
	}
	
	public double getX() { return x; }
	public double getY() { return y; }
	public void setX(double newx) { x = newx; }
	public void setY(double newy) { y = newy; }
	
	public double distanceTo(PointConstr p) {
		double deltax = x - p.x;
		double deltay = y - p.y;		
		return Math.sqrt(deltax*deltax + deltay*deltay);
	}
	
	public static void main(String[] args) {
		PointConstr p = new PointConstr(3,4);
		System.out.println(p.distanceTo(p));
	}
}
