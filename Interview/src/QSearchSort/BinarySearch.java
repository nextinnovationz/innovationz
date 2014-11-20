package QSearchSort;

import Exception.InvalidInputException;
import Exception.NoResultException;

public class BinarySearch {
	public static void main(String[] args) {
		int[] arr = {1,2,3,5,6,9};
		try {
			System.out.println("find 4: " + binarySearchIterative(arr, 4));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("find 3: " + binarySearchRecursive(arr, 3));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int binarySearchIterative(int[] arr, int target) throws InvalidInputException, NoResultException {
		if(arr == null || arr.length == 0)
			throw new InvalidInputException("bad array input");
		
		int left = 0, right = arr.length-1, mid = 0;
		
		//must check left == right for the last try to make sure target don't exist
		while(left <= right) {
			mid = (left+right)/2;
			System.out.println("mid: " + arr[mid]);
			if(arr[mid] > target) {
				right = mid-1;
				System.out.println("\t" + arr[mid] + " > " + target + " -- look left");
			} else if(arr[mid] < target) {
				left = mid+1;
				System.out.println("\t" + arr[mid] + " < " + target + " -- look right");
			} else {
				System.out.println("\tfound target!!!");
				return arr[mid];
			}
		}
		
		throw new NoResultException("cannot find target in array");
	}
	
	public static int binarySearchRecursive(int[] arr, int target) throws InvalidInputException, NoResultException {
		if(arr == null || arr.length == 0)
			throw new InvalidInputException("bad array input");
		return binarySearchRecursive(arr, target, 0, arr.length-1);
	}
	
	public static int binarySearchRecursive(int[] arr, int target, int left, int right) throws NoResultException {
		if(left > right)
			throw new NoResultException("cannot find target in array");
			
		int mid = (left+right)/2;
		if(arr[mid] > target) 
			return binarySearchRecursive(arr, target, left, mid-1);
		else if(arr[mid] < target)
			return binarySearchRecursive(arr, target, mid+1, right);
		else 
			return arr[mid];
	}
}
