package QThread;

public class MergeSort {
	public static void main(String[] args) {
		
	}
	
	public static void sort(int[] arr) {
		
	}
	
	public static int[] sort(int[] arr, int start, int end) {
		if(end - start > 0) {
			int mid = (start+end)/2;
			int[] left = sort(arr, start, mid);  //new thread
			int[] right = sort(arr, mid+1, end);  //new thread
			
			//join threads before merge 
			merge(left, right);
		}
		return new int[]{arr[end]};
	}
	
	public static void merge(int[] left, int[] right) {
		
	}
}
