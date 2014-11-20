package QArray;

import java.util.Arrays;

public class AlternateEvenOdd {
	public static void main(String[] args) {
		int[] arr = {0,1,3,5,2,4,2,7,8,9,1,1,2,4,6,5};
		int c1 = unstableAlternateFast(arr);
		System.out.println(Arrays.toString(arr));
		System.out.println("swap: " + c1);
		
		int[] arr2 = {0,1,3,5,2,4,2,7,8,9,1,1,2,4,6,5};
		int c2 = unstableAlternateSlow(arr2);
		System.out.println(Arrays.toString(arr2));
		System.out.println("swap: " + c2);
		
		int[] arr3 = {0,1,3,5,2,4,2,7,8,9,1,1,2,4,6,5};
		int c3 = unstableAlternate(arr3);
		System.out.println(Arrays.toString(arr3));
		System.out.println("swap: " + c3);
		
		int[] arr4 = {0,1,3,5,2,4,2,7,8,9,1,1,2,4,6,5};
		int c4 = stableAlternateByRotate(arr4);
		System.out.println(Arrays.toString(arr4));
		System.out.println("rotate: " + c4);
		
		int[] arr5 = {0,1,3,5,2,4,2,7,8,9,1,1,2,4,6,5};
		stableAlternateByRotateFast(arr5);
		System.out.println(Arrays.toString(arr5));
	}
	
	public static int unstableAlternateSlow(int[] arr) {
		int left = 0, right = arr.length-1, swapCnt = 0;
		while(left < right) {
			if(left % 2 == 1 && arr[left] % 2 == 0){  //left is at odd index, but arr[left] is even
				while(arr[right] % 2 == 0) {  //find a right that is odd to swap
					--right;
				}
				swap(arr, left, right);
				++swapCnt;
			} else if(left % 2 == 0 && arr[left] % 2 == 1){  //left is at even index, but arr[right] is odd
				while(arr[right] % 2 == 1) {  //find a right that is even to swap
					--right;
				}
				swap(arr, left, right);
				++swapCnt;
			}
			++left;
		}
		return swapCnt;
	}
	
	public static int unstableAlternateFast(int[] arr) {
		int left = 0, right = arr.length-1, swapCnt = 0;
		while(left < right) {
			if(left % 2 == 1 && arr[left] % 2 == 0){  //left is at odd index, but arr[left] is even
				 //find a right that is odd to swap (even index, odd val) or (odd index, even val)
				while(right % 2 == 0 && arr[right] % 2 == 0) { 
					--right;
				}
				swap(arr, left, right);
				++swapCnt;
			} else if(left % 2 == 0 && arr[left] % 2 == 1){  //left is at even index, but arr[right] is odd
				//find a right that is even to swap (even index, odd val) or (odd index, even val)
				while(right % 2 == 1 && arr[right] % 2 == 1) {  //find a right that is even to swap
					--right;
				}
				swap(arr, left, right);
				++swapCnt;
			}
			++left;
		}
		return swapCnt;
	}
	
	public static int unstableAlternate(int[] arr) {
		int left = 0, search = 1, end = arr.length-1, swapCnt = 0;
		while(left < end) {
			if(left % 2 == 1 && arr[left] % 2 == 0){  //left is at odd index, but arr[left] is even
				while(arr[search] % 2 == 0) {  //from left+1 find 1st odd value to swap
					++search;
				}
				swap(arr, left, search);
				++swapCnt;
			} else if(left % 2 == 0 && arr[left] % 2 == 1){  //left is at even index, but arr[right] is odd
				while(arr[search] % 2 == 1) {  //from left+1 find 1st even value to swap
					++search;
				}
				swap(arr, left, search);
				++swapCnt;
			}
			++left;
			search = left+1;  //reset search to left+1 to search again
		}
		return swapCnt;
	}
	
	public static int stableAlternateByRotate(int[] arr) {
		int curr = 0, search = 1, end= arr.length-1, rotCnt = 0;
		while(curr < end) {
			if(curr % 2 == 0 && arr[curr] % 2 == 1) {
				while(arr[search] % 2 == 1) {
					++search;
				}
				rotateRight(arr, curr, search, 1);
				++rotCnt;
			} else if(curr % 2 == 1 && arr[curr] % 2 == 0) {
				while(arr[search] % 2 == 0) {
					++search;
				}
				rotateRight(arr, curr, search, 1);
				++rotCnt;
			}
			++curr;
			search = curr+1;
 		}
		return rotCnt;
	}

	public static void rotateRight(int[] arr, int start, int end, int amount) {
		//System.out.println("(" + start + "," + end + "," + amount + ")");
		reverse(arr, end-amount+1, end);
		reverse(arr, start, end-amount);
		reverse(arr, start, end);
	}
	
	public static void reverse(int[] arr, int start, int end) {
		while(start < end) {
			swap(arr, start, end);
			++start;
			--end;
		}
	}
	
	public static void stableAlternateByRotateFast(int[] arr) {
		int len = arr.length, start = 0, end = len-1, find = 0; 
		while(start < end) {  //check all elements
			if((arr[start] & 1) == 0 && (start & 1) == 1) {  //odd index, even element
				while(find < end && (arr[find] & 1) == 0) {  //find nearest odd element
					++find;
				}
				
				if(find < end) {  //make sure loop broke due to odd element found
					rotateRightByOne(arr, start, find);
				} else {  //no more odd element so done
					break;
				}
			} else if((arr[start] & 1) == 1 && (start & 1) == 0) {  //even index, odd element 
				while(find < end && (arr[find] & 1) == 1) {  //find nearest even element
					++find;
				}
				
				if(find < end) {  //make sure loop broke due to even element found
					rotateRightByOne(arr, start, find);
				} else {  //no more even element so done
					break;
				}
			}
			
			++start;  //move to check next element at next index
			find = start;  //reset find to keep up with start
		}
	}
	
	//since this algorithm only requires right rotation by 1, no need to use the triple reverse rotation
	//just remove last element that goes to the front
	//then move arr[i]=arr[i-1] so at the end the front spot is free to insert the last element
	public static void rotateRightByOne(int[] arr, int start, int end) {
		int last = arr[end];
		for(int i = end; i > start; --i) {
			arr[i] = arr[i-1];
		}
		arr[start] = last;
	}
	
	public static void swap(int[] arr, int left, int right) {
		int temp = arr[left];
		arr[left] = arr[right];
		arr[right] = temp;
	}
}
