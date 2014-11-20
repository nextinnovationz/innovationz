package QMath;

import java.util.HashMap;

public class LineWithMostPoints {
	public static void main(String[] args) {
		
	}
	
	static class Point {
		public int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class Line {
		public Point[] points;
		public double slope, yint;
		public boolean infiniteSlope;
		public static final double EPSILON = 0.0000001;
		
		public Line (Point p1, Point p2) {
			this.points[0] = p1;
			this.points[1] = p2;
			
			if(isEqual(p1.x, p2.x)) {  //no run so this is a vertical line 
				//slope cannot be divided by 0 so it has infinite slope
				this.slope = 0.0;
				this.infiniteSlope = true;
				
				//line does not cross y-axis since its vertical at an x
				this.yint = p1.x;
			} else {  //has run so this is not a vertial line
				//slope can be divided by nonzero number 
				this.slope = (p2.y-p1.y)/(p2.x-p1.x);
				this.infiniteSlope = false;
				
				//y=mx+b
				//b=-mx+y or b=y-mx (x=0, y=b is where line cross y-axis)
				this.yint = p1.y-(slope*p1.x); 
			}
		}
		
		//since double values have higher precision than integers,
		//need to do (abs(a-b) < epsilon) to check if a and b are close enough to be considered the same
		//this means that the absolute difference between the two double is smaller than the threshold
		//so they are essentially the same value
		public boolean isEqual(double a, double b) {
			return Math.abs(a-b) < EPSILON;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(!(obj instanceof Line))
				return false;
			else if(obj == this)
				return true;
			else {
				//two lines are the same if they have same slope and yint
				Line line = (Line)obj;
				return (isEqual(slope, line.slope) && isEqual(yint, line.yint) && (infiniteSlope == line.infiniteSlope));
			}
		}
		
		@Override
		public int hashCode() {
			return (int)(slope*1000) | (int)(yint*1000); 
		}
	}
	
	public static Line bestLine(Point[] points) {
		HashMap<Line,Integer> lines = new HashMap<Line,Integer>();
		int len = points.length;
		Line bestLine = null;
		for(int i = 0; i < len; ++i) {
			for(int j = i+1; j < len; ++j) {
				Line line = new Line(points[i], points[j]);
				if(lines.containsKey(line)) {
					lines.put(line, lines.get(line)+1);
				} else {
					lines.put(line, 1);
				}
				
				if(lines.get(line) > lines.get(bestLine)) {
					bestLine = line;
				}
			}
		}
		
		return bestLine;
	}
}
