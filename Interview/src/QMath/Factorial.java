package QMath;

import java.util.ArrayList;

public class Factorial {
	public static void main(String[] args) {
		int n = 13;
		ArrayList<Character> fact = factorial(n);
		System.out.println("fact(" + n + "): " + fact.toString());
	}
	
	public static ArrayList<Character> factorial(int n) {
		if(n < 0)
			return null;
		
		ArrayList<Character> result = new ArrayList<Character>();
		
		int temp = n;
		while(temp > 0) {
			result.add((char) ((temp%10) + '0'));
			temp /= 10;
		}
		
		int next = n-1, mul = 0, carry = 0, len = 0, pos = 0, digit = 0;
		
		while(next > 1) {  //go from n-1 to 2
			len = result.size(); 
			
			while(pos < len) {  //multiply each digit by next (left to right)
				digit = result.get(pos) - '0';
				mul = (digit * next) + carry;
				carry = mul/10;  //carry to next index
				result.set(pos, (char) ((mul%10) + '0'));
				++pos;
			}
			
			if(carry > 0) {  //extra carry after multiplying all the digits, just add them to array
				while(carry > 0) {
					result.add((char) ((carry%10) + '0'));
					carry /= 10;
				}
			}
			
			pos = 0;
			--next;
		}

		reverseArrayList(result);
		return result;
	}
	
	public static void reverseArrayList(ArrayList<Character> num) {
		if(num == null || num.size() < 2) {
			return;
		}
		
		int i = 0, j = num.size()-1;
		char temp;
		
		while(i < j) {
			temp = num.get(i);
			num.set(i, num.get(j));
			num.set(j, temp);
			++i;
			--j;
		}
	}
}
