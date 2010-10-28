// (C) 2008 Ralf Laemmel

package oo.shapes.simple;

import java.io.PrintStream;

/** 
 * Rectangles as a kind of shape
 */
class Rectangle extends Shape {
	
	// Private state
	private int width;
	private int height;

	// Constructor
	public Rectangle(int x, int y, int width, int height) {
	      super(x, y);
	      setWidth(width);
	      setHeight(height);
	}
	
	// Accessors for the width & height
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public void setWidth(int width) { this.width = width; }
	public void setHeight(int height) { this.height = height; }

	/**
	 *  Draw the rectangle
	 */
	public void draw(PrintStream s) {
		s.println(
				"Drawing a Rectangle at:(" + getX() + ", " + getY() +
				"), width " + getWidth() + ", height " + getHeight());
	}
}
