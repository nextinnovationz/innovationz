package QMath;

public class OperationsWithOnlyAdd {
	public static void main(String[] args) {
		int a = -9;
		int b = 0;
		System.out.println("sub: " + subtract(a, b));
		System.out.println("mul: " + multiply(a, b));
		System.out.println("div: " + divide(a, b));
	}
	
	public static int negate(int num) {
		int one = 0, flip = 0;
		if(num < 0) { //neg to pos
			one = 1;
		}
		else if(num > 0) { //pos to neg
			one = -1;
		}
		else {
			return 0;
		}
		
		while(num != 0) {  //reach 0 and keep count at each iteration
			flip += one;  //keep adding -1 or +1 until negation value
			num += one;  //neg num will add 1 until 0, pos num will add -1 until 0
		}
		
		return flip;
	}
	
	public static int absolute(int num) {
		if(num == 0)
			return 0;
		if(num > 0)
			return num;
		
		int abs = 0;
		while(num < 0) {  //keep count while adding 1 to neg num until it reach 0
			++abs;
			++num;
		}
		
		return abs;
	}
	
	public static int subtract(int a, int b) {
		return a + negate(b);  //a + (-b)
	}
	
	public static int multiply(int a, int b) {
		if(a == 0 || b == 0)
			return 0;
	
		int numA = a, numB = b;
		
		//only multiply using positives 
		if(a < 0) 
			numA = absolute(a);
		if(b < 0)
			numB = absolute(b);
		
		int i = 0;
		while(i < numB) {  //add b to itself a times 
			numA += numB;
			++i;
		}
		
		//a*b=c or -a*-b=c
		if((a > 0 && b > 0) || (a < 0 && b < 0))
			return numA;

		//-a*b=-c or a*-b=-c
		return negate(numA);
	}
	
	public static int divide(int a, int b) {
		if(b == 0)
			throw new ArithmeticException();  //divide by 0 is undefined
		if(a == 0)  //numerator is 0 so whole fraction is 0
			return 0;
		
		int numA = a, numB = b;
		
		//only multiply using positives 
		if(a < 0) 
			numA = absolute(a);
		if(b < 0)
			numB = absolute(b);
		
		int mul = 0, c = 0;
		while(mul != numA) {  //a/b=c is same as a=bc
			mul += numB;  //try adding b to itself each time and check it against a
			++c;  //use a counter to find c
		}
		
		if((a > 0 && b > 0) || (a < 0 && b < 0))
			return c;
		
		return negate(c);
	}
}
