package QArray;

public class CountInversions {
	public static void main(String[] args) {
		//int[] arr = {1,2,4,3,5,7,3,2,8,1};
		int[] arr = {1,2,5,4,2,3};
		System.out.println(countBetter(arr));
		System.out.println(countBruteForce(arr));
	}
	
	public static int countBruteForce(int[] arr) {
		int c = 0;
		for(int i = 0; i < arr.length; ++i) {
			for(int j = i+1; j < arr.length; ++j) {
				if(arr[i] > arr[j]) {
					++c;
					//System.out.println("(" + arr[i] + "," + arr[j] + ")");
				}
			}
		}
		return c;
	}
	
	public static int countBetter(int[] arr) {
		if(arr == null || arr.length == 0)
			return 0;
		
		countInversions(arr, 0, arr.length-1);
		return count;
	}
	
	public static int[] countInversions(int[] arr, int start, int end) {
		//base case for merge sort's divide step 
		//arr only has one element when start and end is the same
		//one element array is sorted so just return to merge large arrays
		if(end-start == 0) {
			int[] temp = {arr[start]};
			return temp;
		}
		
		int mid = (start+end)/2;
		int[] left = countInversions(arr, start, mid);
		int[] right = countInversions(arr, mid+1, end);
		return countInversions(left, right);
	}
	
	public static int count = 0;
	
	public static int[] countInversions(int[] leftArr, int[] rightArr) {
		int left = 0, right = 0, leftLen = leftArr.length, rightLen = rightArr.length, merge = 0;
		int[] sorted = new int[leftLen + rightLen];
		while(left < leftLen && right < rightLen) {
			if(leftArr[left] <= rightArr[right]) {
				sorted[merge] = leftArr[left];
				++left;
			} else {
				sorted[merge] = rightArr[right];
				++right;
				
				//this right is less than all lefts from current left to end of left (inverted)
				count += leftLen-left;
			}
			++merge;
		}

		//all inversions already considered in the loop above
		//just copy remaining elements to sorted array if any remains
		while(left < leftLen) {
			sorted[merge] = leftArr[left];
			++left;
			++merge;
		}
		
		while(right < rightLen) {
			sorted[merge] = rightArr[right];
			++right;
			++merge;
		}
		
		return sorted;
	}
}
