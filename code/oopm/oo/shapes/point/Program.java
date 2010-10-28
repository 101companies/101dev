// (C) 2008 Ralf Laemmel

package oo.shapes.point;

public class Program {

	public static void main(String[] args) {
		
	      	// Create some shape instances
	      	Shape[] scribble = new Shape[2];
	      	scribble[0] = new Rectangle(new Point(10, 20), 5, 6);
	      	scribble[1] = new Circle(new Point(15, 25), 8);

	      	// Iterate through the list and handle shapes polymorphically
	      	for (int i = 0; i < scribble.length; i++) {
	      		scribble[i].draw(System.out);
	      		scribble[i].moveBy(100, 100);
	      		scribble[i].draw(System.out);
	      	}
	      
	      	// Call a rectangle specific function
	      	Rectangle arect = new Rectangle(new Point(0, 0), 15, 15);
	      	arect.draw(System.out);       
	      	arect.setWidth(30);
	      	arect.draw(System.out);       
	      	
	      	// Test the line implementation
	      	Line line = new Line(new Point(0,0), new Point(100,100));
	      	line.draw(System.out);
	      	line.moveTo(new Point(10, 20));
	      	line.draw(System.out);
	}
}
