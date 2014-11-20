package QDynamicProgramming;

public class MinCoinMakeChange {
	public static void main(String[] args) {
		int val = 37;
		int[] denom = {1,5,10,25};
		System.out.println("min coins: " + minCoins(val, denom));
	}
	
	public static int minCoins(int val, int[] denom) {
		int[] mem = new int[val+1];
		mem[0] = 0;  //base case val=0 so no change needed 
		
		int len = val+1;  
		
		//initialize mem to -1 so its different from base case
		//this is used to store computation results to avoid recomputing same subproblems
		for(int i = 1; i < len; ++i) {
			mem[i] = -1;
		}
		
		return minCoins(val, denom, mem);
	}
	
	public static int minCoins(int val, int[] denom, int[] mem) {
		//System.out.println("val: " + val);
		if(mem[val] != -1) {
			//System.out.println("\tval already exist in mem, no recompute");
			return mem[val];
		} 
		
		//System.out.println("\tval not in mem, compute");
		int len = denom.length;
		
		//temp array used to find min of this recursive level's val's subproblems
		int minLen = 0;
		int[] min = new int[len];
		
		for(int i = 0; i < len; ++i) {
			//a denom[i] can only be use to reach val if it is <= it
			if(val >= denom[i]) {				
				//do subproblem for each usable denom[i]
				//store min coin change for each subproblem in temporary array to find min later
				min[minLen] = minCoins(val-denom[i], denom, mem);
				++minLen;
			}
		}
		
		//find min of the results from subproblems
		int subMin = min[0];
		for(int i = 1; i < minLen; ++i) {
			if(subMin > min[i])
				subMin = min[i];
		}
		
		//add last coin to the min result
		mem[val] = subMin+1;
		System.out.println("val: " + val + "\tmin: " + mem[val]);
		return mem[val];
	}
}