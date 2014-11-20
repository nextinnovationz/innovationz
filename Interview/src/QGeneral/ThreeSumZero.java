package QGeneral;

import java.util.Arrays;
import java.util.HashMap;

public class ThreeSumZero {
	public static void main(String[] args) {
		int[] arr = {1,2,14,15,3,4,-1,9,6,8,6,-2};
		findThreeSumZero(arr);
	}
	
	public static void findThreeSumZero(int[] arr) {
		Arrays.sort(arr);
		int len = arr.length;
		
		//a+b+c=0 is the same as a+b=-c
		//consider each element as -c and find two elements that sum to it
		for(int i = 0; i < len; ++i) {
			int neg = -arr[i];
			Pair<Integer> pair = findTwoSumUnsorted(arr, neg);
			if(pair != null) {
				System.out.println("(" + pair.a + "," + pair.b + "," + arr[i] + ")");
			} 
		}
	}
	
	static class Pair<T> {
		public T a, b;
		public Pair(T a, T b) {
			this.a = a;
			this.b = b;
		}
	}
	
	public static Pair<Integer> findTwoSumSorted(int[] arr, int sum) {
		int left = 0, right = arr.length-1;
		while(left < right) {
			if(arr[left] + arr[right] > sum)  //currsum is too big, move right to make it smaller
				--right;
			else if(arr[left] + arr[right] < sum)  //currsum is too small, move left to make it bigger
				++left;
			else {  //currsum is the target sum
				return new Pair<Integer>(arr[left], arr[right]);
			}
		}
		return null;
	}
	
	public static Pair<Integer> findTwoSumUnsorted(int[] arr, int sum) {
		HashMap<Integer,Integer> lookup = new HashMap<Integer,Integer>();
		int len = arr.length;
		for(int i = 0; i < len; ++i) {
			lookup.put(arr[i], i);
		}
		
		for(int i = 0; i < len; ++i) {
			if(lookup.containsKey(sum-arr[i]))
				return new Pair<Integer>(arr[i], arr[lookup.get(sum-arr[i])]);
		}
		return null;
	}
}
