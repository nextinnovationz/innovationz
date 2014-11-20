package QMath;

import java.util.ArrayList;

public class UglyNumber {
	public static void main(String[] args) {
		int k = 20;
		System.out.println(getKUgly(k).toString());
		System.out.println(getKthUglyBruteForce(k));
		System.out.println(getKthUgly(k));
	}
	
	public static boolean isUgly(int num) {
		//try if 2 is a factor to divide it completely to 1
		while(num % 2 == 0)
			num /= 2;
		
		//in case 2 was not a factor, or not finish dividing to 1, try 3
		while(num % 3 == 0)
			num /= 3;
		
		//in case 3 was not a factor, or not finish dividing to 1, try 5
		while(num % 5 == 0)
			num /= 5;
		
		return num == 1;
	}
	
	public static int getKthUglyBruteForce(int k) {
		ArrayList<Integer> ugly = new ArrayList<Integer>();
		
		//since not every number is ugly, cannot stop when i == k
		//must keep generating ugly number until the size is k
		for(int i = 1; ugly.size() < k; ++i) {
			if(isUgly(i))
				ugly.add(i);
		}
		
		return ugly.get(k-1);
	}
	
	public static ArrayList<Integer> getKUgly(int k) {
		ArrayList<Integer> ugly = new ArrayList<Integer>();
		
		//since not every number is ugly, cannot stop when i == k
		//must keep generating ugly number until the size is k
		for(int i = 1; ugly.size() < k; ++i) {
			if(isUgly(i))
				ugly.add(i);
		}
		
		return ugly;
	}
	
	public static int getKthUgly(int k) {
		//instead of checking if each number is ugly
		//find next ugly number using the existing ones
		//all ugly numbers is some multiple of 2*prevUgly, 3*prevUgly, 5*prevUgly
		//just take the min of these 3 values and add it to the array if it doesn't exist already
		
		ArrayList<Integer> ugly = new ArrayList<Integer>();
		//1st ugly number is 1
		ugly.add(1);
		
		int twoidx = 0, threeidx = 0, fiveidx = 0;
		int twoNxt, threeNxt, fiveNxt;
		int min;
		
		while(ugly.size() < k) {
			//next ugly number must be a multiple of 2,3,5 times one of the existing ugly 
			twoNxt = 2*ugly.get(twoidx);
			threeNxt = 3*ugly.get(threeidx);
			fiveNxt = 5*ugly.get(fiveidx);

			//get min of the three possible next uglies
			min = Math.min(twoNxt, threeNxt);
			min = Math.min(fiveNxt, min);
			
			//increment the multiple index used to get min
			if(min == twoNxt) {
				++twoidx;
			} else if(min == threeNxt) {
				++threeidx;
			} else {
				++fiveidx;
			}
			
			//check if the last ugly is min (if it already exist don't add it again)
			if(ugly.get(ugly.size()-1) != min) {	
				ugly.add(min);
				//System.out.println("min: " + min);
			} else {
				//repeat all the above again (now all three choices will not exist in ugly yet)
				//since the index was incremented
				//System.out.println("ugly already exist: " + min);
			}
		}
		
		return ugly.get(k-1);
	}
}
