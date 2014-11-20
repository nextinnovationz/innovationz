package QSearchSort;

import java.util.Arrays;

public class NextGreaterNumber {
	public static void main(String[] args) {
		int[] arr = {6, 1, 2, 7, 6, 5};
		System.out.println("next greater pos: " + arr[findNext(arr, 2)]);
		System.out.println("next greater number: " + Arrays.toString(findNextGreater(arr)));
	}
	
	public static int[] findNextGreater(int[] arr) {
		int len = arr.length-1;
		
		//find index pos where it is smaller than its right
		int pos = len, toRight = arr[len];
		while(pos >= 0) {
			if(arr[pos] < toRight)
				break;
			toRight = arr[pos];
			--pos;
		}
		
		System.out.println("curr smallest pos: " + pos);
		
		//from index pos to its rightmost, find smallest number greater than arr[pos]
		int next = findNext(arr, pos);
		
		//swap arr[pos] and arr[next]
		swap(arr, pos, next);
		
		//from pos+1 to its rightmost, reverse all digits from nondecreasing to nonincreasing
		++pos;
		while(pos <= len) {
			swap(arr, pos, len);
			++pos;
			--len;
		}
		
		//number now is next greatest
		return arr;
	}

	public static int findNext(int[] arr, int selfIdx) {
		if(arr == null || arr.length == 0 || selfIdx < 0 || selfIdx >= arr.length)
			return -1;
		
		int left = selfIdx+1, right = arr.length-1, mid = 0;
		while(left <= right) {
			mid = left+right/2;
			if(arr[mid] <= arr[selfIdx])
				right = mid-1;
			else if(arr[mid] > arr[selfIdx]) {
				if(mid+1 == arr.length)
					return mid;
				else if(mid+1 < arr.length && arr[mid+1] < arr[selfIdx])
					return mid;
				else
					left = mid+1;
			}
		}
		
		return -1;
	}
	
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
