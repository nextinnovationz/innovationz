package QQueue;

import java.util.ArrayDeque;

public class MaxSlidingWindow {
	public static void main(String[] args) {
		int[] arr = {1,2,3,-1,2,1,0,7};
		maxSlidingWindows(arr, 3);
	}
	
	public static void maxSlidingWindows(int[] arr, int k) {
		//store index of element so its easy to check for window range
		ArrayDeque<Integer> q = new ArrayDeque<Integer>();
		int len = arr.length;
		
		for(int i = 0; i < k; ++i) {
			if(!q.isEmpty() && i - q.peekFirst() >= k) {
				//check if head is still within window range
				q.removeFirst();
				System.out.println("remove head, its out of range");
			}
			
			if(q.isEmpty()) {  
				//just add element if q is empty
				q.addFirst(i);
			} else if(arr[q.peekFirst()] <= arr[i]) {
				//curr is greater than or equal to head
				//remove all less than it
				while(!q.isEmpty()) {
					q.removeFirst();
				}
				q.add(i);
			} else if(arr[q.peekLast()] <= arr[i]) {  
				//head is not tail
				//curr is less than head, but greater than tail
				//remove all until head
				while(q.size() > 1) {
					q.removeLast();
				}
				q.addLast(i);
			} else {  
				//curr is less than head, there is only 1 element in queue
				//just add curr to tail
				q.addLast(i);
			}
		}
		
		//print max (head of deque) at each sliding window
		System.out.println(arr[q.peekFirst()]);
		
		for(int i = k; i < len; ++i) {
			if(!q.isEmpty() && i - q.peekFirst() >= k) {
				//check if head is still within window range
				q.removeFirst();
				System.out.println("remove head, its out of range");
			}
			
			if(q.isEmpty()) {  
				//just add element if q is empty
				q.addFirst(i);
				System.out.println("empty, just add");
			} else if(arr[q.peekFirst()] <= arr[i]) {
				//curr is greater than or equal to head
				//remove all less than it
				while(!q.isEmpty()) {
					q.removeFirst();
				}
				q.add(i);
				System.out.println("remove all front");
			} else if(arr[q.peekLast()] <= arr[i]) {  
				//head is not tail
				//curr is less than head, but greater than tail
				//remove all until head
				while(q.size() > 1) {
					q.removeLast();
				}
				q.addLast(i);
				System.out.println("remove all tails until head");
			} else {  
				//curr is less than head, there is only 1 element in queue
				//just add curr to tail
				q.addLast(i);
				System.out.println("only 1 element");
			}
			
			//print max (head of deque) at each sliding window
			System.out.println(arr[q.peekFirst()]);
		}
	}
}
