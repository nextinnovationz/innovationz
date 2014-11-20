package QArray;

public class FindLeader {
	public static void main(String[] args) {
		int[] arr = {1,2,8,3,2,5,6,3,2,1,0};
		findLeaders(arr);
	}
	
	public static void findLeaders(int[] arr) {
		int max = arr[arr.length-1];
		for(int i = arr.length-2; i >= 0; --i) {
			if(arr[i] >= max) {
				max = arr[i];
				System.out.println(max);
			}
		}
	}
}
