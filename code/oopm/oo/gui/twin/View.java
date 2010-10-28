// (C) 2009 Ralf Laemmel

package oo.gui.twin;

import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * A class that encapsulates the GUI of our counter application.
 * (We are not using nested classes in this sample suite.)
 */
public class View 
		implements
			ActionListener,
			PropertyChangeListener {

	private Counter counter;
	private JFrame frame;
	private JPanel panel;
	private JButton step;
	private JButton reset;
	
	public View(Counter counter, int x, int y) {
		
		counter.addListener(this);
		this.counter = counter;
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(x,y);
		updateTitle();

		panel = new JPanel();
		frame.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		step = new JButton("Step");
		step.addActionListener(this);
		panel.add(step);
		
		reset = new JButton("Reset");
		reset.addActionListener(this);
		panel.add(reset);
		
		frame.pack();
	    frame.setVisible(true);		
	}
	
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == step) {
			counter.step();
			updateTitle();
			return;
		}
		if (event.getSource() == reset) {
			counter.reset();
			updateTitle();
			return;
		}
	}
	
	public void updateTitle() {
		frame.setTitle(Integer.toString(counter.getCount()));		
	}
	
	public void propertyChange(PropertyChangeEvent event) {
		if (event.getSource() == counter 
		&&	event.getPropertyName().equals("count"))
			updateTitle();
	}
	
}