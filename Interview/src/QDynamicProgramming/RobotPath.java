package QDynamicProgramming;

import java.util.Stack;

public class RobotPath {
	public static void main(String[] args) {
		/*
		 * OXOXOO
		 * OOXOOX
		 * XOOOXO
		 * OOOXOO
		 * XXOOOO
		 */
		
		String pattern = "OXOXOOOOXOOXXOOOXOOOOXOOXXOOOO";
		Cell[][] map = generateMap(pattern, 5, 6);
		printMap(map);
		
		//System.out.println("rowLen: " + map.length + "\tcolLen: " + map[0].length);
		//System.out.println("rowLen: " + map[4][5].y + "\tcolLen: " + map[4][5].x);
		Cell[] path = findPath(map, map[0][0], map[4][5]);
		//printPath(path);
	}
	
	private static class Cell {
		private int x, y;
		private boolean isFree;
		
		private Cell(int x, int y, boolean isFree) {
			this.x = x;
			this.y = y;
			this.isFree = isFree;
		}
	}
	
	public static void printPath(Cell[] path) {
		if(path == null || path.length == 0)
			return;
		
		for(Cell c: path) {
			System.out.println("(" + c.x + "," + c.y + ")");
		}
		System.out.println();
	}
	
	public static void printMap(Cell[][] map) {
		int rowLen = map.length, colLen = map[0].length, row = 0, col = 0;
		while(row < rowLen) {
			if(map[row][col].isFree)
				System.out.print("O ");
			else
				System.out.print("X ");
			++col;
			if(col == colLen) {
				col = 0;
				++row;
				System.out.println();
			}
		}
		System.out.println("\n");
	}
	
	public static Cell[][] generateMap(String pattern, int rowLen, int colLen) {
		Cell[][] map = new Cell[rowLen][colLen];
		int pos = 0, len = pattern.length(), row = 0, col = 0;
		char c;
		
		while(pos < len) {
			c = pattern.charAt(pos);
			if(c == 'X') {
				map[row][col] = new Cell(col, row, false);
			} else if(c == 'O'){
				map[row][col] = new Cell(col, row, true);
			} else {
				return null;
			}
			
			++col;
			if(col == colLen) {
				col = 0;
				++row;
			}
			
			++pos;
		}
		
		return map;
	}
	
	public static Cell[] findPath(Cell[][] map, Cell start, Cell end) {
		if(map == null || start == null || end == null)
			return null;
		
		Cell[] path = new Cell[end.x + end.y];
		//path[0] = start;
		
		boolean[][] visited = new boolean[end.y][end.x];
		visited[start.y][start.x] = true;
		
		Stack<Cell> backtrack = new Stack<Cell>();
		//backtrack.push(start);
		
		int rowLen = end.x, colLen = end.y, pathLen = 1;
		Cell curr = start;
		
		do{  //trying an iterative version of backtracking
			System.out.println("looping");
			visited[curr.y][curr.x] = true;
			
			if(curr.x == end.x && curr.y == end.y) {  //reached end cell so found path
				System.out.println("\tfound a path");
				path[pathLen] = curr;
				++pathLen;
				return path;
			}
			
			if(!curr.isFree || curr.x+1 == colLen || curr.y+1 == rowLen || pathLen == end.x+end.y){  //reached block so need to backup a cell and removed last added cell from path
				if(backtrack.isEmpty()) {  //nothing else to backtrack to, so no path exist
					System.out.println("\tno more cell for backtrack so not path exist");
					break;
				} 
				
				System.out.println("\tcurr cell is not free, need to backtrack");
				
				//go back to previous cell that led to this current one
				curr = backtrack.pop();
				--pathLen;
				path[pathLen] = null; 
			}

			System.out.println("********\ncurr cell: (" + curr.x + "," + curr.y + ")");
			
			if(curr.x-1 >= 0 && !visited[curr.y][curr.x-1] && map[curr.y][curr.x-1].isFree) {
				System.out.println("checking left");
				backtrack.push(curr);
				path[pathLen] = curr;
				++pathLen;
				visited[curr.y][curr.x] = true;
				curr = map[curr.y][curr.x-1];
				continue;
			} 
			
			if(curr.x+1 < colLen && !visited[curr.y][curr.x+1] && map[curr.y][curr.x+1].isFree) {
				System.out.println("checking right");
				backtrack.push(curr);
				path[pathLen] = curr;
				++pathLen;
				visited[curr.y][curr.x] = true;
				curr = map[curr.y][curr.x+1];
				continue;
			} 
			
			if(curr.y-1 >= 0 && !visited[curr.y-1][curr.x] && map[curr.y-1][curr.x].isFree) {
				System.out.println("checking up");
				backtrack.push(curr);
				path[pathLen] = curr;
				++pathLen;
				visited[curr.y][curr.x] = true;
				curr = map[curr.y-1][curr.x];
				continue;
			}
			
			if(curr.y+1 < rowLen && !visited[curr.y+1][curr.x] && map[curr.y+1][curr.x].isFree) {
				System.out.println("checking down");
				backtrack.push(curr);
				path[pathLen] = curr;
				++pathLen;
				visited[curr.y][curr.x] = true;
				curr = map[curr.y+1][curr.x];
				continue;
			}
		} while(!backtrack.isEmpty());
		
		return null;
	}
}
