package DSHeap;

import java.util.Arrays;

public class Driver {
	public static void main(String[] args) {
		int[] arr = {2,4,3,1,6,4,7,8,0,9,5};
		Heap minheap = new MinHeap();
		minheap.buildHeap(arr);
		System.out.println(Arrays.toString(minheap.heap));
		minheap.print();
		
		while(minheap.size > 1) {
			System.out.println("root: " + minheap.extractRoot());
			minheap.print();
			System.out.println();
		}
		
		Heap maxheap = new MaxHeap();
		maxheap.buildHeap(arr);
		System.out.println(Arrays.toString(maxheap.heap));
		maxheap.print();
		
		while(maxheap.size > 1) {
			System.out.println("root: " + maxheap.extractRoot());
			maxheap.print();
			System.out.println();
		}
	}
}
