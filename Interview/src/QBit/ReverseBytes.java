package QBit;

import DBit.Bit;

public class ReverseBytes {
	public static void main(String[] args) {
		int num = Bit.convertBinaryToInteger("10101010001100110101010111101110", 31);
		printBytesSeparately(Bit.printBinary(num));
		printBytesSeparately(Bit.printBinary(reverseByte(num)));
	}
	
	public static void printBytesSeparately(String bin) {
		int start = 0;
		for(int i = 8; i < bin.length(); i+=8) {
			System.out.print(bin.substring(start,i) + "  ");
			start = i;
		}
		System.out.print(bin.substring(start,32));
		System.out.println();
	}
	
	public static int reverseByte(int num) {
		//get each individual bytes
		int byte0 = num & createMask(0);
		int byte1 = num & createMask(1);
		int byte2 = num & createMask(2);
		int byte3 = num & createMask(3);

		/*
		System.out.println("bytes before");
		System.out.println(Bit.printBinary(byte0));
		System.out.println(Bit.printBinary(byte1));
		System.out.println(Bit.printBinary(byte2));
		System.out.println(Bit.printBinary(byte3));
		*/
		
		//reverse byte order by swapping start and end
		//left/right shift by bigger (byte number*8)
		byte0 <<= 3*8;
		byte3 >>>= 3*8;
		byte1 <<= 8;
		byte2 >>>= 8;
		
		/*
		System.out.println("\nbytes after");
		System.out.println(Bit.printBinary(byte0));
		System.out.println(Bit.printBinary(byte1));
		System.out.println(Bit.printBinary(byte2));
		System.out.println(Bit.printBinary(byte3));
		*/
		
		return byte0 | byte1 | byte2 | byte3;
	}
	
	public static int createMask(int byteNum) {
		int mask = ~0;  //create 32bits of 1s
		mask >>>= 24;  //clear right 24bits to put 8bits of 1s to the right
		mask <<= (byteNum * 8);  //move 8bits of 1s to the correct byteNum position
		//System.out.println("mask: " + Bit.printBinary(mask));
		return mask;
	}
}
