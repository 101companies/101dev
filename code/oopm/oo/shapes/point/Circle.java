// (C) 2008 Ralf Laemmel

package oo.shapes.point;

import java.io.PrintStream;

/** 
 * Circle as a kind of shape
 */
class Circle implements Shape {

	// Private state
	private Point origin;
	private int radius;

	// Constructor
	public Circle(Point origin, int radius) {
		moveTo(origin);
		setRadius(radius);
	}

	// Accessors
	public int getRadius() { return radius; }
	public void setRadius(int radius) { this.radius = radius; }

	public void moveTo(Point origin) {
		this.origin = origin;
	}

	public void moveBy(int deltax, int deltay) {
		moveTo(new Point(origin.getX()+deltax,origin.getY()+deltay));
	}	
	
	public void draw(PrintStream s) {
		s.println(
				"Drawing a Circle at: " + origin.toString() +
				" with radius = " + getRadius());
	}
}
