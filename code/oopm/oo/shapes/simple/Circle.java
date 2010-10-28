// (C) 2009 Ralf Laemmel

package oo.shapes.simple;

import java.io.PrintStream;

// Circle as a kind of shape
class Circle extends Shape {

	// Private state
	private int radius;

	// Constructor
	public Circle(int x, int y, int radius) {
		super(x, y);
		setRadius(radius);
	}

	// Getter and setter for the radius
	public int getRadius() { return radius; }
	public void setRadius(int radius) { this.radius = radius; }

	// Draw the circle
	public void draw(PrintStream s) {
		s.println(
			"Drawing a Circle at:(" 
			+ getX() + ", " + getY() 
			+ "), radius " + getRadius());
	}
}
