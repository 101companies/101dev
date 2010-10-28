// (C) 2008 Ralf Laemmel

package oo.shapes.iop;

import java.io.PrintStream;

/** 
 * Circle as a kind of shape
 */
class Circle implements Shape {

	// Private state
	private int x;
	private int y;
	private int radius;

	// Constructor
	public Circle(int x, int y, int radius) {
		setX(x);
		setY(y);
		setRadius(radius);
	}

	// Accessors
	public int getX() { return x; }
	public int getY() { return y; }
	public int getRadius() { return radius; }
	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }
	public void setRadius(int radius) { this.radius = radius; }

	public void moveTo(int x, int y) {
		setX(x);
		setY(y);
	}

	public void moveBy(int deltax, int deltay) {
		setX(x+deltax);
		setY(y+deltay);
	}	
	
	public void draw(PrintStream s) {
		s.println(
				"Drawing a Circle at:(" + getX() + ", " + getY() +
				"), radius " + getRadius());
	}
}
