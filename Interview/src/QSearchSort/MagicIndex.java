package QSearchSort;

public class MagicIndex {
	public static void main(String[] args) {
		int[] arrA = {-3,-2,0,1,2,5,8,9,10};
		System.out.println("magic distinct: " + magicIndexDistinct(arrA));
		
		int[] arrB = {-3,-2,0,1,1,1,2,3,3,9,12};
		System.out.println("magic repeats: " + magicIndexRepeat(arrB));
	}
	
	public static int magicIndexDistinct(int[] arr) {
		if(arr == null || arr.length == 0) {
			return -1;
		}
		
		int start = 0, end = arr.length, mid = 0;
		while(start <= end) {
			mid = (start+end)/2;
			
			//Ex. mid = 5, arr[mid] = 6
			//right side elements must be greater than 6
			//but index starts at 5 onward so it will always be less than element
			//must look left to find matching
			if(arr[mid] > mid) { 
				end = mid-1;
			} else if(arr[mid] < mid) {
				start = mid+1;
			} else {
				return mid;
			}
		}
		return -1;
	}
	
	public static int magicIndexRepeat(int[] arr) {
		if(arr == null || arr.length == 0) {
			return -1;
		}
		return magicIndexRepeat(arr, 0, arr.length-1);
	}
	
	public static int magicIndexRepeat(int[] arr, int start, int end) {
		//out of bound or crossing base case
		if(start > end || start < 0 || end > arr.length-1)
			return -1;
		
		int mid = (start+end)/2;
		
		//matching base case
		if(arr[mid] == mid)
			return mid;
		
		//keep searching left and right until either base case is reached
		int left = magicIndexRepeat(arr, start, mid-1);
		int right = magicIndexRepeat(arr, mid+1, end);
		
		//result is either left or right that is not -1 or -1 if not magic index exist
		return left == -1 ? right : left;
	}
}
