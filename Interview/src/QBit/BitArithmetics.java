package QBit;

import DBit.Bit;

public class BitArithmetics {
	public static void main(String[] args) {
		int a = 9, b = 19;
		System.out.println(add(a,b));
	}
	
	public static int add(int a, int b) {
		int sum = 0, carry = 0;
		int apos = 0, bpos = 0, abit, bbit;
		
		while(apos < 32 && bpos < 32) {
			carry <<= apos;
			abit = (1 << apos) & a;
			bbit = (1 << bpos) & b;
			sum |= carry ^ abit ^ bbit;
			
			//System.out.println("a;" + Bit.printBinary(abit) + "\nb: " + Bit.printBinary(bbit) + "\ncarry: " + Bit.printBinary(carry));
			//System.out.println("sum: " + Bit.printBinary(sum));
			//System.out.println();
			
			//& any two bits of 1's will produce a carry for next bit addition
			if((carry&abit) > 0 || (carry&bbit) > 0 || (abit&bbit) > 0)
				carry = 1;
			else
				carry = 0;
			
			++apos;
			++bpos;
		}
		
		return sum;
	}
}
