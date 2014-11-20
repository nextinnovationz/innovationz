package QString;

import Exception.InvalidInputException;

public class Conversion {
	public static void main(String[] args) {
		char[] a = "-78656".toCharArray();
		char[] d = {'0'};
		try {
			System.out.println(stringToIntLeftRight(d));
			System.out.println(stringToIntRightLeft(d));
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
		
		int b = -3245, c = 0;
		System.out.println(intToString(b));
		System.out.println(intToString(c));
	}
	
	public static int stringToIntLeftRight(char[] num) throws InvalidInputException {
		if(num == null || num.length == 0)
			throw new InvalidInputException("Bad input");
		
		boolean isNeg = num[0] == '-';
		int sum = 0;
		int len = num.length;
		int start = 0;
		
		if(isNeg)
			start = 1;
		
		for(int i = start; i < len; ++i) {
			//System.out.println("sum: " + sum + "\tcurr: "+ (num[i] - '0'));
			sum = (10*sum) + (num[i] - '0');
			
		}
		
		if(isNeg)
			sum = -sum;
		
		return sum;
	}
	
	public static int stringToIntRightLeft(char[] num) throws InvalidInputException {
		if(num == null || num.length == 0)
			throw new InvalidInputException("Bad input");
		
		boolean isNeg = num[0] == '-';
		int len = num.length;
		int sum = num[len-1] - '0';
		int placeVal = 0;
	
		for(int i = len-1; i >= 0; --i) {
			if(num[i] == '-')
				break;
			
			int j = len-i-1; 
			while(j > 0) {
				placeVal *= 10;
				--j;
			}
			
			sum += (num[i] - '0') * placeVal;
			//System.out.println("sum: " + sum + "\tplaceVal: " + placeVal);
			placeVal = 1;
		}
		
		if(isNeg)
			sum = -sum;
		
		return sum;
	}
	

	public static String intToString(int num) {
		boolean isNeg = num < 0;
		if(isNeg)
			num = -num;
		
		StringBuilder sb = new StringBuilder();
		int n = num;
		
		do {
			sb.append(n % 10);
			n /= 10;
		} while(n > 0); 
			
		if(isNeg)
			sb.append('-');
		
		return sb.reverse().toString();
	}
}
