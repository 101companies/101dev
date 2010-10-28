// (C) 2008 Ralf Laemmel

package oo.shapes.iop;

import java.io.PrintStream;

/**
 * The interface for all kinds of shapes
 */
public interface Shape {
    public void moveTo(int x, int y);
    public void moveBy(int deltax, int deltay);
    public void draw(PrintStream s);   
}
