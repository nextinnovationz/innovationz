package QGeneral;

import java.util.ArrayList;
import java.util.Stack;

public class PhoneNumberCombination {
	public static void main(String[] args) {
		ArrayList<char[]> map = new ArrayList<char[]>();
		map.add(new char[]{'A','B','C'});
		map.add(new char[]{'D','E','F'});
		map.add(new char[]{'G','H','I'});
		map.add(new char[]{'J','K','L'});
		map.add(new char[]{'M','N','O'});
		map.add(new char[]{'P','R','S'});
		map.add(new char[]{'T','U','V'});
		map.add(new char[]{'W','X','Y'});
		generate(map, "305");
	}
	
	public static void generate(ArrayList<char[]> map, String num) {
		Stack<Character> build = new Stack<Character>();
		generate(map, num, build);
	}
	
	public static void generate(ArrayList<char[]> map, String num, Stack<Character> build) {
		if(build.size() == num.length()) {
			System.out.println(build.toString());
			return;
		}
		
		//build's current size indicates the current position in original string to select
		//since 0 and 1 don't have mapping, -2 is offset to the table 
		//need to subtract from '0' to get int value of a char digit
		int idx = num.charAt(build.size()) - '0'; 
		
		//0 or 1 so just add it to build since there is no conversion, thus only 1 choice for this position
		if(idx == 0) {
			build.push('0');
			generate(map, num, build);
			build.pop();
		} else if(idx == 1) {
			build.push('1');
			generate(map, num, build);
			build.pop();
		} else {
			//get the correct array to select a char from
			char[] select = map.get(idx-2);
			int selectLen = select.length;
			
			//need to go through all conversions in this array so use a loop 
			for(int curr = 0; curr < selectLen; ++curr) {
				build.push(select[curr]);
				generate(map, num, build);
				build.pop();
			}	
		}
	}
}
