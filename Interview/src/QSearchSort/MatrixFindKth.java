package QSearchSort;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MatrixFindKth {
	public static void main(String[] args) {
		
	}
	
	static class Item {
		public int val, row, col;
		public Item(int val, int row, int col) {
			this.val = val;
			this.row = row;
			this.col = col;
		}
	}
	
	static class ItemComparator implements Comparator<Item> {
		@Override
		public int compare(Item arg0, Item arg1) {
			return arg0.val-arg1.val;
		}
	}
	
	public static int findKth(int[][] mat, int k) {
		if(k < 1 || k >= mat.length*mat[0].length)
			return -1;
		
		//create a min heap of size k
		PriorityQueue<Item> pq = new PriorityQueue<Item>(k, new ItemComparator());
		
		//initialize pq with 1st element
		pq.add(new Item(mat[0][0], 0, 0));
		Item min = null;
		
		//while counter is not 0, keep removing min (to get kth smallest element from index 1)
		while(k > 0) {
			min = pq.remove();
			
			//each remove will insert min's next row element (directly under it) into pq
			//if no more element underneath min, insert the immediate right element on same row into pq
			if(min.row+1 == mat.length)
				pq.add(new Item(mat[min.row][min.col+1], min.row, min.col+1));
			else
				pq.add(new Item(mat[min.row+1][min.col], min.row+1, min.col));
			--k;
		}
		
		//min could be null due to initialization 
		if(min != null)
			return min.val;
		else
			return -1;
	}
}
