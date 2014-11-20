package QMath;

public class VerifyPrimeNumber {
	public static void main(String[] args) {
		int a = 13;
		System.out.println("isPrime: " + isPrime(a));
	}
	
	public static boolean isPrime(int n) {
		if(n < 2 || n % 2 == 0)  //anything less than 2 and even numbers are not prime
			return false;
		
		//no need to check 3 to n for prime, just up to ceil(sqrt(n))
		//ex: n = 100
		//100x1=100, 50x2=100, 25x4=100, 20x5=100, 10x10=100, 2x50=100, 5x20=100, 
		int sqrt = (int)Math.ceil(Math.sqrt(n));  
		for(int i = 3; i <= sqrt; ++i) {
			if(n % i == 0)
				return false;					
		}
		
		return true;
	}
}
