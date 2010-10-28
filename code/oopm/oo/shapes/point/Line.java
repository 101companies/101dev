// (C) 2008 Ralf Laemmel

package oo.shapes.point;

import java.io.PrintStream;

/** 
 * Lines as a kind of shape
 */
class Line implements Shape {
	
	// Private state
	private Point origin;
	private Point end;

	// Constructor
	public Line(Point origin, Point end) {
		this.origin = origin;
		this.end = end;
	}
	
	// Accessors
	public Point getEnd() { return end; }
	public void setEnd(Point end) { this.end = end; }

	public void moveTo(Point origin) {
		int deltax = origin.getX() - this.origin.getX();
		int deltay = origin.getY() - this.origin.getY();
		moveBy(deltax,deltay);
	}

	public void moveBy(int deltax, int deltay) {
		origin = new Point(origin.getX()+deltax,origin.getY()+deltay);
		end = new Point(end.getX()+deltax,end.getY()+deltay);
	}	
	
	public void draw(PrintStream s) {
		s.println(
				"Drawing a Line from: " + origin.toString() +
				" to: " + end.toString());
	}
}
