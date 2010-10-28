// (C) 2009 Ralf Laemmel

package oo.shapes.awtish;

import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;

public class Figure implements Iterable<Shape> {

	private List<Shape> shapes = new LinkedList<Shape>();

	/** Iterator for aggregated figures */
	public Iterator<Shape> iterator() {
		return shapes.iterator();
	}
	
	/** Factory method for rectangles */
	public Rectangle createRectangle(int x, int y, int width, int height) {
		Rectangle r = new Rectangle(x,y,width,height);
		shapes.add(r);
		return r;
	}

	/** Factory method for circles */
	public Circle createCircle(int x, int y, int radius) {
		Circle c = new Circle(x,y,radius);
		shapes.add(c);
		return c;
	}

	/** Create an applet and draw the figure */
	public void draw() {
		new ShapesPanel(this);
	    try {
	    	Thread.sleep(1000); // TODO: use synchronous draw
	    } catch (InterruptedException x) {
	    	// Don't care.
	    }
	}
	
	public void draw(Graphics2D g2) {
		for (Shape s : shapes) 
			s.draw(g2);
	}
}
