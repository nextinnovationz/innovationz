package QGeneral;

import java.util.ArrayList;

public class FindAllStringPaths {
	public static void main(String[] args) {
		char start = 'a', end = 'i';
		char[][] matrix = {{'a','b','c'}, {'d','e','f'}, {'g','h','i'}};
		System.out.println("total paths: " + findAllPaths(matrix, start, end));
	}
	
	public static int findAllPaths(char[][] matrix, char start, char end) {
		//find all paths in matrix that leads start to finish
		//first need to find start in matrix before building path to end
		
		ArrayList<Character> path = new ArrayList<Character>();
		int rowLen = matrix.length, colLen = matrix[0].length, cnt = 0;
		boolean[][] visited = new boolean[rowLen][colLen];
		
		for(int row = 0; row < rowLen; ++row) {
			for(int col = 0; col < colLen; ++col) {
				//found a starting point, pass it to helper and let it start exploring from helper
				if(matrix[row][col] == start) {
					cnt += findAllPaths(matrix, row, col, end, path, visited);
				}
			}
		}
		
		return cnt;
	}
	
	public static int findAllPaths(char[][] matrix, int row, int col, char end, ArrayList<Character> path, boolean[][] visited) {
		//already visited this cell, don't repeat
		if(visited[row][col]) {
			return 0;
		}
		
		//add current char to path
		path.add(matrix[row][col]);
		
		int cnt = 0;
		
		//current char is end char so print path
		if(matrix[row][col] == end) {
			System.out.println(path.toString());
			path.remove(path.size()-1);  //remove this last char from path
			return 1;
		}
		
		//only set visited flag for all nonend cells 
		//so multiple paths can use this last cell
		visited[row][col] = true;
		
		//explore left cell
		if(row-1 > 0) {
			cnt += findAllPaths(matrix, row-1, col, end, path, visited);
		}
		
		//explore right cell
		if(row+1 < matrix.length) {
			cnt += findAllPaths(matrix, row+1, col, end, path, visited);
		}
		
		//explore top cell
		if(col-1 > 0) {
			cnt += findAllPaths(matrix, row, col-1, end, path, visited);
		}
		
		//explore bottom cell
		if(col+1 < matrix[0].length) {
			cnt += findAllPaths(matrix, row, col+1, end, path, visited);
		}
		
		//remove current char
		path.remove(path.size()-1);
		
		//unset the visited flag
		visited[row][col] = false;
		
		//total number of paths from all recursive calls from this level
		return cnt;
	}
}
