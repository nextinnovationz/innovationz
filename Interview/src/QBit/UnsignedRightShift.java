package QBit;

public class UnsignedRightShift {
	public static void main(String[] args) {
		int a = 13;  //1101 -> 0110
		System.out.println("shift: " + unsignedRightShift(a,1));
		
		int b = -13; 
		System.out.println("shift: " + unsignedRightShift(b, 1));
	}
	
	public static int unsignedRightShift(int num, int shift) {
		if(num < 0) {  //num is neg (right shift will pad right with 1's not 0's)
			//make it positive by (flip all bits and subtract 1)
			num = (~num)-1;
			num >>= shift;
			num = (~num)+1;
			return num;
		} else {  //num is pos so just right shift
			return num >> shift;
		}
	}
}
