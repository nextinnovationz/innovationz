package QGeneral;

public class Conversion {
	public static void main(String[] args) {
		System.out.println("int: " + stringToInteger("120"));
		System.out.println("str: " + integerToString(120));
	}
	
	public static int stringToInteger(String num) {
		boolean isNeg = num.charAt(0) == '-';
		int result = 0;
		int len = num.length();
		int i = 0;

		if(isNeg)
			i = 1;
		
		while(i < len) {
			int c = num.charAt(i) - 48; //ascii 0 is 48 so every char-48 will yield the actual int digit 
			//System.out.println("c: " + c);
			result = result * 10 + c;  //whole result gets increased by x10 from right to left
			++i;
		}
		
		if(isNeg)
			result = -result;
		return result;
	}
	
	public static String integerToString(int num) {
		boolean isNeg = num < 0 ? true : false;
		StringBuffer sb = new StringBuffer();
		
		while(num > 0) {
			sb.append(num % 10);  //append rightmost digit each time
			num /= 10;  //reduce num by 1 rightmost digit each time
		}
		
		if(isNeg)
			sb.append('-');
		
		return sb.reverse().toString();  //need to reverse the string 
	}
}
