package QSearchSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class RadixSort {
	public static void main(String[] args) {
		int[] arr = {3,45,32,2,12,7,5,23,10,12,17,192,322,9999,543,763,11,2};
		radixsort(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	public static void radixsort(int[] arr) {
		//O(kn) where k is the number of digits or number of passes to run
		//1 digit requires 1 pass 
		
		//create 10 empty buckets from 0 to 9 
		ArrayList<LinkedList<Integer>> buckets = new ArrayList<LinkedList<Integer>>();
		for(int i = 0; i < 10; ++i) {
			buckets.add(new LinkedList<Integer>());
		}
		
		//place starts at 10 because a (single digit % 10) will remove rightmost digit
		//first put number into single digit bucket 
		//next iteration will put it into bucket 0 
		int place = 10, len = arr.length, passCnt = 1, bucketCnt = 0, currNum = 0, currDig = 0;
		
		//initially put all number into buckets according to LSD (rightmost digit)
		for(int i : arr) {
			//to get index 2 (from right) digit - 4
			//need place = 1000 (3 zeros to remove 3 digits)
			//3456 % 1000 = 456  (need to get rightmost digit)
			//456 / (1000 / 10) = 4 (current place 1000/10 will be 100 which division will yield rightmost digit)
			buckets.get((i%place)/(place/10)).add(i);
		}
		
		System.out.println("pass " + passCnt);
		++passCnt;
		printAllBuckets(buckets);
		
		//for next digit to left (place = 100 now)
		place *= 10;
		
		//keep doing digit checking until all numbers are in bucket 0
		//when a number does not have any other digit, it goes to bucket 0 by default
		//so even if some numbers have less digits than others, they will be sorted in bucket 0
		//when bucket 0 has all numbers, then array is sorted
		while(buckets.get(0).size() < len) {
			System.out.println("pass " + passCnt);
			
			//process each bucket's element by digit
			for(LinkedList<Integer> bucket : buckets) {
				//remove from back, add to front
				//bucket size can change since the element is removed from currNument bucket
				//and placed into another bucket based on the digit
				for(int j = bucket.size()-1; j >=0; --j) {
					currNum = bucket.get(j);
					currDig = (currNum%place)/(place/10);  //get current digit from current number
					
					//only remove number if current digit is not the same as current bucket
					//otherwise, current number belongs to current bucket 
					if(currDig != bucketCnt) {  
						bucket.remove(j);  //remove number from bucket since it may go to another bucket based on its digit
						buckets.get(currDig).addFirst(currNum);  //put number in correct bucket based on its digit
					}
				}
				++bucketCnt;
			}
			
			printAllBuckets(buckets);
			place *= 10;  //get next digit to its left
			++passCnt;
			bucketCnt = 0;
		}
		
		//since elements are added to front and removed from back
		//bucket 0 will be in reversed sorted order
		//copy elements back to array to finish sort
		int idx = len-1;
		for(int i : buckets.get(0)) {
			arr[idx] = i;
			--idx;
		}
	}
	
	public static void printAllBuckets(ArrayList<LinkedList<Integer>> buckets) {
		int bucketCnt = 0;
		for(LinkedList<Integer> bucket : buckets) {
			System.out.println("\tbucket " + bucketCnt + " - " + bucket.toString());
			++bucketCnt;
		}
		System.out.println();
	}
}
