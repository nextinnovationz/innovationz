package QSearchSort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

public class SortAnagram {
	public static void main(String[] args) {
		
	}
	
	public static String[] sortAnagramWithComparator(String[] strs) {
		Arrays.sort(strs, new AnagramComparator());
		return strs;
	}
	
	static class AnagramComparator implements Comparator<String> {
		@Override
		public int compare(String s1, String s2) {
			char[] str1 = s1.toCharArray();
			Arrays.sort(str1);
			s1 = new String(str1);
			
			char[] str2 = s2.toCharArray();
			Arrays.sort(str2);
			s2 = new String(str2);
			return s1.compareTo(s2);
		}
	}
	
	public static String[] sortAnagramWithHashmap(String[] strs) {
		HashMap<String, LinkedList<String>> map = new HashMap<String, LinkedList<String>>();
		for(String s: strs) {
			String sorted = sortStringByChar(s);
			if(map.containsKey(sorted)) {
				map.get(sorted).add(s);
			} else {
				map.put(sorted, new LinkedList<String>());
			}
		}
		
		int len = strs.length, curr = 0;
		while(curr < len) {  //replace all elements in array by groups of anagrams
			for(String key : map.keySet()) {  //get each key
				strs[curr] = key;  //add key itself 
				++curr;
				for(String val : map.get(key)) {  //get list associated with each key
					strs[curr] = val;  //add each val in list next to key (group anagrams)
					++curr;
				}
			}
		}
		
		return strs;
	}
	
	public static String sortStringByChar(String s) {
		char[] temp = s.toCharArray();
		Arrays.sort(temp);
		return new String(temp);
	}
}
