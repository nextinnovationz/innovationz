package QSearchSort;

import Exception.NoResultException;

public class MinimumRotationNumber {
	public static void main(String[] args) {
		try {
			int[] arrA = {4,5,6,7,8,1,2,3};
			System.out.println("min: " + findMinimumRotationNumber(arrA));
			
			int[] arrD = {4,5,6,7,8,8,1,2,2,3,4};
			System.out.println("min: " + findMinimumRotationNumber(arrD));
			
			int[] arrC = {2,5,6,7,8,2,2,2};
			System.out.println("min: " + findMinimumRotationNumber(arrC));
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			int[] arrB = {9,8,7,6,5,4,3,1};
			System.out.println("min: " + findMinimumRotationNumber(arrB));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			int[] arrD = {4,5,6,7,8,9,10,11};
			System.out.println("min: " + findMinimumRotationNumber(arrD));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int findMinimumRotationNumber(int[] arr) throws NoResultException {
		int left = 0, right = arr.length-1, mid = 0;
		int leftE = arr[left], rightE = arr[right];
		
		while(left <= right) {
			mid = (left+right)/2;
			leftE = arr[left];
			rightE = arr[right];
			
			if(right-left == 1) {  //left points to tail of 1st subarray and right points to head of 2nd subarray
				return rightE;
			}
			
			//left and right are more than 1 apart
			if(arr[mid] >= leftE && arr[mid] > rightE) { //mid must be in 1st subarray
				//System.out.println(arr[mid] + " in 1st sub");
				left = mid;
			} else if(arr[mid] <= rightE && arr[mid] < leftE) {  //mid must be in 2nd subarray
				//System.out.println(arr[mid] + " in 2nd sub");
				right = mid;  //cannot eliminate self because it could be the min
			} else if(arr[mid] == leftE && arr[mid] == rightE) {  //don't know where mid is
				//System.out.println(arr[mid] + " don't know in which sub, search sequentially for min");
				return findMinSequentially(arr, left, right);
			} else {  //there is no minimum because input is not properly rotated
				throw new NoResultException("cannot find min number in rotated array");
			}
		}
		
		throw new NoResultException("cannot find min number in rotated array");
	}
	
	public static int findMinSequentially(int[] arr, int left, int right) {
		int curr = left, min = arr[left];
		while(curr < right) {
			if(min > arr[curr])
				min = arr[curr];
			++curr;
		}
		return min;
	}
}
