package QBit;

public class BitDifferences {
	public static void main(String[] args) {
		int a = 13;
		int b = 8;
		System.out.println("diff: " + difference(a, b));
	}
	
	public static int difference(int a, int b) {
		int xor = a ^ b;  //result has all difference bits set
		int ones = 0;  //just count the number of set bits to know differences
		while(xor > 0) {
			xor &= (xor-1);
			++ones;
		}
		return ones;
	}
}
