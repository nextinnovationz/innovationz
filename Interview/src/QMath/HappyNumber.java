package QMath;

import java.util.ArrayList;

public class HappyNumber {
	public static void main(String[] args) {
		int a = 10;
		int b = 11;
		System.out.println(isHappy(a));
		System.out.println(isHappy(b));
	}
	
	public static boolean isHappy(int num) {
		ArrayList<Integer> mem = new ArrayList<Integer>();
		mem.add(num);
		
		int sum = 0, digit = 0;
		
		while(num > 0) {
			digit = num%10;  //get rightmost digit
			digit *= digit;  //digit^2
			sum += digit;  //accumulate sum for each digit^2
			num /= 10;  //remove rightmost digit
			
			//don't know if num is happy, yet
			//repeat procedure again until it reaches 1
			if(num == 0) {  //no more digit from num
				num = sum;  //make sum into num and repeat the whole loop again if needed
				sum = 0;  //reset sum for next round
				
				//num here is the sum 
				if(num == 1) {  //done, num is happy
					return true;
				}
				
				//check if num has already been seen before
				if(mem.contains(num)) {  //num is not happy, it will never be 1
					return false;
				}
				
				//put num into mem to keep track of whether it existed before
				//prevent this method from running forever if num never reaches 1
				mem.add(num);
			}
		}
		
		//all numbers <= 0 are not happy (constraint of problem)
		return false;
	}
}
