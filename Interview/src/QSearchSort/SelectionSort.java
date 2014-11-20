package QSearchSort;

import java.util.Arrays;

public class SelectionSort {
	public static void main(String[] args) {
		int[] arr = {3,4,1,2,7,4,4,9,2,1,9,0,3};
		selectionsort(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	public static void selectionsort(int[] arr) {
		if(arr == null || arr.length == 0)
			return;
		
		int min = 0, minIdx = 0, len = arr.length, curr = 0;
		
		while(curr < len) {
			min = arr[curr];
			minIdx = curr;
			for(int i = curr+1; i < len; ++i) {
				if(arr[i] < min) {
					min = arr[i];
					minIdx = i;
				}
			}
			swap(arr, curr, minIdx);
			++curr;
		}
	}
	
	public static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
}
