// (C) 2008 Ralf Laemmel

package oo.shapes.simple;

public class Program {

	public static void main(String[] args) {
		
	      	// Construct a list of shapes
	      	Shape[] scribble = new Shape[2];
	      	scribble[0] = new Rectangle(10, 20, 5, 6);
	      	scribble[1] = new Circle(15, 25, 8);

	      	// Handle the shapes in the list polymorphically
	      	for (int i = 0; i < scribble.length; i++) {
	      		scribble[i].draw(System.out);
	      		scribble[i].moveBy(100, 100);
	      		scribble[i].draw(System.out);
	      	}
	      
	      	// Test the effect of the moves
	      	assert scribble[0].getX() == 110;
	      	assert scribble[1].getX() == 115;

	      	// Call a rectangle specific function
	      	Rectangle arect = new Rectangle(0, 0, 15, 15);
	      	arect.setWidth(30);
	      	arect.draw(System.out);       
	}
}
