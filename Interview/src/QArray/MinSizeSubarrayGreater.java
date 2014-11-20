package QArray;

import java.util.Arrays;

public class MinSizeSubarrayGreater {
	public static void main(String[] args) {
		int[] arr = {10,1,1,2,3,4,9,3,18,5,2,0,1,1,7,4,1,8,6,6,12};
		System.out.println(Arrays.toString(findSub(arr, 12)));
	}
	
	public static int[] findSub(int[] arr, int givenSum) {
		//find smallest subarray such that the sum is greater or equal to than the givenSum
		int start = 0, end = 0, sum = 0, len = arr.length;
		int prevStart = 0, prevEnd = len-1, prevSum = Integer.MAX_VALUE;
		
		while(end < len) {
			//accumulate sub until sum is > givenSum
			while(end < len && sum+arr[end] < givenSum) {
				sum += arr[end];
				++end;
			}
			
			//loop broke because adding next arr[end] to sum will >= givenSum
			//so must add it now (don't increment so end stays at last index of current sub)
			if(end < len)
				sum += arr[end];
			else  //current sum is < givenSum and there are no more elements so done (use prev sub)
				break;
			
			System.out.println("before: " + sum);
			System.out.println("[" + start + "," + end + "]");
			
			//try to trim down length of sum from beginning
			//as long as the reduced sum is still > givenSum
			while(start <= end && sum-arr[start] >= givenSum) {
				sum -= arr[start];
				++start;
			}
			
			System.out.println("after: " + sum);
			System.out.println("[" + start + "," + end + "]");
			//System.out.println("prev: [" + prevStart + "," + prevEnd + "] " + prevSum);
			
			//update prevSum if currSum is smaller and shorter (better)
			if(sum <= prevSum && prevEnd-prevStart+1 > end-start+1) {
				System.out.println("update prev to curr");
				prevStart = start;
				prevEnd = end;
				prevSum = sum;
			}
			
			//increment start and end for next sub accumulation
			++end;
			start = end;
			sum = 0;
		}
		
		//move end and start back one since its over by 1 from breaking loop
		--end;
		--start;
		
		//get the final sub by comparing last sub and the prev sb=ub
		//the loop could break above when the remaining elements cannot yield givenSum so must check for it
		//otherwise, if sum is also >= givenSum, pick the smaller sum and shorter sub between curr sub and prev sub
		if(sum < givenSum  || (sum >= prevSum && prevEnd-prevStart+1 < end-start+1)) {
			start = prevStart;
			end = prevEnd;
		}
		
		System.out.println("final: [" + start + "," + end + "]");
		
		//populate sub array with start and end 
		int[] sub = new int[end-start+1];
		int j = 0;
		for(int i = start; i <= end; ++i) {
			sub[j] = arr[i];
			++j;
		}
		
		return sub;
	}
}
