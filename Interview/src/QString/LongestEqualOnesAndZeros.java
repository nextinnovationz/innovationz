package QString;

public class LongestEqualOnesAndZeros {
	public static void main(String[] args) {
		String bin = "1101011001110001001";
		System.out.println(findLongestEqual(bin));
	}
	
	public static String findLongestEqual(String str) {
		int strlen = str.length()-1; 
		String maxstr = "", temp;
		
		//don't need to check last char since its center is at len-1 and it can't expand to an even length substring
		for(int i = 0; i < strlen; ++i) {  
			//only try expanding center if the even center are different chars (10 or 01)
			if(str.charAt(i) != str.charAt(i+1)) {
				temp = expand(str, i, i-1, i+2);
				if(temp.length() > maxstr.length())
					maxstr = temp;	
			}
		}
		return maxstr;
	}
	
	public static String expand(String str, int center, int left, int right) {
		int strlen = str.length();
		//left char must match center i
		//right char must match center i+1
		//1100 or 0011
		while(left >= 0 && right < strlen && str.charAt(left) == str.charAt(center) && str.charAt(right) == str.charAt(center+1)) {
			--left;
			++right;
		}
		
		return str.substring(left+1, right);
	}
}
