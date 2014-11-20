package QBit;

import DBit.Bit;

public class InsertBits {
	public static void main(String[] args) {
		int a = Bit.convertBinaryToInteger("11101011011", 10);
		System.out.println("a: " + a);
		
		int b = Bit.convertBinaryToInteger("110111011101010", 14);
		System.out.println("b: " + b);
		
		System.out.println("int result: " + insertBits(a, b, 2, 6));
	}
	
	public static int insertBits(int numA, int numB, int i, int j) {
		//insert numA [i,j] into numB [i,j]
		
		//get numA[i,j]
		//~0 generates 32bits of 1
		//clear all (len-j-1) 1's to the left of j by leftshifting (len-j-1) putting (len-j-1) to the right of it
		//need to rightshift the 1's back to its original positon so rightshift (len-j-1) to add (len-j-i) 0s to the left
		//clear all (i) 1's to the right of i by rightshifting by (i) to clear those 1's
		//put i 0s back on the right of i by leftshifting by i 
		//the final mask is AND with the number to only keep bits [i,j] and clear everything else
		int maskTakeA = (~0) << (32-j-1);
		maskTakeA >>>= ((32-j-1)+i);
		maskTakeA <<= i;
		numA &= maskTakeA;
		System.out.println("\tmask to take numA[i,j]: " + Bit.printBinary(maskTakeA));
		
		//clear numB[i,j]
		//use same mask as before, but negate all bits so it keeps all bits in number except those in [i,j]
		int maskClearB = ~maskTakeA;
		numB &= maskClearB;
		System.out.println("\tmask to clear numB[i,j]: " + Bit.printBinary(maskClearB));
		
		System.out.println("\ttook numA: " + Bit.printBinary(numA));
		System.out.println("\tcleared numB: " + Bit.printBinary(numB));
		
		//inserting numA[i,j] into numB[i,j] by OR
		numB |= numA;
		System.out.println("\tinserted: " + Bit.printBinary(numB));
		return numB;
	}
}
