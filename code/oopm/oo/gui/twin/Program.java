// (C) 2009 Ralf Laemmel

package oo.gui.twin;

/**
 * A tiny application to provide a counter with a GUI.
 * In fact, the GUI provides two identical panels.
 * This way two users could count simultaneously.
 * Extra event handling ensures the consistency of both panels.
 */
public class Program {
	
	public static void main(String[] args) {
		Counter counter = new Counter();
		new View(counter,100,100);
		new View(counter,200,150);
	}

}
