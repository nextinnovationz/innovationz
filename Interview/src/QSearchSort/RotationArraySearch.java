package QSearchSort;

public class RotationArraySearch {
	public static void main(String[] args) {
		int[] arr = {4,5,6,7,8,0,1,2,3};
		System.out.println(search(arr, 2));
	}
	
	public static int search(int[] arr, int target) {
		int left = 0, right = arr.length-1, mid = 0;
		
		while(left < right) {
			mid = (left+right)/2;

			//found a match
			if(target == arr[mid])
				return arr[mid];
			
			if(target > arr[mid] && arr[left] > arr[mid]) {
				//in 2nd increasing subarray
				//look left since target is larger than mid [left, mid)
				System.out.println("mid: " + arr[mid] + "\tleft: " + arr[left] + "\tlook left");
				right = mid;
			} else if(target > arr[mid] && arr[left] < arr[mid] && target > arr[left]) {
				//in 1st increasing subarray
				//look right since target is larger than mid (mid, right]
				System.out.println("mid: " + arr[mid] + "\tleft: " + arr[left] + "\tlook right");
				left = mid;
			} else if(target < arr[mid] && arr[left] > arr[mid] && target < arr[left]) {
				//in 2nd increasing subarray
				//look right since target is smaller than mid (mid, right]
				System.out.println("mid: " + arr[mid] + "\tleft: " + arr[left] + "\tlook left");
				left = mid;
			} else if(target < arr[mid] && arr[left] < arr[mid] && target < arr[left]) {
				//in 1st increasing subarray
				//look right since target is less than mid and less than left
				System.out.println("mid: " + arr[mid] + "\tleft: " + arr[left] + "\tlook right");
				left = mid;
			} else if(target < arr[mid] && arr[left] < arr[mid] && target > arr[left]) {
				//in 1st increasing subarray
				//look left since target is larger than mid, but greater than left [left, mid)
				System.out.println("mid: " + arr[mid] + "\tleft: " + arr[left] + "\tlook left");
				right = mid;
			}
		}
		
		return -1;
	}
}
