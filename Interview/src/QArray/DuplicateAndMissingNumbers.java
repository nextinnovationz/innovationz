package QArray;

public class DuplicateAndMissingNumbers {
	public static void main(String[] args) {
		int[] arr = {2,3,2,1,5};
		System.out.println(findDuplicates(arr));
		System.out.println(findMissing(arr));
	}
	
	//index 0 to n
	//arr[index] 1 to n+1
	//0 1 2 3 4
	//1 2 3 4 5 = 15
	//arr[i] should be at index i-1
	//example
	//2 3 2 1 5 
	//1 2 2 3 5 = 13
	//dup = 2 and missing = 15-(13-2) = 4
	public static int findDuplicates(int[] arr) {
		int i = 0;
		while(i < arr.length) {
			//System.out.println("checking (" + i + "," + arr[i] + ")");
			if(arr[i]-1 == i) {
				//System.out.println("\tmatch so keep going");
				++i;
			}
			else {
				if(arr[arr[i]-1] == arr[i]) {
					//check arr[arr[i]-1] to see if the value there is arr[i]
					//if so then arr[i] is duplicate 
					
					//System.out.println("found dup");
					return arr[i];
				} else {
					//arr[arr[i]-1] is also in wrong position so 
					//make arr[i] in correct position by putting it in arr[arr[i]-1]
					//next iteration will keep checking at same i since arr[i] could still be incorrect after swap
					
					//System.out.println("\tswapping: " + arr[arr[i]-1] + " with " + arr[i]);
					int temp = arr[arr[i]-1];
					arr[arr[i]-1] = arr[i];
					arr[i] = temp;
				}
			}
		}
		return -1;
	}
	
	public static int findMissing(int[] arr) {
		int dup = findDuplicates(arr);
		if(dup == -1)
			return dup;
		
		int len = arr.length; 
		int partSum = 0;
		for(int i = 0; i < len; ++i)
			partSum += arr[i];
		
		int fullSum = (len*(len+1))/2;
		System.out.println("duplicate: " + dup);
		System.out.println("fullSum: " + fullSum);
		System.out.println("partSum: " + partSum);
		System.out.println("missing: " + (fullSum-(partSum-dup)));
		return fullSum - (partSum - dup);
	}
}
