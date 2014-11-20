package QRecursion;

public class Fibonacci {
	public static void main(String[] args) {
		System.out.println("fib: " + fibRecursive(9));
		System.out.println("fib: " + fibMemo(9));
	}
	
	//fib(0) == fib(1) == 1
	//fib(2) = fib(1) + fib(0) = 2
	//fib(3) = fib(2) + fib(1) = 3
	//fib(4) = fib(3) + fib(2) = 5
	public static int fibRecursive(int n) {
		if(n < 0)
			return -1;
		if(n == 1 || n == 0) 
			return 1;
		return fibRecursive(n-1) + fibRecursive(n-2);
	}
	
	public static int fibMemo(int n) {  //dynamic programming
		if(n < 0)
			return -1;
				
		if(n < 2)
			return 1;
		
		int[] mem = new int[n+1];  //save results without repeating same computations
		mem[0] = 1;
		mem[1] = 1;
		
		for(int i = 2; i <= n; ++i) {
			mem[i] = mem[i-1] + mem[i-2];  //use previous results to compute
		}
		
		return mem[n];
	}
}
