package QGeneral;

public class EightQueens {
	public static void main(String[] args) {
		System.out.println("count: " + placeQueens());
	}
	
	public static int placeQueens() {
		//index = row, board[index] = col
		//queens must be placed on different row and col
		//just need to check diagonals
		//for each row, try placing a queen in different columns to avoid any conflict using recursive
		//use backtracking to retry at each col if a path failed to produce a good placement
		
		int[] board = new int[8];
		for(int i = 0; i < 8; ++i) {
			board[i] = i;
		}
		
		return placeQueens(board, 0);
	}
	
	public static int placeQueens(int[] board, int pos) {
		if(pos == 8) {  //just placed the last queen so its on last row (last index+1 of board)
			if(checkQueens(board)) {  //check the diagonal for each queen to see if any queens attack
				System.out.println("found a good placement");
				printQueens(board);
				return 1;
			} else {  //one or more queens attack on diagonal so not a good placement, backtrack each level to retry
				return 0;
			} 
		}
		
		int cnt = 0;  //used to accumulate all recursive calls made on this recursion level before returning the sum up
		
		//set pos as base row, swap it with everything else to try placing each remaining (right) on different columns 
		//always restore the swapping after the recursive call returns so that the next element can try
		for(int col = pos; col < 8; ++col) {  
			int temp = board[col];
			board[col] = board[pos];
			board[pos] = temp;
			//System.out.println("\t***placing queen for col" + col);
			cnt += placeQueens(board, pos+1);
			board[pos] = board[col];
			board[col] = temp;
		}
		return cnt;
	}
	
	public static boolean checkQueens(int[] board) {
		for(int i = 0; i < 8; ++i) {  //for each row's queen
			for(int j = i+1; j < 8; ++j) {  //check both diagonals from that queen 
				//i-j checks next diagonal lower right \
				//j-i checks next diagonal lower left /
				if(board[i]-board[j] == i-j || board[i]-board[j] == j-i) {
					//System.out.println("\tattacking: (" + board[i] + "," + board[j] + ")");
					return false;
				} else {
					//System.out.println("\tno attacking: (" + board[i] + "," + board[j] + ")");
				}
			}
		}
		return true;
	}
	
	public static void printQueens(int[] board) {
		for(int i = 0; i < 8; ++i) {
			for(int j = 0; j < 8; ++j) {
				if(board[i] == j) 
					System.out.print("Q  ");
				else 
					System.out.print("_  ");
			}
			System.out.println();
		}
		System.out.println("\n\n");
	}
}
