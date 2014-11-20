package QBit;

import DBit.Bit;

public class ReverseNumber {
	public static void main(String[] args) {
		int a = 3;
		//System.out.println("clear bit 0: " + clearBit(0, a));
		//System.out.println("set bit 1: " + setBit(1, a));
		//System.out.println("isSet 0: " + isBitSet(0, a));
		//System.out.println("isSet 1: " + isBitSet(1, a));
		System.out.println(Bit.printBinary(a));
		System.out.println(reverseNumber(a));
		System.out.println(Bit.printBinary(reverseNumber(a)));
	}
	
	public static boolean isBitSet(int idx, int num) {
		return (num & (1 << idx)) > 0;  //only the bit being checked is left after masking so it should be >0 if it is set
	}
	
	public static int clearBit(int idx, int num) {
		return num & ~(1 << idx);
	}
	
	public static int setBit(int idx, int num) {
		return num | (1 << idx);
	}
	
	public static int reverseNumber(int n) {
		int left = 31, right = 0, num = n;
		boolean isSetLeft = false, isSetRight = false;
		while(left > right) {
			//System.out.println("\t" + Bit.printBinary(num));
			//System.out.println("\tleft: " + isSetLeft + "\tright: " + isSetRight);
			isSetLeft = isBitSet(left, num);
			isSetRight = isBitSet(right, num);
			
			if(isSetLeft && !isSetRight) {
				//System.out.println("left: " + left + "\tright: " + right + "\tswap(1,0)");
				num = setBit(right, num);
				num = clearBit(left, num);
			} else if(!isSetLeft && isSetRight) {
				//System.out.println("left: " + left + "\tright: " + right + "\tswap(0,1)");
				num = setBit(left, num);
				num = clearBit(right, num);
			} else {
				//System.out.println("left: " + left + "\tright: " + right + "\tsame");
			}
			//System.out.println();
			--left;
			++right;
		}
		return num;
	}
}
