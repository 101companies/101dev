// (C) 2009 Ralf Laemmel

package oo.gui.exit;

import java.awt.event.*;

/** 
 * A listener to be attached to a button.
 * The action makes the application exit.
 * (We are not using nested classes in this sample suite.)
 */
public class ExitAction implements ActionListener {
	public void actionPerformed(ActionEvent event) {
		System.out.println("Exiting ...");
		System.exit(0);
	}
}