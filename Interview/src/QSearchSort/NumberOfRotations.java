package QSearchSort;

public class NumberOfRotations {
	public static void main(String[] args) {
		int[] arr = {3,4,5,6,0,1,2};
		System.out.println("rotations: " + rotationCounts(arr));
	}
	
	public static int rotationCounts(int[] arr) {
		int left = 0, right = arr.length-1, mid = 0;
		
		//keep searching for head of 2nd increasing subarray (pointed by right when done)
		while(left < right) {
			mid = (left+right)/2;
			if(mid == left) {  //left and right are right next to each other (middle is at left)
				break;
			}
			if(arr[mid] > arr[left])  //mid is greater than left, so still in 1st increasing subarray
				left = mid;
			else if(arr[mid] < arr[left])  //mid is less than left, so in 2nd increasing subarray
				right = mid;				
		}
		
		System.out.println("right: " + right);
		return arr.length-right;
	}
}
