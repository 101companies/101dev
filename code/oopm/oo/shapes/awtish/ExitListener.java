// (C) 2009 Ralf Laemmel

package oo.shapes.awtish;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/** 
 * A listener to be attached to frames et al: quitting the frame quits the program.
 * A more sophisticated programmer would use an inner class.
 */
public class ExitListener extends WindowAdapter {
	public void windowClosing(WindowEvent event) {
		System.exit(0);
	}
}
