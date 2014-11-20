package QBit;

import DBit.Bit;

public class SwapEvenOddBits {
	public static void main(String[] args) {
		int num = 6;
		System.out.println("6: " + Bit.printBinary(num));
		System.out.println("-6: " + Bit.printBinary(-num));
		
		int num2 = 13456;
		System.out.println("13456: " + Bit.printBinary(num2));
		System.out.println("13456: " + Bit.printBinary(swapEvenOdd(num2)));
		System.out.println("swapped: " + isSwapEvenOdd(num2, swapEvenOdd(num2)));
	}
	
	public static int swapEvenOdd(int num) {
		//1010 1010 1010 1010 1010 1010 1010 1010 = 0xAAAAAAAA
		int even = num & 0xAAAAAAAA;  //get all even bits
		
		//0101 0101 0101 0101 0101 0101 0101 0101 = 0x55555555
		int odd = num & 0x55555555;  //get all odd bits
		
		//move even bits right by 1 to make them all odd bits
		even >>>= 1;
		
		//move odd bits left by 1 to make them all even bits
		odd <<= 1;
		
		//even | odd will combine so even becomes odd and odd becomes even
		return even | odd;
	}
	
	public static boolean isSwapEvenOdd(int numA, int numB) {
		//numA is original, numB is swapped
		int evenA = numA & 0xAAAAAAAA;  
		int evenB = numB & 0xAAAAAAAA;
		
		int oddA = numA & 0x55555555; 
		int oddB = numB & 0x55555555; 
		
		//just reverse the process of shifting after obtaining all even and all odd of numB
		return (oddB << 1) == evenA && (evenB >> 1) == oddA;
	}
}
