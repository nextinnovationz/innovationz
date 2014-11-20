package QArray;

import java.util.Arrays;

public class InplaceColorSort {
	public static void main(String[] args) {
		int[] arr = {0,0,1,1,0,2,2,0,1,2,1,2,0,1,2,2,2,2,1,0};
		sort(arr);
		System.out.println(Arrays.toString(arr));
		
		int[] arr2 = {2,2,1,1,0,1,0,2,1,0,0,1,1,2,1,2,0,1,2,0,0,1,2,0,1,0,1,2};
		sort(arr2);
		System.out.println(Arrays.toString(arr2));
	}
	
	//0 = red
	//1 = green
	//2 = blue
	public static void sort(int[] arr) {
		int left = 0, right = arr.length-1, green = right;
		while(left < right) {
			if(arr[left] == 2) {  //swap blue with red or green

				//find rightmost green or red to swap blue with
				while(left < right && arr[right] == 2) {
					--right;
				}
				swap(arr, left, right);  
				
				//swapped with green so make sure left is not incremented 
				//so next iteration will find a red to swap with this green
				if(arr[left] == 1)  
					--left;  //left will always get increment at the end, so decrease it now to cancel the effect and stay at this green index for next iteration
				
				//right now points to a blue (everything to the right of this are also blue)
				--right;
			} else if(arr[left] == 1) {  //swap green with red
				
				//find rightmost red to swap green with
				while(left < green && arr[green] != 0) {  
					--green;
				}
				
				//make sure green is not using a red that on the left side that was sorted already
				//if so, then green is in the correct position since there are no more red on the right to swap
				//this means that all the red are sorted to the left 
				if(left < green)  
					swap(arr, left, green);
				
				//green now points to a green (everything to the right of this are green or blue)
				--green;  
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
