// (C) 2009 Ralf Laemmel

package oo.shapes.awtish;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ShapesPanel extends JPanel {

	private static int counter = 1;
	private Figure f;
	
	public ShapesPanel(Figure f) {
		this.f = f;
	    JFrame frame = new JFrame("Shapes Demo " + counter);
	    counter++;
	    frame.setSize(256, 256);
	    frame.setLocation(counter*100, counter*100);
	    frame.setContentPane(this);
	    frame.addWindowListener(new ExitListener());
	    frame.setVisible(true);		
	}
	
	/** Draw all shapes of the figure */
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
	    f.draw(g2);
	}

}
