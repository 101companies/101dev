// (C) 2009 Ralf Laemmel

package oo.shapes.awtish;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/** 
 * Circle as a kind of shape
 */
class Circle extends Shape {

	// Private state
	private int radius;

	// Constructor
	Circle(int x, int y, int radius) {
		super(x, y);
		setRadius(radius);
	}

	// Accessors for the radius
	public int getRadius() { return radius; }
	public void setRadius(int radius) { this.radius = radius; }

	/**
	 *  Draw the circle
	 */
	public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
		g2.draw(new Ellipse2D.Double(
				getX()-getRadius(), getY()-getRadius(), 
				2 * getRadius(), 2 * getRadius()));		
	}
}
