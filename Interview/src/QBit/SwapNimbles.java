package QBit;

import DBit.Bit;

public class SwapNimbles {
	public static void main(String[] args) {
		int a = 1078;
		System.out.println("original: " + Bit.printBinary(a));
		System.out.println("swapped: " + Bit.printBinary(swapNibbles(a, 0, 1)));
	}
	
	public static int swapNibbles(int num, int part1, int part2) {
		//an int is 32bit
		//leftmost is msb (index 31), rightmost is lsb (index 0)
		//part1 (right nibble) and part2 (left nibble) are the nibbles to swap in the int
		
		//get each nibble from the num
		//0xF is 1111 (nibble all set)
		//left shift 0xF and & it with the num so that only the nibble remains 
		int nib1 = num & (0xF << (part1*4));
		int nib2 = num & (0xF << (part2*4));
		
		//shift nib1 to nib2's pos and vice verse (to swap)
		nib1 <<= ((part2-part1)*4);
		nib2 >>>= ((part2-part1)*4);
		
		System.out.println("\tnib1: " + Bit.printBinary(nib1));
		System.out.println("\tnib2: " + Bit.printBinary(nib2));
		
		//clear each nibble in the same num
		//~(0xF << nimble position) will clear the nimble in num
		num &= ~(0xF << (part1*4));
		num &= ~(0xF << (part2*4));
		
		System.out.println("\tcleared nibs: " + Bit.printBinary(num));
		
		//put the swapped nibbles back into num using | 
		num |= nib1;
		num |= nib2;
		
		return num;
	}
}
