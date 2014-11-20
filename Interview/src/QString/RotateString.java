package QString;

public class RotateString {
	public static void main(String[] args) {
		char[] str = "abcdefgh".toCharArray();
		rotate1(str, 2);
		
		char[] str2 = "abcdefgh".toCharArray();
		rotate2(str2, 2);
		System.out.println(new String(str));
		System.out.println(new String(str2));
	}
	
	public static void rotate1(char[] str, int amount) {
		//str: abcdefgh
		//reverse abcdefgh -> hgfedcba
		//reverse ba -> ab
		//reverse hgfedc -> cdefgh
		//final: cdefghab
		
		reverse(str, 0, str.length-1);  //reverse whole string
		reverse(str, str.length-amount, str.length-1);  //reverse rotated portion at back
		reverse(str, 0, str.length-amount-1);  //reverse unrotated portion at front
	}
	
	public static void rotate2(char[] str, int amount) {
		//str: abcdefgh
		//reverse ab -> ba
		//reverse cdefgh -> hgfedc
		//reverse bahgfedc -> cdefghab
		//final: cdefghab
		
		reverse(str, 0, amount-1);  //reverse [0, amount-1] 
		reverse(str, amount, str.length-1);  //reverse [amount, length-1]
		reverse(str, 0, str.length-1);  //reverse whole string	
	}
	
	public static void reverse(char[] str, int start, int end) {
		char temp;
		while(start < end) {
			temp = str[start];
			str[start] = str[end];
			str[end] = temp;
			++start;
			--end;
		}
	}
}
