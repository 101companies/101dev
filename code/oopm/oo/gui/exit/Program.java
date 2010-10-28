// (C) 2009 Ralf Laemmel

package oo.gui.exit;

import javax.swing.*;

/**
 * Bring up a frame with a button to exit the application.
 * The button has a listener attached to it that does the exiting.
 */
public class Program {
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton b = new JButton("Exit");
		f.add(b);
		f.pack();
		b.addActionListener(new ExitAction());
	    f.setVisible(true);		
	}

}
