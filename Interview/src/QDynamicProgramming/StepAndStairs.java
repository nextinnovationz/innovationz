package QDynamicProgramming;

public class StepAndStairs {
	public static void main(String[] args) {
		int top = 9;
		System.out.println("total ways to reach level " + top + ": " + countWaysIterative(top));
		System.out.println("total ways to reach level " + top + ": " + countWaysRecursive(top));
	}
	
	public static int countWaysIterative(int top) {
		if(top == 0)
			return 0;  //0 way to reach top since no stairs
		if(top == 1)
			return 1;  //1 way to reach top by going 1 step
		
		int[] mem = new int[top+1];
		mem[0] = 0;
		mem[1] = 1;
		
		int oneHop = 0, twoHops = 0, threeHops = 0;
		
		for(int i = 2; i <= top; ++i) {
			oneHop = mem[i-1];
			twoHops = mem[i-2];
			
			if(i == 2)
				threeHops = 0;
			else
				threeHops = mem[i-3];
			
			mem[i] = oneHop + twoHops + threeHops;
		}
		
		return mem[top];
	}
	
	public static int countWaysRecursive(int top) {
		if(top == 0)
			return 0;  //0 way to reach top since no stairs
		if(top == 1)
			return 1;  //1 way to reach top by going 1 step
		
		int[] mem = new int[top+1];  //store results instead of recomputing
		
		//base cases
		mem[0] = 0;
		mem[1] = 1;
		
		return countWaysRecursive(top, mem);
	}
	
	public static int countWaysRecursive(int top, int[] mem) {	
		//some recursive calls may pass top as less than 0 so need to cover that
		//when top is 2, there are calls for top-1=1, top-2=0, top-3=-1
		//when recursion, reached base case, it returns upward and store results for each level
		if(top < 0) {
			System.out.println("\tbase case is " + top);
			return 0;
		}
		
		if(mem[top] != 0) {  //already computed this top so just get it
			System.out.println("\talready computed " + top);
			return mem[top];
		} else {  //haven't computed this top yet, need to do it based on previous 3 
			System.out.println("\tneed to recursively compute " + top);
			mem[top] = countWaysRecursive(top-1, mem) + 
					countWaysRecursive(top-2, mem) + 
					countWaysRecursive(top-3, mem);
			
			return mem[top];	
		}
	}
}
