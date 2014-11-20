package QStack;

import java.util.Stack;

public class SortStack {
	public static void main(String[] args) {
		Stack<Integer> stk = new Stack<Integer>();
		stk.push(5);
		stk.push(4);
		stk.push(9);
		stk.push(1);
		stk.push(0);
		stk.push(2);
		printStack(sortStack(stk));
	}

	public static Stack<Integer> sortStack(Stack<Integer> stk) {
		int tempHolderCount = 0;
		int curr = 0;
		Stack<Integer> sorted = new Stack<Integer>();
		
		while(!stk.isEmpty()) {
			//find the correct position to insert curr in sorted stack
			curr = stk.pop();
			System.out.println("curr: " + curr);
			while(!sorted.isEmpty()) {
				//just push to top since the current top is less than this one
				if(sorted.peek() < curr) {
					System.out.println("\tinsert at " + sorted.size());
					sorted.push(curr);
					break;
				} else {
					stk.push(sorted.pop());
					++tempHolderCount;
				}
			}
			
			//curr's position in sorted stack is the very bottom 
			if(sorted.isEmpty()) {
				System.out.println("\tinserted at " + sorted.size());
				sorted.push(curr);
			}
			
			//restore all those popped temporarily in s1 back to s2
			while(tempHolderCount > 0) {
				System.out.println("\t\trestoring " + stk.peek());
				sorted.push(stk.pop());
				--tempHolderCount;
			}
		}
		
		return sorted;
	}
	
	public static void printStack(Stack<Integer> s) {
		while(!s.isEmpty()) {
			System.out.print(s.pop() + "  ");
		}
		System.out.println();
	}
}
