package QBit;

public class SwapTwoNumbers {
	public static void main(String[] args) {
		int a = 9, b = 5;
		swapBySub(a, b);
		swapByXOR(a, b);
	}

	public static void swapBySub(Integer a, Integer b) {
		//a = 9, b = 5
		//a = 9-5=4
		//b = 5+4=9
		//a = 9-4=5
		a = a-b;
		b = b+a;
		a = b-a;
		System.out.println("a: " + a + "\tb: " + b);
	}
	
	public static void swapByXOR(Integer a, Integer b) {
		a = a^b;
		b = a^b;
		a = a^b;
		System.out.println("a: " + a + "\tb: " + b);
	}
}
