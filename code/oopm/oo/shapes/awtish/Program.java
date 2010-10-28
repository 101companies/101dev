// (C) 2009 Ralf Laemmel

package oo.shapes.awtish;

import java.io.File;
import java.util.Iterator;

public class Program {

	public static void main(String[] args) throws Exception {
		
		// Parse figure from file
		String pathname =
			  "oo" + 
			  File.separatorChar + 
			  "shapes" + 
			  File.separatorChar + 
			  "awtish" +
			  File.separatorChar + 
			  "shapes.xml";
		Figure f = XmlParser.parse(pathname);
		
		// Capture shapes from figure
		Iterator<Shape> i = f.iterator();
		Rectangle r1 = (Rectangle)i.next();
		Rectangle r2 = (Rectangle)i.next();
		Circle c = (Circle)i.next();
			
		// Show snapshot
		f.draw();
		
		// Move shapes around
		r1.moveBy(10, 10);
		r2.moveBy(35, 35);
		c.moveTo(60, 60);

		// Show another snapshot
		f.draw();
	}
}
