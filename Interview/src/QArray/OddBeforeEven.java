package QArray;

import java.util.Arrays;

public class OddBeforeEven {
	public static void main(String[] args) {
		int[] arr = {1,2,4,3,6,5,7,4,0,8,1,2,6};
		reorder(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	public static void reorder(int[] arr) {
		ReorderRule r = new ReorderOddEven();
		r.reorder(arr);
	}

	static interface ReorderRule {
		public void reorder(int[] arr);
	}
	
	static class ReorderOddEven implements ReorderRule {

		@Override
		public void reorder(int[] arr) {
			int len = arr.length, left = 0, right = len-1;
			
			//can stop when left cross right because there would be no more odd in the right
			//to swap with an even in the left so everything right must be even and everything left are odd
			while(left < right) {
				//left is even, need to find an odd from the end to swap
				if((arr[left] & 1) == 0) {
					//find odd from the end using right
					while(left < right && (arr[right] & 1) == 0)
						--right;
					
					//make sure left is still less than right
					//otherwise, an odd would be chosen from the left which was already properly reordered
					if(left < right && (arr[right] & 1) == 1) {
						swap(arr, left, right);
						--right;
					}
				}
				++left;
			}
		}
		
		public static void swap(int[] arr, int a, int b) {
			int temp = arr[a];
			arr[a] = arr[b];
			arr[b] = temp;
		}
	}
}
