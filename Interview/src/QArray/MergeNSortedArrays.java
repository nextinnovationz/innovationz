package QArray;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeNSortedArrays {
	public static void main(String[] args) {
		int[][] arrs = {{7}, {1,3,5,8,8,9}, {3,4,10}, {0,1,1,7,8}, {2}, {6,8,11,14}, {9,10,12}, {3,4,4,9,16}, {10,23}};
		int[] sorted = merge(arrs);
		System.out.println(Arrays.toString(sorted));
	}
	
	static class Item {
		public int row, col, val;
		public Item(int row, int col, int val) {
			this.row = row;
			this.col = col;
			this.val = val;
		}
	}
	
	static class ItemComparator implements Comparator<Item> {
		@Override
		public int compare(Item arg0, Item arg1) {
			return arg0.val - arg1.val;
		}
	}
	
	public static int[] merge(int[][] arrs) {
		if(arrs == null || arrs.length == 0) 
			return new int[0];
		
		int len = 0;
		for(int[] arr : arrs) {
			len += arr.length;
		}
		
		if(len == 0)
			return new int[0];
		
		int[] sorted = new int[len];
		int idx = 0;
		
		PriorityQueue<Item> pq = new PriorityQueue<Item>(arrs.length, new ItemComparator());
		for(int i = 0; i < arrs.length; ++i) {
			Item it = new Item(i, 0, arrs[i][0]);
			pq.add(it);
		}
		
		Item min = null;
		while(!pq.isEmpty()) {
			min = pq.remove();
			if(arrs[min.row].length > min.col+1) {
				pq.add(new Item(min.row, min.col+1, arrs[min.row][min.col+1]));
			}
			
			sorted[idx] = min.val;
			++idx;
		}
		
		return sorted;
	}
}
