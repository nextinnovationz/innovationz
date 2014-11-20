package QSearchSort;

import Exception.InvalidInputException;
import Exception.NoResultException;

public class TurningNumber {
	public static void main(String[] args) {
		int[] arrA = {4,5,6,7,8,9,6,3,2,1};
		try {
			System.out.println("turning: " + findTurning(arrA));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int[] arrB = {4,5,6,7,8,9};  //it will keep searching right until it reach the end
		try {
			System.out.println("turning: " + findTurning(arrB));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int[] arrC = {6,3,2,1,0};  //it will keep searching left until it reach the beginning
		try {
			System.out.println("turning: " + findTurning(arrC));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static int findTurning(int[] arr) throws NoResultException, InvalidInputException {
		if(arr == null || arr.length == 0)
			throw new InvalidInputException("bad array input");
		
		int left = 0, right = arr.length-1, mid = 0;
		while(left <= right) {
			mid = (left+right)/2;
			if(mid == arr.length-1 || mid == 0)
				throw new NoResultException("mid is at beginning or end of array so its impossible to be turning point");
			
			if(arr[mid-1] < arr[mid] && arr[mid+1] > arr[mid])  //arr[mid] is in 1st increasing subarray
				left = mid+1;
			else if(arr[mid-1] > arr[mid] && arr[mid] > arr[mid+1])  //arr[mid] is in 2nd decreasing subarray
				right = mid-1;
			else  //arr[mid] is turning since it is not in 1st nor 2nd subarrays (it can't be in any other places)
				return arr[mid];
		}
		
		throw new NoResultException("done searching and no turning point found so input must have errors");
	}
}
