package QArray;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomSizeNSets {
	public static void main(String[] args) {
		int[] arr = {3,2,3,4,5,7,6,0,2,4};
		
		for(int i = 0; i < 10; ++i) {
			System.out.println(Arrays.toString(arr));
			System.out.println(generate(arr, 4).toString());
		}
	}
	
	public static Set<Integer> generate(int[] arr, int n) {
		int[] clone = arr.clone();
		
		Set<Integer> s = new HashSet<Integer>();
		int len = clone.length;
		int curr = 0;
		Random rand = new Random();
		
		while(curr < len) {
			//from [curr, (len-curr)), pick a random index
			//len-curr is the number of elements to the right of curr, excluding curr itself
			//since nextInt(len-curr) is exclusive, curr+nextInt will not be out of bound
			//swap element in curr and random index to mark the random element as done
			//it will not be considered for selection again 
			//so each element has equal probability of being selected
			//move curr right by 1 exclude the random element (now in swapped index) for future consideration
			
			swap(clone, curr, curr+rand.nextInt(len-curr));
			s.add(clone[curr]);
			++curr;
		}
		
		return s;
	}
	
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
 	}
}
