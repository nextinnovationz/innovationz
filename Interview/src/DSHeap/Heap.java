package DSHeap;

import java.util.ArrayDeque;

public abstract class Heap implements HeapOperations{
	public int[] heap;
	public int size, capacity;
	private static final int INITIAL_CAPACITY = 10;
	
	public Heap() {
		this(INITIAL_CAPACITY);
	}
	
	public Heap(int capacity) {
		this.capacity = capacity;
		this.heap = new int[capacity];
		this.size = 1;  //root starts at index 1
	}
	
	public void regrow() {
		capacity = 2 * capacity + 1;
		int[] temp = new int[capacity];
		for(int i = 0; i < size; ++i) {
			temp[i] = heap[i];
		}
		heap = temp;
	}
	
	public void swap(int i, int j) {
		int temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
	}
	
	public void print() {
		ArrayDeque<Integer> q = new ArrayDeque<Integer>();
		q.add(1);
		int curr = 1, left = 0, right = 0;
		int currLvl = 1, nxtLvl = 0;
		while(!q.isEmpty()) {
			while(currLvl > 0) {
				curr = q.remove();
				System.out.print(heap[curr] + "  ");
				left = 2*curr;
				right = 2*curr+1;
				if(left < size) {
					q.add(left);
					++nxtLvl;
				}
				if(right < size) {
					q.add(right);
					++nxtLvl;
				}
				--currLvl;
			}
			System.out.println();
			currLvl = nxtLvl;
			nxtLvl = 0;
		}
		System.out.println();
	}
}
