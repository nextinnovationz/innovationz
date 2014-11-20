package QArray;

public class PrintSpiral {
	public static void main(String[] args) {
		int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
		printTopClockwise(matrix);  //start at top left
		printTopCounterclockwise(matrix);  //start at top right
		printBottomClockwise(matrix);  //start at bottom right
		printBottomCounterclockwise(matrix);  //start at bottom left
	}
	
	public static void printTopClockwise(int[][] matrix) {
		int top = 0, bottom = matrix.length-1, left = 0, right = matrix[0].length-1;
		int row = top, col = left;
		while(row >= top && row <= bottom && col >= left && col <= right) {
			while(col <= right) {  //top row, left to right
				System.out.print(matrix[row][col] + "  ");
				++col;  //go right a col each time
			}
			++top;  //top got moved down by 1 row
			col = right;  //col starts at right
			row = top;  //row starts at top
			
			while(row <= bottom) {  //right row, top to bottom 
				System.out.print(matrix[row][col] + "  ");
				++row;
			}
			--right;  //right got moved left by 1 col
			col = right;  //col starts at right
			row = bottom;  //row starts at bottom
			
			while(col >= left) {  //bottom row, right to left
				System.out.print(matrix[row][col] + "  ");
				--col;  //go left a col each time
			}
			--bottom;  //bottom got moved up 1 row
			col = left;  //col starts at left
			row = bottom;  //row starts at bottom
			
			while(row >= top) {  //left col, bottom to top
				System.out.print(matrix[row][col] + "  ");
				--row;  //go up a row each time
			}
			++left;  //left got moved right by 1 col
			col = left;  //col starts at left
			row = top;  //row starts at top
		}
		
		System.out.println();
	}
	
	public static void printTopCounterclockwise(int[][] matrix) {
		int top = 0, bottom = matrix.length-1, left = 0, right = matrix[0].length-1;
		int row = top, col = right;
		while(row >= top && row <= bottom && col >= left && col <= right) {
			while(col >= left) {  //go right to left at top row
				System.out.print
				(matrix[row][col] + "  ");
				--col;
			}
			++top;  //eliminate top row
			row = top;
			col = left;
			
			while(row <= bottom) { //go top to bottom at left col
				System.out.print(matrix[row][col] + "  ");
				++row;
			}
			++left;  //eliminate left col
			row = bottom;
			col = left;
			
			while(col <= right) {  //go left to right at bottom row
				System.out.print(matrix[row][col] + "  ");
				++col;
			}
			--bottom;  //eliminate bottom row
			row = bottom;
			col = right;
			
			while(row >= top) {  //go bottom to top at right col
				System.out.print(matrix[row][col] + "  ");
				--row;
			}
			--right;  //eliminate right col
			row = top;
			col = right;
		}
		
		System.out.println();
	}
	
	public static void printBottomClockwise(int[][] matrix) {
		int top = 0, bottom = matrix.length-1, left = 0, right = matrix[0].length-1;
		int row = bottom, col = right;
		while(row >= top && row <= bottom && col >= left && col <= right) {
			while(col >= left) {  //go from right to left at bottom row
				System.out.print(matrix[row][col] + "  ");
				--col;
			}
			--bottom;  //eliminate bottom row
			row = bottom;
			col = left;
			
			while(row >= top) {  //go from bottom to top at left col
				System.out.print(matrix[row][col] + "  ");
				--row;
			}
			++left;  //eliminate left col
			row = top;
			col = left;
			
			while(col <= right) {  //go from left to right at top row
				System.out.print(matrix[row][col] + "  ");
				++col;
			}
			++top;  //eliminate top row
			row = top;
			col = right;
			
			while(row <= bottom) {  //go from top to bottom at right row
				System.out.print(matrix[row][col] + "  ");
				++row;
			}
			--right;  //eliminate right col
			row = bottom;
			col = right;
		}
		System.out.println();
	}
	
	public static void printBottomCounterclockwise(int[][] matrix) {
		int top = 0, bottom = matrix.length-1, left = 0, right = matrix[0].length-1;
		int row = bottom, col = left;
		while(row >= top && row <= bottom && col >= left && col <= right) {
			while(col <= right) {  //go left to right at bottom row
				System.out.print(matrix[row][col] + "  ");
				++col;
			}
			--bottom;  //eliminate bottom row
			row = bottom;
			col = right;
			
			while(row >= top) {  //go bottom to top at right col
				System.out.print(matrix[row][col] + "  ");
				--row;
			}
			--right;  //eliminate right col
			row = top;
			col = right;
			
			while(col >= left) {  //go right to left at top row
				System.out.print(matrix[row][col] + "  ");
				--col;
			}
			++top;  //eliminate top row
			row = top;
			col = left;
			
			while(row <= bottom) {  //go top to bottom at left col
				System.out.print(matrix[row][col] + "  ");
				++row;
			}
			++left;  //eliminate left col
			row = bottom;
			col = left;
		}
		System.out.println();
	}
}
