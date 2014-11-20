package QArray;

public class MaximumSubarray {
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,-9,3,-8,-6,4,7,8,9,-20,-4,0,2,1,4,5,6};
		findMaxSub(arr);
	}
	
	public static void findMaxSub(int[] arr) {
		int currSum = 0, prevMax = arr[0], currMax = arr[0];
		int prevStart = 0, prevEnd = 0, currStart = 0, currEnd = 0;
		int curr = 0, len = arr.length;
		while(curr < len) {
			currSum += arr[curr];
			System.out.println("\nindex: " + curr + "\tcurr: " + arr[curr] + "\tcurrSum: " + currSum);
			if(currSum > currMax) {  //add curr to sum if it increases it
				currMax = currSum;  
				currEnd = curr;
				System.out.println("\tcurrSum is greater than currMax, keep curr");
				System.out.println("\tcurrMax: " + currMax + "\t(" + currStart + "," + currEnd + ")");
			} else if(currSum < 0) {  //curr sum is <0 after including the current element 
				System.out.println("\tcurrSum < 0, so next index is start of new subarray");
				//if the subarray found previously has a larger sum than this one, then keep old one
				if(currMax > prevMax) {
					System.out.println("\tcurrMax is greater than prevMax so replace it");
					//adding curr to sum is less than curr itself so exclude it
					//curr+1 is new start for next subarray
					//save the prev subarray
					prevStart = currStart;
					prevEnd = currEnd;
					prevMax = currMax-arr[curr];					
				} else {
					System.out.println("\tcurrMax os less than prevMax so don't replace it");
				}
				
				//for next subarray
				currStart = curr+1;
				currEnd = curr+1;
				currSum = 0;
				if(curr+1 < len)
					currMax = arr[curr+1];
			}
			
			System.out.println("\tend of iteration:\tcurrMax: " + currMax + "\t(" + currStart + "," + currEnd + ")");
			++curr;
		}
		
		//print the larger max of prev and curr
		if(prevMax > currMax) {
			currMax = prevMax;
			currStart = prevStart;
			currEnd = prevEnd;
			System.out.println("[" + prevStart + "," + prevEnd + "] = " + prevMax);
		} else {
			System.out.println("[" + currStart + "," + currEnd + "] = " + currMax );
		}
		
		for(int i = currStart; i <= currEnd; ++i) {
			System.out.print(arr[i] + "  ");
		}
		System.out.println();
	}
}
