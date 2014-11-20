package QString;

import java.util.HashMap;

public class LongestNonRepeatingSequence {
	public static void main(String[] args) {
		String s = "ababcdefghabbjipqrsdbamno";
		System.out.println(find(s));
	}
	
	public static String find(String str) {
		//keep count of each char for quick repeat lookup 
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		int len = str.length();
		char c;
		for(int i = 0; i < len; ++i) {
			c = str.charAt(i);
			if(map.containsKey(c))
				map.put(c, map.get(c)+1);
			else
				map.put(c, 1);
		}
		
		int prevStart = 0, prevEnd = 0;
		int start = 0, end;
		for(end = 0; end < len; ++end) {
			c = str.charAt(end);
			
			//curr char is repeat so check if curr string is > than prev string
			if(map.get(c) > 1) {
				//the curr end is actually end-1 (because end now is repeat)
				//len of curr string is (end-start)
				//the prevEnd is the actual last char 
				//len of prev string is (prevEnd-prevStart+1)
				if(end-start > prevEnd-prevStart+1) {
					prevStart = start;
					prevEnd = end-1;
				}
				//curr char (end) is repeat so skip it for next start of string
				start = end+1;
			} 
		}
		
		//check the prev string and the last curr string lengths for longest
		if(prevEnd-prevStart+1 > end-start) {
			//prevEnd points to actual last char of prev string so need to +1 for substring to include last char
			return str.substring(prevStart, prevEnd+1);
		} else {
			//end is actually (end char index +1) since it broke loop so substring excludes char at end
			return str.substring(start, end);  
		}
	}
}
