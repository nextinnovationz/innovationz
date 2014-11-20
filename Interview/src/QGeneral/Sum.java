package QGeneral;

public class Sum {
	public static void main(String[] args) {
		int n = 3;
		Sum[] arr = new Sum[n];
		System.out.println("sum: " + arr[0].sum);
	}
	
	private int sum = 0, n = 1;
	
	public Sum() {
		System.out.println("n: " + n + "\tsum: " + sum);
		sum += n;
		++n;
	}
}
