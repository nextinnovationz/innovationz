package QSearchSort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MatrixSort {
	public static void main(String[] args) {
		int[][] mat = generateMatrix("123433893578", 4, 4);
		int[] s = sort(mat);
		System.out.println(Arrays.toString(s));
	}
	
	static class Item {
		public int arrIdx, offsetIdx, val;
		public Item(int arrIdx, int offsetIdx, int val) {
			this.arrIdx = arrIdx;
			this.offsetIdx = offsetIdx;
			this.val = val;
		}
	}
	
	static class ItemComparator implements Comparator<Item> {
		public int compare(Item a, Item b) {
			if(a.val < b.val)
				return -1;
			else if(a.val > b.val)
				return 1;
			else
				return 0;
		}
	}
	
	public static int[][] generateMatrix(String pattern, int rowLen, int colLen) {
		int[][] matrix = new int[rowLen][colLen];
		int pos = 0, len = pattern.length(), curr = 0, row = 0, col = 0;
		while(pos < len) {
			curr = pattern.charAt(pos) - '0';
			matrix[row][col] = curr;
			++col;
			if(col == rowLen) {
				col = 0;
				++row;
			}
			++pos;
		}
		return matrix;
	}
	
	public static int[] sort(int[][] mat) {
		//this is similar to external merge sort on n arrays 
		//picking min out of n arrays using PQ is O(1) 
		//brute force min will take O(n) 
		
		int[] sorted = new int[mat.length*mat[0].length];
		PriorityQueue<Item> pq = new PriorityQueue<Item>(sorted.length, new ItemComparator());
		
		for(int i = 0; i < mat.length; ++i) {
			pq.add(new Item(0, 0, mat[i][0]));
		}
		
		//arrIdx is the row number
		//offset is the col number 
		//so mat[arrIdx][offset] will get the (offset)th element in the (arrIdx)th array
		int sortedIdx = 0;
		Item min = null;
 		while(!pq.isEmpty()) {
 			min = pq.remove();
 			sorted[sortedIdx] = min.val;
 			++sortedIdx;
 			if(min.offsetIdx+1 < mat[min.arrIdx].length) {
 				pq.add(new Item(min.arrIdx, min.offsetIdx+1, mat[min.arrIdx][min.offsetIdx+1]));	
 			}
		}
 		
 		return sorted;
	}
}
