package QArray;

public class OneMissingNumber {
	public static void main(String[] args) {
		int[] arr = {0,1,2,3,5,6,7,8,9,10,11,12,13};
		System.out.println(missing(arr));
		System.out.println(missingOneEasy(arr));
	}
	
	public static int missing(int[] arr) {
		//number ranges from 0 to n (works for both sorted and unsorted)
		//use a 32bit bit vector to keep track of each number in the arr
		//set each bit when that number is in arr
		//return the 1st nonset bit index to represent the 1st missing number
		//don't need to rescan array at the end, since array is sorted,
		//then the 1st gap between two set bits will be the missing number
		//can skip scanning all numbers if such gap is found
		//works for more than 1 missing numbers
		
		int len = arr.length;
		
		//missing 1 number so the length of full array is arr.length+1
		boolean[] bitvector = new boolean[arr.length+1];
		
		//set the bit that exist in the array using each element as bit index
		for(int i = 0; i < len; ++i) {
			//set bit(arr[i) as true to mark it
			bitvector[arr[i]] = true; 
		}
		
		//find 1st nonset bit for 1st missing number
		for(int i = 0; i < len; ++i) {
			if(!bitvector[i])
				return i;
		}
		
		return -1;
	}
	
	public static int missingOneEasy(int[] arr) {
		//total is n(n+1)/2 (1 to n)
		//sum up all number in arr 
		//subtract current sum from total sum for the missing difference
		//only works for 1 missing number
		
		int currsum = 0, len = arr.length;
		for(int i = 0; i < len; ++i) {
			currsum += arr[i];
		}
		
		return ((len*(len+1))/2) - currsum; 
	}
}
