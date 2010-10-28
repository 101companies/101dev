// (C) 2008 Ralf Laemmel

package oo.shapes.point;

import java.io.PrintStream;

/** 
 * Rectangles as a kind of shape
 */
class Rectangle implements Shape {
	
	// Private state
	private Point origin;
	private int width;
	private int height;

	// Constructor
	public Rectangle(Point origin, int width, int height) {
		moveTo(origin);
		setWidth(width);
	    setHeight(height);
	}
	
	// Accessors
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public void setWidth(int width) { this.width = width; }
	public void setHeight(int height) { this.height = height; }

	public void moveTo(Point origin) {
		this.origin = origin;
	}

	public void moveBy(int deltax, int deltay) {
		moveTo(new Point(origin.getX()+deltax,origin.getY()+deltay));
	}	
	
	public void draw(PrintStream s) {
		s.println(
				"Drawing a Rectangle at: " + origin.toString() +
				" with width = " + getWidth() +
				" with height = " + getHeight());
	}
}
