package QStack;

import java.util.ArrayDeque;
import java.util.Queue;

public class QueueToStack {
	public static void main(String[] args) {
		MyStack<Integer> s = new MyStack<Integer>();
		s.push(3);
		s.push(4);
		s.push(5);
		s.pop();
		s.pop();
		s.push(8);
		s.pop();
		s.push(7);
		s.push(9);
		s.print();
	}
	
	static class MyStack<T> {
		public int size;
		public Queue<T> q1, q2;
		
		public MyStack() {
			this.size = 0;
			this.q1 = new ArrayDeque<T>();
			this.q2 = new ArrayDeque<T>();
		}
		
		public void push(T data) {
			if(q2.isEmpty()) {
				q1.add(data);
			} else {
				q2.add(data);
				
			}
			++size;
		}
		
		public T pop() {
			if(size > 0) {
				if(!q1.isEmpty()) {
					int i = q1.size()-1;
					while(i > 0) {
						q2.add(q1.remove());
						--i;
					}
					--size;
					return q1.remove();
				} else {
					int i = q2.size()-1;
					while(i > 0) {
						q1.add(q2.remove());
						--i;
					}
					--size;
					return q2.remove();
				}
			}
			return null;
		}
		
		public T peek() {
			if(size > 0) {
				if(!q1.isEmpty()) {
					int i = q1.size()-1;
					while(i > 0) {
						q2.add(q1.remove());
						--i;
					}
					return q1.peek();
				} else {
					int i = q2.size()-1;
					while(i > 0) {
						q1.add(q2.remove());
						--i;
					}
					return q2.peek();
				}
			}
			return null;
		}
		
		public void print() {
			int i = size;
			while(i > 0) {
				System.out.println(pop());
				--i;
			}
		}
	}
}
