package QArray;

public class Rotate {
	public static void main(String[] args) {
		int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
		
		//90 deg
		rotateRight(matrix);
		printMatrix(matrix);
		
		//180 deg
		rotateRight(matrix);
		printMatrix(matrix);
		
		//270 deg
		rotateRight(matrix);
		printMatrix(matrix);
		
		//360 deg (back to original)
		rotateRight(matrix);
		printMatrix(matrix);
	}
	
	public static void printMatrix(int[][] matrix) {
		int rowLen = matrix.length;
		int colLen = matrix[0].length;
		for(int row = 0; row < rowLen; ++row) {
			for(int col = 0; col < colLen; ++col) {
				System.out.print(matrix[row][col] + "  ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void rotateRight(int[][] matrix) {
		//there are floor(m/2) rings for mxm matrix
		int totalRings = matrix.length/2;
		
		//use colLen or rowLen for lower right (x,y) to define bound for ring
		int colLen = matrix[0].length-1;
		
		for(int ring = 0; ring < totalRings; ++ring) {
			//upper left (x,y) and lower right (x,y) defines the ring
			rotateRing(matrix, ring, ring, colLen-ring, colLen-ring);
		}
	}
	
	public static void rotateRing(int[][] matrix, int ULx, int ULy, int LRx, int LRy) {
		int URx = LRx, URy = ULy;
		int LLx = ULx, LLy = LRy;
		int colLen = LRx;
		int rowLen = LRy;
		
		while(ULx < colLen && URy < rowLen) {
			//save UL corner
			int temp = matrix[ULy][ULx];
			
			//move LL to UL
			matrix[ULy][ULx] = matrix[LLy][LLx];
			
			//move LR to LL
			matrix[LLy][LLx] = matrix[LRy][LRx];
			
			//move UR to LR
			matrix[LRy][LRx] = matrix[URy][URx];
			
			//move UL (temp) to UR
			matrix[URy][URx] = temp;
			
			//move ULx right by 1
			++ULx;
			
			//move URy down by 1
			++URy;
			
			//move LRx left by 1
			--LRx;
			
			//move LLy up by 1
			--LLy;
		}
	}
}
