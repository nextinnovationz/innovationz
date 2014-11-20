package QQueue;

import java.util.Stack;

public class StackToQueue {
	public static void main(String[] args) {
		MyQueue<Integer> q = new MyQueue<Integer>();
		q.enqueue(1);
		q.enqueue(2);
		q.dequeue();
		q.enqueue(3);
		q.dequeue();
		q.enqueue(4);
		q.enqueue(5);
		q.print();
	}
	
	static class MyQueue<T> {
		public Stack<T> pushStack, popStack;
		public int size;
		
		public MyQueue() {
			this.pushStack = new Stack<T>();
			this.popStack = new Stack<T>();
			this.size = 0;
		}
		
		public void enqueue(T data) {
			pushStack.push(data);
			++size;
		}
		
		public T dequeue() {
			if(popStack.isEmpty()) {
				while(!pushStack.isEmpty()) {
					popStack.push(pushStack.pop());
				}
				
				if(popStack.isEmpty()) {
					return null;
				}
			}
			--size;
			return popStack.pop();
		}
		
		public T peek() {
			if(popStack.isEmpty()) {
				while(!pushStack.isEmpty()) {
					popStack.push(pushStack.pop());
				}
				
				if(popStack.isEmpty()) {
					return null;
				}
			}
			return popStack.peek();
		}
		
		public void print() {
			int i = size;
			while(i > 0) {
				System.out.println(dequeue());
				--i;
			}
		}
	}
}
