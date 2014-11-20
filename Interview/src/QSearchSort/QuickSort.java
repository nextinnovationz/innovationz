package QSearchSort;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {
	public static void main(String[] args) {
		int[] arr = {3, 2, 5, 4, 6, 7, 2, 1, 8};
		System.out.println("array before sort: " + Arrays.toString(arr));
		sort(arr, 0, arr.length-1);
		System.out.println("array after sort: " + Arrays.toString(arr));
	}
	
	public static void sort(int[] arr, int start, int end) {
		//after partition, element at pivotIdx is in final sorted position
		//so recursively sort left [start,pivotIdx-1] and right [piovtIdx+1,end]
		int pivotIdx = partition(arr, start, end);
		if(start < pivotIdx - 1)
			sort(arr, start, pivotIdx-1);
		if(pivotIdx < end)
			sort(arr, pivotIdx+1, end);			
	}
	
	public static int partition2(int[] arr, int start, int end) {
		Random r = new Random();
		int pivot = start+r.nextInt(end-start+1);
		swap(arr, pivot, start);
		pivot = start;
		
		int big = start+1, small = start+1;
		for(; small < end; ++small) {
			if(arr[pivot] > arr[small]) {
				swap(arr, big, small);
				++big;
			}
		}
		
		swap(arr, pivot, big-1);
		return big-1;
	}
	
	public static int partition(int[] arr, int start, int end, int pivotIdx) {
		int pivot = arr[pivotIdx];
		int left = start, right = end;
		
		//when left and right crossed, then all elements has been visited by the two pointers so no need to check anymore
		while(left <= right) {
			while(arr[left] < pivot) 
				++left;
			while(arr[right] > pivot)
				--right;
			
			if(left <= right) {
				swap(arr, left, right);		
				++left;
				--right;
			}
		}
		return left;
	}
	
	public static int partition(int[] arr, int start, int end) {
		int pivotIdx = (start+end)/2, pivot = arr[pivotIdx];
		int left = start, right = end;
		
		//when left and right crossed, then all elements has been visited by the two pointers so no need to check anymore
		while(left <= right) {
			while(arr[left] < pivot) 
				++left;
			while(arr[right] > pivot)
				--right;
			
			if(left <= right) {
				swap(arr, left, right);		
				++left;
				--right;
			}
		}

		return left;
	}
	
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
