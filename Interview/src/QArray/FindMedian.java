package QArray;

import java.util.Arrays;
import java.util.Random;

public class FindMedian {
	public static void main(String[] args) {
		//int[] arr = {3,5,2,1,4,0,6,5,2};
		int[] arr = {4,1,2,5,6,0};
		
		//mid=(start+end)/2 where start=0 here and end=arr.length-1
		int mid = (arr.length-1)/2;
		System.out.println("unsorted median: " + findMedian(arr, mid));
		System.out.println("unsorted array: " + Arrays.toString(arr));
		Arrays.sort(arr);
		System.out.println("sorted median: " + arr[mid]);
		System.out.println("sorted array: " + Arrays.toString(arr));
	}
	
	//bad partition() because the final position of pivot is incorrect
	public static int partition(int[] arr, int start, int end) {
		int pivot = (start+end)/2;
		int left = start, right = end;
		while(left <= right) {
			while(arr[left] < arr[pivot]) {
				++left;
			}
			while(arr[right] > arr[pivot]) {
				--right;
			}
			if(left <= right) {
				int temp = arr[left];
				arr[left] = arr[right];
				arr[right] = temp;
				++left;
				--right;
			}
		}
		
		return left;
	}
	
	public static int partition2(int[] arr, int start, int end) {
		//generate a random pivot index for better performance
		Random r = new Random();
		int pivot = r.nextInt(end);
		
		//swap pivot with first element
		swap(arr, start, pivot);
		
		//update pivot index to first
		pivot = start;
		
		//start scanning from pivot+1 (everything to the right of pivot)
		//find elements smaller than or equal to pivot on the right side
		//swap it with a larger element on the left side 
		//use small to represent small element index and keep incrementing in loop to search
		//use big to represent big element index which only moves right by 1 after a swap with a smaller element
		//so small is always ahead of big after the initialization step (unless swap is called everytime and they will be the same)
		int big = start+1;
		for(int small = start+1; small < end; ++small) {
			if(arr[pivot] >= arr[small]) {
				swap(arr, small, big);
				++big;
			}
		}
		
		//the one index before big is the final position for pivot
		//it is the last position where the left is <= pivot and right is >pivot
		swap(arr, big-1, pivot);
		return big-1;
	}
	
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	public static int findMedian(int[] arr, int k) {
		//since it doesn't partition and sort left and right like quicksort, it is O(n) 
		//it only pick a side and go to it at each loop iteration below
		
		int start = 0, end = arr.length-1;
		int pivot = partition(arr, start, end);
		//System.out.println("pivot: " + arr[pivot]);
		//System.out.println(Arrays.toString(arr));
		
		//will get pivot for kth element 
		//since its index from 0, k is actually k+1th element
		//so there are k elements to the left of the k+1th element
		//don't stop partition until the pivot is k
		while(pivot != k) {
			if(pivot > k) {  //there are more than k elements on the left so look in the left to reduce k
				end = pivot-1;
				pivot = partition(arr, start, end);
			} else if(pivot < k) {  //there are less than k elements on the left so look in the right to increase k
				start = pivot+1;
				pivot = partition(arr, start, end);
			}
		}
		return arr[pivot];
	}
}
