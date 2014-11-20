package QMath;

import java.util.ArrayList;

public class Power {
	public static void main(String[] args) {
		double base = 3;
		int exp = 3;
		System.out.println(power(base, exp));
		System.out.println(powerRecursive(base, exp));
		
		int[] a = {5,9,4,3};
		int[] b = {7,6,5};
		printNumberArray(multiplyBigArray(a, b));
	}
	
	public static double power(double base, double exp) {
		if(exp == 0)
			return 1.0;
		else if(exp == 1)
			return base;
		else {
			boolean isNeg = false;
			if(exp < 0) { 
				isNeg = true;
				exp = -exp;  //make exp positive for loop below
			}
			
			double pow = 1;
			
			//loop exp times to raise base to exp
			for(int i = 0; i < exp; ++i) {
				pow *= base;
			}
			
			if(isNeg)
				return 1.0/pow;
			else
				return pow;
		}
	}
	
	public static double powerRecursive(double base, int exp) {
		if(exp == 0)
			return 1;
		
		boolean isNeg = false;
		if(exp < 0) {
			isNeg = true;
			exp = -exp;
		}
		
		double result = powerRecursiveHelper(base, exp);
		if(isNeg)
			return 1.0/result;
		else
			return result;
	}
	
	public static double powerRecursiveHelper(double base, int exp) {
		if(exp == 1)
			return base;
		else if((exp&1) == 0) 
			return powerRecursive(base*base, exp/2);
		else 
			return base * powerRecursive(base*base, exp/2);
	}
	
	/*
	public static Integer[] powerBig(int base, int exp) {
		if(exp == 0)
			return 1.0;
		else if(exp == 1)
			return base;
		else {
			boolean isNeg = false;
			if(exp < 0) { 
				isNeg = true;
				exp = -exp;  //make exp positive for loop below
			}
			
			double pow = 1;
			
			//loop exp times to raise base to exp
			for(int i = 0; i < exp; ++i) {
				
			}
			
			if(isNeg)
				return 1.0/pow;
			else
				return pow;
		}
	}
	*/
	
	public static Integer[] multiplyBigArray(int[] a, int[] b) {
		int rem = 0, mul = 0, remcpy = 0;
		int bidx = 0, aidx = 0, tidx = 0;
		int blen = b.length, alen = a.length;
		ArrayList<Integer> temp = new ArrayList<Integer>();
		
		//multiply entire a by each rightmost digit of b until no more b
		while(bidx < blen) {
			while(aidx < alen) {
				mul = a[aidx] * b[bidx];
				mul += rem;
				if(tidx < temp.size())
					temp.set(tidx, mul % 10);
				else
					temp.add(mul % 10);
				rem = mul / 10;
				++aidx;
				++tidx;
			}
			
			//last remaining can all be appended to the front of result (back of array)
			while(rem > 0) {
				temp.add(rem%10);  
				rem /= 10;
			}
			
			temp.set(0, 0);
			
			System.out.println(temp.toString() + "  rem: " + rem);
			tidx = 0;
			aidx = 0;
			++bidx;
		}
		
		//last remaining can all be appended to the front of result (back of array)
		while(rem > 0) {
			temp.add(rem%10);  
			rem /= 10;
		}
		
		Integer[] result = new Integer[temp.size()];
		temp.toArray(result);
		return result;
	}	
	
	public static Integer[] multiplyBig(int a, int b) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		int rem = 0, mul = 0;
		
		//multiply entire a by each rightmost digit of b until no more b
		while(b > 0) {
			mul = a * (b%10);  //multiply a with rightmost digit of b
			mul += rem;  //add mul with previous iteration's remaining (previous mul without rightmost digit)
			res.add(mul%10);  //add mul's rightmost digit to result array 
			rem = mul/10;  //remove rightmost digit in mul (to be added to next mul)
			b /= 10;  //remove b's rightmost digit
		}
		
		//last remaining can all be appended to the front of result (back of array)
		while(rem > 0) {
			res.add(rem%10);  
			rem /= 10;
		}
		
		Integer[] result = new Integer[res.size()];
		res.toArray(result);
		return result;
	}
	
	public static void printNumberArray(Integer[] arr) {
		if(arr.length == 0)
			return;
		
		int len = arr.length;
		for(int i = len-1; i >= 0; --i) 
			System.out.print(arr[i]);
		
		System.out.println();
	}
}
