package QSearchSort;

import java.util.Arrays;

public class MergeSort {
	public static void main(String[] args) {
		int[] arr = {2,3,1,4,2,3,6,7,5,9};
		mergesort2(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	public static int[] mergesort(int[] arr) {
		if(arr == null || arr.length == 0)
			return arr;
		return mergesort(arr, 0, arr.length-1);
	}
	
	public static int[] mergesort(int[] arr, int start, int end) {
		System.out.println("start: " + start + "\tend: " + end);
		if(end-start > 0) {  //divide step still has subarray with size >1
			int mid = (start+end)/2;  //midpoint to divide subarrays into 2
			int[] left = mergesort(arr, start, mid);  
			int[] right = mergesort(arr, mid+1, end);
			
			//in more optimized mergesort, when subarrays are below some size,
			//instead of doing regular comparison merge, use insertion sort 
			return merge(left, right);  
		} else {  //base case of only 1 element in array so its sorted
			return new int[]{arr[start]};  //return 1 element array
		}
	}
	
	public static int[] merge(int[] left, int[] right) {
		int lcurr = 0, rcurr = 0, llen = left.length, rlen = right.length, ccurr = 0;
		int[] combined = new int[llen + rlen];
		while(lcurr < llen && rcurr < rlen) {
			if(left[lcurr] <= right[rcurr]) {
				combined[ccurr] = left[lcurr];
				++lcurr;
			} else {
				combined[ccurr] = right[rcurr];
				++rcurr;
			}
			++ccurr;
		}
		
		while(lcurr < llen) {
			combined[ccurr] = left[lcurr];
			++ccurr;
			++lcurr;
		}
		
		while(rcurr < rlen) {
			combined[ccurr] = right[rcurr];
			++ccurr;
			++rcurr;
		}
		
		return combined;
	}
	
	public static void mergesort2(int[] arr) {
		if(arr == null || arr.length == 0)
			return;
		mergesort2(arr, 0, arr.length-1);
	}	
	
	public static void mergesort2(int[] arr, int start, int end) {
		if(start < end) {  //stop when only 1 element (start == end)
			int mid = (start+end)/2;
			mergesort2(arr, start, mid);
			mergesort2(arr, mid+1, end);
			merge(arr, start, mid, end);
		}
	}
	
	public static void merge(int[] arr, int start, int mid, int end) {
		int[] helper = new int[arr.length];
		for(int i = start; i <= end; ++i) {
			helper[i] = arr[i];
		}
		//System.out.println("\thelper: " + Arrays.toString(helper));
		
		int left = start;
		int right = mid+1;
		int sortedIdx = start;
		
		//go from [left, mid] and [right, end] => [start, end]
		//if right element is smaller, move right to left side
		//so at the end, if no more right elements, 
		//then just need to copy all remaining larger left elements to the right side
		while(left <= mid && right <= end) {
			if(helper[left] <= helper[right]) {
				arr[sortedIdx] = helper[left];
				++left;
			} else {
				arr[sortedIdx] = helper[right];
				++right;
			}
			++sortedIdx;
		}
		
		//have some left side remaining that should go right
		while(left <= mid) {
			arr[sortedIdx] = helper[left];
			++left;
			++sortedIdx;
		}
	}
}
