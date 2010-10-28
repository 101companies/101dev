// (C) 2008 Ralf Laemmel

package oo.shapes.simple;

import java.io.PrintStream;

// The abstract base class of all kinds of shapes
public abstract class Shape {
	
	// Private state
    private int x;
    private int y;

    // Construction is invoked by concrete subclasses
    protected Shape(int x, int y) { moveTo(x, y); }
    
    // Getters and setters for coordinates
    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

    // Move the shape, absolutely
    public void moveTo(int x, int y) { setX(x); setY(y); }
    
    // Move the shape, relatively
    public void moveBy(int deltax, int deltay) {
        moveTo(getX() + deltax, getY() + deltay);
    }

    // Draw the shape
    public abstract void draw(PrintStream s);   
}
