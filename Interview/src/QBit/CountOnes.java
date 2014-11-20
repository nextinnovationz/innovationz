package QBit;

public class CountOnes {
	public static void main(String[] args) {
		//countFromRight(43);
		//countFromLeft(7);
		countBySetBits(8);
	}
	
	public static int countFromRight(int n) {
		int num = n;
		int count = 0;
		int mask = 1;
		boolean flag = true;
		
		while(flag) {  //ends when flag reach 32bit integer
			System.out.println("mask: " + Integer.toBinaryString(mask) + "\tlength: " + Integer.toBinaryString(mask).length());
			
			if((num & mask) > 0) 
				++count;
			
			System.out.println("\t&: " + Integer.toBinaryString(num & mask));
			System.out.println("\tcount: " + count);
			
			mask <<= 1;  //shift mask left to check next digit
			
			if(mask == 0)
				flag = false;
		}
		
		System.out.println("count: " + count);
		return count;
	}
	
	public static int countFromLeft(int n) {
		int count = 0;
		int mask = 1;
		
		while(n > 0) {
			if((n & mask) > 0)
				++count;
			n >>>= 1;  //shift num right to remove rightmost digit for next check
		}
		
		System.out.println("count: " + count);
		return count;
	}
	
	public static int countBySetBits(int n) {
		int count = 0;
		while(n > 0) {
			n = n & (n-1);  //clear rightmost set bit and flip all bits left of it, then clear rightmost bit in original
			++count;
		}
		System.out.println("count: " + count);
		return count;
	}
}
