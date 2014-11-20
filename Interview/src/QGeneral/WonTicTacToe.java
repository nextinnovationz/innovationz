package QGeneral;

public class WonTicTacToe {
	public static void main(String[] args) {
		
	}
	
	public static boolean hasWon(char[][] board) {
		int rowLen = board.length, colLen = board[0].length;
		boolean won = true;
		
		//check all rows (horizontal win)
		for(int row = 0; row < rowLen; ++row) {
			for(int col = 1; col < colLen; ++col) {
				if(board[row][col] == 0 || board[row][col] == board[row][col-1]) {
					won = false; 
					break;
				}
			}
		}
		
		if(won)
			return true;
		else
			won = true;
		
		//check all cols (vertical win)
		for(int row = 1; row < rowLen; ++row) {
			for(int col = 0; col < colLen; ++col) {
				if(board[row][col] == 0 || board[row][col] == board[row-1][col]) {
					won = false; 
					break;
				}
			}
		}
		
		if(won)
			return true;
		else
			won = true;
		
		//check upper left to lower right diagonal
		int row = 1, col = 1;
		while(row < rowLen && col < colLen) {
			if(board[row][col] == 0 || board[row][col] == board[row-1][col-1]) {
				won = false; 
				break;
			}
			
			//move down right by 1
			++row;
			++col;
		}
		
		if(won)
			return true;
		else
			won = true;
		
		//check upper right to lower left diagonal
		row = 1;
		col = colLen-2;
		while(row >= 0 && col >= 0) {
			if(board[row][col] == 0 || board[row][col] == board[row-1][col+1]) {
				won = false; 
				break;
			}
			
			//move down left by 1
			++row;
			--col;
		}
		
		return won;
	}
}
