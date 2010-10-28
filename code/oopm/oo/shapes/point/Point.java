package oo.shapes.point;

public class Point {
	private int x, y;
	public Point(int x, int y) { this.x = x; this.y = y; }
	int getX() { return x; }
	int getY() { return y; }
    void setX(int x) { this.x = x; }
	void setY(int y) { this.y = y; }
	public String toString() {
		return "Point("+getX()+","+getY()+")";
	}
}
