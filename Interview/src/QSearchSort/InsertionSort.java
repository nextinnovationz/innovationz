package QSearchSort;

import java.util.Arrays;

public class InsertionSort {
	public static void main(String[] args) {
		int[] arr = {87,1,3,4,0,2,3,7,5,4,10,1,0,8,99};
		insertionsort(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	public static void insertionsort(int[] arr) {
		if(arr == null || arr.length == 0)
			return;
	
		int len = arr.length;
		int curr;
		for(int i = 0; i < len; ++i) {
			//must save arr[i] because it could be replaced later 
			//when moving everything from j to i right by 1 (replaced arr[i] by arr[i-1]
			curr = arr[i];
			//find correct place in sorted section to insert this element
			for(int j = 0; j < i; ++j) {
				//so this element goes in front of arr[j]
				//right shift everything from arr[j] to end by 1
				if(arr[i] < arr[j]) {  //found correct position j
					//from j to i (i is right of j)
					//because removed current element at i 
					//the correct insertion spot is either <= i
					//if it is i then its already in correct spot
					//otherwise, it must be in the front (i's left)
					for(int k = i; k > j; --k) { 
						arr[k] = arr[k-1];  //move everything right by 1 (copy k-1 to k)
					}
 					arr[j] = curr;  //put curr=old arr[i] into correct position j
					break;
				}
			}
		}
	}
}
