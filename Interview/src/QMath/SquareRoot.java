package QMath;

public class SquareRoot {
	public static void main(String[] args) {
		double num = 25.0;
		double ep = 0.000001;
		System.out.println(sqrt(num, ep));
	}
	
	public static double sqrt(double num, double epsilon) {
		double left = 0, right = num;  //range to do binary search to find sqrt(num) -> Newton's Method
		double mid = num/2;  //start at from 0 to num inclusive, get midpoint in this range
		double midSqu = mid*mid;  //see if mid is sqrt(num) by checking mid^2 == num
		
		//check if the difference between real sqrt(num) and mid is within acceptable range
		//to be considered the same based on epsilon threshold
		//their difference must be smaller than the acceptable threshold to be considered the same
		while(Math.abs(midSqu - num) > epsilon) {  
			if(midSqu > num) {  //mid^2 is greater than num, so sqrt(num) must be smaller than mid
				right = mid;
			} else if(midSqu < num){  //mid^2 is less than num so sqrt(num) must be greater than mid
				left = mid;
			} 
			
			mid = (left+right)/2;  //next mid or potentially sqrt(num)
			midSqu = mid*mid;  //next mid^2 or potentially num
		}
		
		//at this point, the absolute difference between mid and sqrt(num) is less than acceptable threshold
		//so they are essentially the same value
		//close enough sqrt(num) if not exact (since returning a double, its okay)
		//for int return of sqrt(num) need to round accordingly 
		//so (int)(mid+0.5) will round up if number is >=0.5 or down if number is <0.5
		return mid; 
	}
}
