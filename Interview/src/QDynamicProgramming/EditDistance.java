package QDynamicProgramming;

public class EditDistance {
	public static void main(String[] args) {
		String a = "BDCABA";
		String b = "ABCBDAB";
		System.out.println("Edit distance (LCS length): " + editDistance(a,b));
	}
	
	public static int editDistance(String a, String b) {
		int alen = a.length(), blen = b.length();
		
		//solution matrix need to have a row and col for initialization
		//so size is (alen+1 x blen+1)
		//thus final solution LCS length is solution[alen][blen] since its (0,0) indexed
		int[][] solution = new int[alen+1][blen+1];
		
		//initialize 1st row to all zeros
		for(int i = 0; i <= blen; ++i) {
			solution[0][i] = 0;
		}
		
		//initialize 1st col to all zeros
		for(int j = 0; j <= alen; ++j) {
			solution[j][0] = 0;
		}
		
		//start dynamic programming solution from (1,1) to (alen,blen)
		for(int i = 1; i <= alen; ++i) {
			for(int j = 1; j <= blen; ++j) {
				if(a.charAt(i-1) == b.charAt(j-1)) {  //char match
					//current cell is (up left diagonal by 1)+1
					solution[i][j] = solution[i-1][j-1]+1;
				} else {  //char don't match
					//current cell is large of (1 left, 1 up)
					solution[i][j] = Math.max(solution[i][j-1], solution[i-1][j]);
				}
			}
		}
		
		int lcsLen = solution[alen][blen];
		char[] lcs = new char[lcsLen];
		int idx = lcsLen-1;
		int sy = alen, sx = blen;
		
		//start at bottom right corner and go up to (1,1)
		//similar to reversing the process of building the solution matrix
		//match is up left 1 diagnoal
		//mismatch is the larger of 1 left or 1 up
		while(sy > 0 && sx > 0) {
			if(a.charAt(sy-1) == b.charAt(sx-1)) {  //char match 
				lcs[idx] = a.charAt(sy-1);
				--idx;
				
				//go up left diagonal by 1
				--sy;
				--sx;
			} else {  //char don't match
				//take the larger of the two (1 left or 1 up)
				if(solution[sy][sx-1] > solution[sy-1][sx]) {
					--sx;
				} else {
					--sy;
				}
			}
		}
		
		System.out.println("Longest Common Subsequence (not contiguous): " + new String(lcs) + "\n");
		printSolutionMatrix(solution);
		return lcsLen;
	}
	
	public static void printSolutionMatrix(int[][] matrix) {
		for(int i = 0; i < matrix.length; ++i) {
			for(int j = 0; j < matrix[0].length; ++j) {
				System.out.print(matrix[i][j] + "  ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
