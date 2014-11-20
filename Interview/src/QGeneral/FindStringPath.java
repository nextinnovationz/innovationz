package QGeneral;

public class FindStringPath {
	public static void main(String[] args) {
		char[][] matrix = {{'a','b','c','d'},{'e','f','g','h'},{'e','j','k','l'},{'m','n','o','p'}};
		char[] str = "efgkop".toCharArray();
		System.out.println("found path: " + findPath(matrix, str));
	}
	
	public static boolean findPath(char[][] matrix, char[] str) {
		int rowLen = matrix.length, colLen = matrix[0].length;
		int row = 0, col = 0;
		char first = str[0];
		boolean found = false;
		
		//same size as original matrix to keep track of which char is on current path so it won't match a char twice
		boolean[][] noBackward = new boolean[rowLen][colLen];
		
		while(row >= 0 && row < rowLen) {  //keep going until all rows are checked
			System.out.println("checking " + matrix[row][col] + " vs. " + first);
			
			//only start probing path after the 1st char has matched
			if(matrix[row][col] == first) {
				System.out.println("\tfound 1st char match, go explore...");
				found = explore(matrix, str, row, col, 1, noBackward);  //explore for remaining chars from this point
			}
			if(!found) { //check next col on same row
				++col;
				if(col == colLen) {  //reached end of current col, go to next row and restart
					col = 0;  //reset to 1st col of next row
					++row;  //go to next row
				}	
			}
			else  //found a path so done
				break;
		}
		
		return found;
	}
	
	public static boolean explore(char[][] matrix, char[] str, int row, int col, int pos, boolean[][] noBackward) {
		//avoid backward matching
		if(noBackward[row][col]) {
			System.out.println("\t\talready matched this char before, can't go backward matching same char again");
			return false;  //trying to match a char that was already matched before
		} else {  //haven't matched this char yet, mark it
			noBackward[row][col] = true;
		}
		
		//pos reached end of str so found a matching path
		if(pos == str.length) {
			System.out.println("\t\treached end of path");
			return true;
		}
		
		//current char to check
		char c = str[pos];
		System.out.println("\t*char: " + c);
		
		//keep track of which direction has a match if any
		boolean found = false;
		
		//check up matched, explore for next char from up
		if(row-1 >= 0 && matrix[row-1][col] == c) { 
			System.out.println("\tcheck up");
			found = explore(matrix, str, row-1, col, pos+1, noBackward);
		}
		
		//check down matched, explore for next char from down
		if(!found && row+1 < matrix.length && matrix[row+1][col] == c) {
			System.out.println("\tcheck down");
			found = explore(matrix, str, row+1, col, pos+1, noBackward);
		} 
		
		//check left matched, explore for next char from left
		if(!found && col-1 >= 0 && matrix[row][col-1] == c) {
			System.out.println("\tcheck left");
			found = explore(matrix, str, row, col-1, pos+1, noBackward);
		}
		
		//check right matched, explore for next char from right
		if(!found && col+1 < matrix[0].length && matrix[row][col+1] == c) {
			System.out.println("\tcheck right");
			found = explore(matrix, str, row, col+1, pos+1, noBackward);
		}
		
		//unmark current cell so that after return this cell is still available 
		//for backtrack purpose in case there is no path returned at this level
		noBackward[row][col] = false;
		
		//return if any path found a match
		return found;
	}
}
