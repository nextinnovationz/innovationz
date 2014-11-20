package QDynamicProgramming;

import java.util.ArrayList;

public class LongestIncreasingSubsequence {
	public static void main(String[] args) {
		//int[] arr = {3,4,2,5,1,6,7,3,4,2,6};
		int[] arr = {3,4,5,1,2,3,4,6};
		System.out.println(longestIncSub(arr).toString());
	}
	
	public static ArrayList<Integer> longestIncSub(int[] arr) {
		if(arr == null || arr.length == 0)
			return null;
		
		ArrayList<ArrayList<Integer>> allSubs = new ArrayList<ArrayList<Integer>>();
		return longestIncSub(allSubs, arr, 0);
	}
	
	public static ArrayList<Integer> longestIncSub(ArrayList<ArrayList<Integer>> allSubs, int[] arr, int curr) {
		//base case when all elements in arr have been considered
		//look through all existing subsequences and find the longest one in length to return upward
		if(curr == arr.length) {
			return findLongestIncSub(allSubs);
		}
		
		int e = arr[curr];  //current element in arr being appended 
		boolean hasSeq = false;  //a flag that indicated whether the current element was appended to at least one existing sequence
		
		//check e against the last element of each existing subsequence 
		//to determine which one e should append to
		for(ArrayList<Integer> seq : allSubs) {
			if(seq.get(seq.size()-1) < e) {
				seq.add(e);
				hasSeq = true;
			} 
		}
		
		//e didn't get appended to any of the existing subsequence
		//this means that e is smaller than the last element of each subsequence
		//create a new subsequence starting with e
		if(!hasSeq) {
			ArrayList<Integer> newSub = new ArrayList<Integer>();
			newSub.add(e);
			allSubs.add(newSub);
		}
		
		//recurse to proceed with remaining elements in arr to determine longest increasing subsequence
		return longestIncSub(allSubs, arr, curr+1);
	}
	
	public static ArrayList<Integer> findLongestIncSub(ArrayList<ArrayList<Integer>> allSubs) {
		int max = 0, maxIdx = 0, len = allSubs.size();
		for(int i = 0; i < len; ++i) {
			if(allSubs.get(i).size() > max) {
				max = allSubs.get(i).size();
				maxIdx = i;
			}
		}
		return allSubs.get(maxIdx);
	}
}
