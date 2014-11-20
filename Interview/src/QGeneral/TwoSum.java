package QGeneral;

import java.util.HashMap;

public class TwoSum {
	public static void main(String[] args) {
		int sum = 16;
		int[] arr = {1,1,2,2,3,4,5,6,8,8,9,10,13,14,14,15,16,16,17};
		findTwoSumSorted(arr, sum);
		
		int[] arr2 = {13,2,5,7,12,7,4,3,15,1,16,11};
		findTwoSumUnsorted(arr2, sum);
	}
	
	public static void findTwoSumSorted(int[] arr, int sum) {
		int left = 0, right = arr.length-1;
		while(left < right) {
			if(arr[left] + arr[right] > sum)  //currsum is too big, move right to make it smaller
				--right;
			else if(arr[left] + arr[right] < sum)  //currsum is too small, move left to make it bigger
				++left;
			else {  //currsum is the target sum
				System.out.println("(" + arr[left] + "," + arr[right] + ")");
				//move both pointers 
				++left;
				--right;
				
				//ignore all left duplicates
				while(left < arr.length && arr[left] + arr[right] == sum) {
					++left;
				}
				
				//make sure left and right still didn't cross
				if(left < right) {
					//ignore all right duplicates
					while(right >= 0 && arr[left] + arr[right] == sum) {
						--right;
					}	
				}
			}
		}
		System.out.println();
	}
	
	public static void findTwoSumUnsorted(int[] arr, int sum) {
		HashMap<Integer,Integer> lookup = new HashMap<Integer,Integer>();
		int len = arr.length;
		
		//hash each element with its index location
		for(int i = 0; i < len; ++i) {
			lookup.put(arr[i], i);
		}
		
		//for each element look up sum-element to see if difference exist 
		//handles the case when the arr have duplicates 
		//have duplicate pairs x+y=sum so (x,y) and (y,x) 
		for(int i = 0; i < len; ++i) {
			if(lookup.containsKey(sum-arr[i]))
				System.out.println("(" + arr[i] + "," + arr[lookup.get(sum-arr[i])] + ")");
		}
		System.out.println();
	}
}
