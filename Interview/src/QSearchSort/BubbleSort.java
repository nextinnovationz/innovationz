package QSearchSort;

import java.util.Arrays;

public class BubbleSort {
	public static void main(String[] args) {
		int[] arr = {87,1,3,4,0,2,3,7,5,4,10,1,0,8,99};
		bubblesort(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	public static void bubblesort(int[] arr) {
		int len = arr.length;
		int temp, end;
		boolean swap = false;
		
		//run len many passes on the array
		for(int i = 0; i < len; ++i) {
			//each pass sorts every adjacent pair
			//compare arr[j] with arr[j-1] each step of pass
			//after each pass, the largest remaining number goes to the end 
			//so i+1 sorted at after each pass
			end = len-i;
			for(int j = 1; j < end; ++j) {
				if(arr[j-1] > arr[j]) {
					temp = arr[j];
					arr[j] = arr[j-1];
					arr[j-1] = temp;
					swap = true;
				}
			}
			
			//no swap at all in the previous pass 
			//array must be all sorted
			if(!swap)
				break;
		}
	}
}
