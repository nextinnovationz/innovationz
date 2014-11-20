package QGeneral;

public class StockPrices {
	public static void main(String[] args) {
		int[] arr = {1,3,2,4,13,5,7,9,8,7,0,5,8,2,9,12};
		findBestPattern(arr);
	}
	
	public static void findBestPattern(int[] arr) {
		int min = arr[0], minIdx = 0, maxIdx = 1, diff = 0, len = arr.length;
		int prevMin = arr[0], prevMinIdx = arr[0], prevMaxIdx = 0, prevDiff = 0;
		
		for(int i = 1; i < len; ++i) {  //treat each 1 to len-1 elements as the max on the right
			if(min > arr[i]) {  //try to find min 
				prevMin = min;
				prevMinIdx = minIdx;
				
				min = arr[i];
				minIdx = i;
			} else {		
				//compare each element with current min and update diff
				//even if min is updated at arr[i], the diff will be 0 so it will not update diff
				//so diff will only update with minIdx < maxIdx to preserve buy and sell order of stocks
				if(arr[i] - min > diff) {
					if(arr[i] - prevMin > diff) {
						maxIdx = i;
						diff = arr[i] - min;
					}
				}	
			}
		}
		System.out.println("min: " + arr[minIdx] + "\tmax: " + arr[maxIdx]);
	}
	
	static class Result {
		public int minIdx = 0, maxIdx = 0;
	}
	/*
	public static Result findBestPatternRecursive(int[] arr) {
		
	}
	*/
}
