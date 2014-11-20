package QGeneral;

public class CountReachableCell {
	public static void main(String[] args) {
		System.out.println("count: " + countCells(3, 4, 4));
	}

	public static int countCells(int limit, int rowLen, int colLen) {
		boolean[] visited = new boolean[rowLen*colLen];
		return countCellHelper(limit, 0, 0, rowLen, colLen, visited);
	}
	
	public static int countCellHelper(int limit, int row, int col, int rowLen, int colLen, boolean[] visited) {
		if(row < 0 || row >= rowLen || col < 0 || col >= colLen) {  //out of bound so stop going any further
			System.out.println("not counting: (" + row + "," + col + ") - out of bound");
			return 0;
		}
		
		if(visited[row*rowLen+col]) {  //already visited this cell, don't recount
			System.out.println("not counting: (" + row + "," + col + ") - already visited");
			return 0;
		} else {  //mark the cell for 1st time visited so it won't get recounted anywhere
			visited[row*rowLen+col] = true;
		}
		
		int idxSum = digitSum(row) + digitSum(col);
		if(limit < idxSum) {  //sum is beyond limit so don't go any further
			System.out.println("counting: (" + row + "," + col + ") - sum beyond limit");
			return 0;
		} else {  //just printing the cell being counted
			System.out.println("\tcounting: (" + row + "," + col + ")");
		}
		
		//in bound and sum is within limit so try 4 directions
		//add 1 for this current level when returning upward for count
		return countCellHelper(limit, row-1, col, rowLen, colLen, visited) + 
				countCellHelper(limit, row+1, col, rowLen, colLen, visited) + 
				countCellHelper(limit, row, col-1, rowLen, colLen, visited) + 
				countCellHelper(limit, row, col+1, rowLen, colLen, visited) + 1;
	}
	
	public static int digitSum(int num) {
		int sum = 0;
		while(num > 0) {
			sum += num%10;  //add rightmost digit to sum each time
			num /= 10;  //remove rightmost digit from sum to reach 0 for termination
		}
		return sum;
	}
}
