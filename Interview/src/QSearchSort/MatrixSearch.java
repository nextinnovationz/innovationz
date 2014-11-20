package QSearchSort;

import Exception.InvalidInputException;
import Exception.NoResultException;

public class MatrixSearch {
	public static void main(String[] args) {
		int[][] matA = generateMatrix("123433893578", 4, 4);
		try {
			System.out.println("find 5: " + search1(matA, 5));
			System.out.println("find 3: " + search1(matA, 3));
			System.out.println("find 0: " + search1(matA, 0));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		int[][] matB = generateMatrix("012334556789", 4, 4);
		try {
			System.out.println("find 5: " + search2(matB, 5));
			System.out.println("find 0: " + search2(matB, 0));
			System.out.println("find -3: " + search2(matB, -3));
		} catch(Exception e) {
			e.printStackTrace();
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
	
	/*
		each row increases
		1234
		2589
		3678
		do column and row eliminate one at a time at each iteration across the diagonal from top right to bottom left
	*/
	public static int search1(int[][] matrix, int target) throws NoResultException, InvalidInputException {
		if(matrix == null)
			throw new InvalidInputException("bad matrix input");
		
		int row = 0, col = matrix[0].length-1;
		while(col >= 0 && row < matrix.length) {
			if(matrix[row][col] > target) {
				--col;  //look in next row for larger
			} else if(matrix[row][col] < target) {
				++row;  //look in the same row for smaller 
			} else {
				return matrix[row][col];
			}
		}
		
		throw new NoResultException("cannot find target in matrix after search");
	}
	
	/*
		each row increases, each column increases
		0123
		4567
		7889
		treat 2D as 1D and do binary search, use / and % to convert mid to row and col
	*/
	public static int search2(int[][] matrix, int target) throws NoResultException, InvalidInputException {
		if(matrix == null)
			throw new InvalidInputException("bad matrix input");
	
		int curr = 0, rowLen = matrix.length, colLen = matrix[0].length;
		int left = 0, right = rowLen * colLen-1, mid = 0;
	
		while(left <= right) {
			mid = (left+right)/2;
			//System.out.println("mid: " + mid + "\t(" + (mid/rowLen) + "," + (mid%colLen) + ")");
			curr = matrix[mid/rowLen][mid%colLen];
			if(curr > target) {
				//System.out.println("\tgo left - " + curr + ">" + target);
				right = mid-1;
			} else if(curr < target) {
				//System.out.println("\tgo right - " + curr + "<" + target);
				left = mid+1;
			} else {
				return curr;
			}
		}
		
		throw new NoResultException("cannot find target in matrix after search");
	}
}
