package QGeneral;

public class CountColony {
	public static void main(String[] args) {
		/*
		 * 0110
		 * 1001
		 * 1110
		 * 1001
		 * 0011
		 * 0010
		 * 1100
		 * 0010
		 */
		try {
			boolean[][] map = generateMap("01101001111010010011001011000010", 8, 4);
			printMap(map);
			System.out.println("colony counts: " + countColony(map));
		} catch(Exception e) {
			
		}
	}
	
	public static boolean[][] generateMap(String pattern, int rowLen, int colLen) throws Exception {
		boolean[][] map = new boolean[rowLen][colLen];
		int pos = 0, len = pattern.length(), row = 0, col = 0;
		char c;
		while(pos < len) {
			c = pattern.charAt(pos);
			if(c == '1') {
				//System.out.print("1  ");
				map[row][col++] = true;
			} else if(c == '0') {
				//System.out.print("0  ");
				map[row][col++] = false;
			} else {
				throw new Exception("bad pattern");
			}
			
			if(col == colLen) {
				col = 0;
				++row;
				//System.out.println();
				if(row == rowLen && pos+1 < len) {
					throw new Exception("pattern size don't match map dimensions");
				}
			} 
			++pos;
		}
		return map;
	}
	
	public static void printMap(boolean[][] map) {
		int rowLen = map[0].length, colLen = map.length;
		//System.out.println("rowLen: " + rowLen + "\tcolLen: " + colLen);
		for(int row = 0; row < colLen; ++row) {
			for(int col = 0; col < rowLen; ++col) {
				if(map[row][col])
					System.out.print("1  ");
				else
					System.out.print("0  ");
			}
			System.out.println();
		}
		System.out.println("\n");
	}
	
	public static int countColony(boolean[][] map) {
		if(map == null || map.length == 0)
			return 0;
		
		int count = 0, row = 0, col = 0, rowLen = map[0].length, colLen = map.length;
		while(row < colLen) {
			//System.out.println("(" + row + "," + col + "): " + map[row][col]);
			if(map[row][col]) {  //this cell can be visited, go and expand from here
				System.out.println("colony size: " + countColony(map, row, col));  //go and erase all cells in this one connected colony
				++count;  //increment colony count
				printMap(map);
			}
			++col;
			if(col == rowLen) {
				col = 0;
				++row;
			} 
		}

		return count;
	}
	
	public static int countColony(boolean[][] map, int row, int col) {
		int rowLen = map[0].length, colLen = map.length;
		
		//check if current cell is within bound for visit
		if(row < 0 || row >= colLen || col < 0 || col >= rowLen) {
			return 0;
		}
		
		if(!map[row][col]) {  //already visited this cell so done
			return 0;
		} else {  //cell has not been visited, visit now and clear it
			map[row][col] = false;  
		}
		
		//expand in four directions from this cell to erase any connected cells to this colony
		return 1 +
			countColony(map, row-1, col) +  
			countColony(map, row+1, col) +
			countColony(map, row, col-1) +
			countColony(map, row, col+1);
	}
}
