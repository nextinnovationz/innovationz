package QMath;

import java.util.ArrayList;

public class GeneratePrimeNumbers {
	public static void main(String[] args) {
		int n = 30;
		ArrayList<Integer> primes = generatePrimes(n);
		System.out.println("primes: " + primes.toString());
		System.out.println("primes: " + generatePrimesBruteForce(n).toString());
	}
	
	//O(n^2) by dividing a number by all numbers before it to see if it can be divided by numbers besides 1 and itself
	public static ArrayList<Integer> generatePrimesBruteForce(int n) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(2);
		boolean isPrime = true;
		int len = 0;
		
		//a prime number is only divisible by 1 and itself
		for(int curr = 2; curr <= n; ++curr) {
			len = list.size();
			//curr is divisible by element i (not 1 since it started at 2) 
			//curr itself is not in list yet
			//so this curr is divisible by more than 1 and itself (some other numbers in between that is already is list)
			for(int i = 0; i < len; ++i) {
				if(curr%list.get(i) == 0) { 
					isPrime = false;
					break;
				}
			}	
			if(isPrime)
				list.add(curr);
			isPrime = true;	
		}
		return list;
	}
	
	//Sieve algorithm O(nloglogn)
	public static ArrayList<Integer> generatePrimes(int n) {
		if(n < 0)
			return null;
		
		boolean[] nums = new boolean[n+1];  //keep track of 0 to n if they have been crossed out or not
		
		//0 and 1 are false since they are not prime
		//assume 2 to n are all primes so mark them as true for now
		int pos = 2;
		while(pos <= n) {
			nums[pos] = true;
			++pos;
		}
		
		pos = 2;
		boolean crossed = false;
		while(pos <= n) {  //repeat until pos==n or if nothing was crossed in the for loop
			if(nums[pos]) {  //only proceed to cross all indexes with pos as mul if nums[pos] was not crossed
				for(int mul = pos+pos; mul <= n; mul+=pos) {  //from pos+pos onward, check every +pos index
					if(nums[mul]) {
						nums[mul] = false;
						crossed = true;
					} 
				}
			}
			
			if(!crossed)  //nothing was crossed since they were all already crossed before, done 
				break;
			
			++pos;
		}
		
		//all remaining not crossed out numbers in list are primes from 2 to n
		pos = 2;
		ArrayList<Integer> primes = new ArrayList<Integer>();
		while(pos <= n) {
			if(nums[pos]) 
				primes.add(pos);
			++pos;
		}
		
		return primes;
	}
}
