// (C) 2008 Ralf Laemmel

package oo.shapes.iop;

public class Program {

	public static void main(String[] args) {
		
	      	// Create some shape instances
	      	Shape[] scribble = new Shape[2];
	      	scribble[0] = new Rectangle(10, 20, 5, 6);
	      	scribble[1] = new Circle(15, 25, 8);

	      	// Iterate through the list and handle shapes polymorphically
	      	for (int i = 0; i < scribble.length; i++) {
	      		scribble[i].draw(System.out);
	      		scribble[i].moveBy(100, 100);
	      		scribble[i].draw(System.out);
	      	}
	      
	      	// Call a rectangle specific function
	      	Rectangle arect = new Rectangle(0, 0, 15, 15);
	      	arect.setWidth(30);
	      	arect.draw(System.out);       
	      	
	}
}
