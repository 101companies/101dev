// (C) 2009 Ralf Laemmel

package oo.shapes.awtish;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/** 
 * Rectangles as a kind of shape
 */
class Rectangle extends Shape {
	
	// Private state
	private int width;
	private int height;

	// Constructor
	Rectangle(int x, int y, int width, int height) {
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
	public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
		g2.draw(new Rectangle2D.Double(
				getX(), getY(), getWidth(), getHeight()));		
	}
}
