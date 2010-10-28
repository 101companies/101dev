// (C) 2008 Ralf Laemmel

package oo.shapes.point;

import java.io.PrintStream;

/**
 * The interface for all kinds of shapes
 */
public interface Shape
{
    public void moveTo(Point origin);
    public void moveBy(int deltax, int deltay);
    public void draw(PrintStream s);   
}
