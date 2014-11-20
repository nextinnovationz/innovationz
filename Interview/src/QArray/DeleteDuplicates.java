package QArray;

import java.util.Arrays;
import java.util.HashMap;

public class DeleteDuplicates {
	public static void main(String[] args) {
		int[] arr = {1,4,5,5,3,2,6,4,7,2,2,3,8,9,1,-9,-2,2,12};
		deleteDuplicates(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	public static void deleteDuplicates(int[] arr) {
		HashMap<Integer, Boolean> lookup = new HashMap<Integer, Boolean>();
		int len = arr.length;
		for(int i = 0; i < len; ++i) {  //count occurrences of each element in lookup table
			if(!lookup.containsKey(arr[i]))
				lookup.put(arr[i], false);
			else
				lookup.put(arr[i], true);
		}
		
		int left = 0, right = len-1;
		while(left < right) {  
			if(lookup.get(arr[left])) {  //curr is a dup
				while(left < right && lookup.get(arr[right])) {  //look from right to left to find a nondup to swap
					--right;
				}
				//make sure it is not swapping with anything to its left because those are already nondups
				//in that case, the loop will break because there are no more nondup to swap with
				if(left < right) {  
					swap(arr, left, right);
					--right;	
				}
			}
			++left;
		}
		
		//just zero out all from right to end of array because they are dups 
		//or copy all nondups to a new array to return
		for(int j = right; j < len; ++j) {
			arr[j] = 0;
		}
	}
	
	public static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
}
