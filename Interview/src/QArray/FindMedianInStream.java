package QArray;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class FindMedianInStream {
	public static void main(String[] args) {
		int cap = 100;
		Median med = new Median(cap);
		int[] stream = {2,3,7,4,5,2,1,0,3,7,5,9,30,22,12,32,11,4,8,2,1,7,9,10,3,0,0,2,1,13};
		for(int i : stream) {
			med.add(i);
			System.out.println("med: " + med.getMedian() + "\tmaxSize: " + med.maxHeap.size() + " minSize: " + med.minHeap.size());
		}
		
		Arrays.sort(stream);
		System.out.println("med from sort: " + stream[(stream.length-1)/2]);
	}
	
	static class Median {
		public PriorityQueue<Integer> minHeap, maxHeap;
		public int len, capacity;
		public static final int INITIAL_CAPACITY = 1000;
		
		public Median(int capacity) {
			this.capacity = capacity;  //cannot use Integer.MAX_VALUE for PQ size, because its too much memory
			this.minHeap = new PriorityQueue<Integer>(capacity/2, new MinComparator());
			this.maxHeap = new PriorityQueue<Integer>(capacity/2, new MaxComparator());
			this.len = 0;
		}
		
		public void add(int i) {
			//the two PQs won't fit any more new elements so just ignore it
			if(len == capacity)
				return;
			
			//initially no elements at all so just add first element to maxHeap
			if(len == 0)
				maxHeap.add(i);
			else if(i > maxHeap.peek()) {  //new element is > max of left side
				minHeap.add(i);  //new element is greater than all of left, so add to right
				if(minHeap.size()-1 > maxHeap.size()) {  //rebalance two sizes if difference is > 1
					maxHeap.add(minHeap.remove());  //remove smallest in right and add it to left 
				}
			} else {  //new element is <= max of left side
				maxHeap.add(i);  //new element is smaller than all of right, so add to left
				if(maxHeap.size()-1 > minHeap.size()) {  //rebalance two sizes if difference is > 1
					minHeap.add(maxHeap.remove());  //remove largest in left and add it to right
				}
			}
			++len;
		}
		
		public int getMedian() {
			int minSize = minHeap.size();
			int maxSize = maxHeap.size();
			
			if(minSize == maxSize)  //even len and partitioned evenly on left and right
				return (minHeap.peek() + maxHeap.peek()) / 2;  //take average of left max and right min
			else if(minSize > maxSize)  //odd len and right has 1 more element so its min of right
				return minHeap.peek();
			else  //odd len and left has 1 more element so its max of left
				return maxHeap.peek();
		}
	}
	
	static class MaxComparator implements Comparator<Integer>{
		@Override
		public int compare(Integer arg0, Integer arg1) {
			if(arg0 > arg1)
				return -1;
			else if(arg0 < arg1)
				return 1;
			else
				return 0;
		}
	}
	
	static class MinComparator implements Comparator<Integer> {
		@Override
		public int compare(Integer arg0, Integer arg1) {
			if(arg0 > arg1)
				return 1;
			else if(arg0 < arg1)
				return -1;
			else
				return 0;
		}
	}
}	
