package QMath;

public class OverlapRectangles {
	public static void main(String[] args) {
		
	}
	
	static class Rectangle {
		int width, height;
		Point upperLeft, upperRight, lowerLeft, lowerRight;
		
		public Rectangle(int width, int height, Point upperLeft, Point upperRight, Point lowerLeft, Point lowerRight) {
			this.width = width;
			this.height = height;
			this.upperLeft = upperLeft;
			this.upperRight = upperRight;
			this.lowerLeft = lowerLeft;
			this.lowerRight = lowerRight;
		}
	}
	
	static class Point {
		int x, y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static boolean isOverlap(Rectangle a, Rectangle b) {
		//too much to check for overlap
		//check for not overlap instead
		
		//a is above b or b is above a
		if(a.lowerLeft.y < b.upperLeft.y || b.lowerLeft.y < a.lowerLeft.y)
			return false;
		
		//a is left of b or b is left of a
		if(a.upperRight.x < b.upperLeft.x || b.upperRight.x < a.upperRight.x)
			return false;
					
		//all other cases, they must overlap
		return true;
	}
}
