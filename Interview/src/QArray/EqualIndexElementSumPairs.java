package QArray;

import java.util.ArrayList;
import java.util.HashMap;

public class EqualIndexElementSumPairs {
	public static void main(String[] args) {
		int[] arr = {0,4,3,2,6,2,9};
		find(arr);
	}
	
	public static void find(int[] arr) {
		int len = arr.length;
		int diff = 0;
		
		HashMap<Integer, ArrayList<Integer>> lookup = new HashMap<Integer, ArrayList<Integer>>();
		
		//do i-arr[i] to get the difference
		//has each difference (use arraylist as value in case there are multiple pairs with same difference)
		for(int i = 0; i < len; ++i) {
			diff = i - arr[i];
			if(lookup.containsKey(diff)) { 
				lookup.get(diff).add(i);
			} else {
				ArrayList<Integer> temp = new ArrayList<Integer>();
				temp.add(i);
				lookup.put(diff, temp);
			}
		}
		
		//look up i-arr[i] for its negated version
		//so diff for -diff and -diff for diff
		//each pair will have same index and element sum
		for(int i = 0; i < len; ++i) {
			diff = i - arr[i];
			if(lookup.containsKey(-diff)) {
				ArrayList<Integer> temp = lookup.get(-diff);
				for(int idx : temp) {
					if(idx != i)
						System.out.println("(" + i + "," + idx + ")   (" + arr[i] + "," + arr[idx] + ")");
				}
			}	
		}
	}
}

