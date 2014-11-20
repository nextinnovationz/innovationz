package QArray;

import java.util.HashMap;

public class AllPairsDifferenceByTwo {
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5,6,7,8,9,10};
		findPairs(arr);
	}
	
	public static void findPairs(int[] arr) {
		HashMap<Integer, Integer> lookup = new HashMap<Integer, Integer>();
		int len = arr.length;
		
		//hashtable for quick index lookup for each element
		for(int i = 0; i < len; ++i) {
			lookup.put(arr[i], i);
		}
		
		//a-b=2 is the same as a=2+b
		//treat each element as b and add 2 then lookup for a
		//the matching pair is (b,a) with difference of 2
		for(int i = 0; i < len; ++i) {
			if(lookup.containsKey(arr[i]+2)) {
				System.out.println("(" + arr[i] + "," + arr[lookup.get(arr[i]+2)] + ")");
			}
		}
	}
}
