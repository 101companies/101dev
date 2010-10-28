// (C) 2009 Ralf Laemmel

package oo.shapes.awtish;

import java.awt.Graphics;

/**
 * The abstract base class of all kinds of shapes
 */
public abstract class Shape
{
	// Private state
    private int x;
    private int y;
    
    /**
     * Since this class is abstract, the constructor is invoked only indirectly.
     * @param  newx     initial x coordinate    
     * @param  newy     initial y coordinate
     */    
    Shape(int newx, int newy) {
        moveTo(newx, newy);
    }
    
    /** Getter for x coordinate */
    public int getX() { return x; }
    /** Getter for y coordinate */
    public int getY() { return y; }
    /** Setter for x coordinate */
    public void setX(int newx) { x = newx; }
    /** Setter for y coordinate */
    public void setY(int newy) { y = newy; }

    /** Move the shape, absolutely */
    public void moveTo(int newx, int newy) {
        setX(newx);
        setY(newy);
    }
    
    /** Move the shape, relatively */
    public void moveBy(int deltax, int deltay) {
        moveTo(getX() + deltax, getY() + deltay);
    }

    /** Draw the shape */
    public abstract void draw(Graphics g);   
       
}
