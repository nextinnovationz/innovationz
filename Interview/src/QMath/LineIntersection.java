package QMath;

public class LineIntersection {
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
	
	public static boolean intersect(Line line1, Line line2, double threshold) {
		//two lines are parallel when their slopes are the same
		//so check for different slopes and they will intersect at some point
		//or if their y-int (when x=0) are the same so they both cross 
		//the y-axis at the same point (same x=0 and same y)
		//since double may have overflow, use a threshold (less than 0) to decide equality
		
		return (((line1.infiniteSlope != line2.infiniteSlope) && (line1.slope - line2.slope > threshold)) || (line1.yint - line2.yint < threshold));
	}
}
