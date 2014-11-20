package QArray;

import Exception.InvalidInputException;

public class FindMajority {
	public static void main(String[] args) {
		int[] arr = {2,3,4,5,2,2,2,2,6,7,4,-1,3,2,0,2,2,3,2,2,2,2,2,2,2,2};
		try {
			System.out.println("maj: " + findMajority(arr));
			System.out.println("maj:" + findMajorityByPartition(arr));
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
	}
	
	public static int findMajority(int[] arr) throws InvalidInputException {
		if(arr == null || arr.length == 0)
			throw new InvalidInputException("bad input array");
		
		int maj = 0, majCnt = 0, idx = 0, len = arr.length;
		while(idx < len) {
			if(majCnt == 0) {
				maj = arr[idx];
				++majCnt;
			} else if(arr[idx] == maj) {
				++majCnt;
			} else {
				--majCnt;
			}
			++idx;
		}
		return maj;
	}
	
	public static int findMajorityByPartition(int[] arr) throws InvalidInputException {
		if(arr == null || arr.length == 0)
			throw new InvalidInputException("bad input array");
		return findMedian(arr);
	}
	
	//find an element such that there are mid number of elements to its left 
	//a majority element should be median of the array when sorted 
	public static int findMedian(int[] arr) {
		int start = 0, end = arr.length-1;
		int pivot = partition(arr, start, end);
		int mid = (start+end)/2;
		while(pivot != mid) {
			System.out.println("pivot: " + pivot);
			if(pivot > mid) {
				System.out.println("left");
				end = pivot-1;
				pivot = partition(arr, start, end);
			} else if(pivot < mid) {
				System.out.println("right");
				start = pivot+1;
				pivot = partition(arr, start, end);
			}
		}
		return arr[mid];
	}
	
	public static int partition(int[] arr, int start, int end) {
		int pivot = (start+end)/2;
		while(start <= end) {
			while(arr[start] < arr[pivot])
				++start;
			while(arr[end] > arr[pivot])
				--end;
			if(start <= end) {
				int temp = arr[start];
				arr[start] = arr[end];
				arr[end] = temp;
				++start;
				--end;
			}
		}
		return start;
	}
	
	//another partition algorithm
	public static int partition2(int[] arr, int start, int end) {
		int pivot = (start+end)/2;
		swap(arr, pivot, end);
		int small = start-1;
		for(int i = start; i <= end; ++i) {
			if(arr[i] < arr[end])
				++small;
				if(i != small)
					swap(arr, small, i);
		}
		
		++small;
		if(small != end)
			swap(arr, small, end);
		
		return small;
	}
	
	public static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
}
