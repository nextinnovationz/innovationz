package QString;

public class LongestPalindrome {
	public static void main(String[] args) {
		String pattern = "abbacdedcadb";
		System.out.println(findLongestPalindrome(pattern));
	}
	
	public static String findLongestPalindrome(String str) {
		String maxPal = "", temp;
		int strLen = str.length();
		
		for(int i = 0; i < strLen; ++i) {
			//try getting odd length maxPal with center at i
			temp = expand(str, i, i-1, i+1);
			if(temp.length() > maxPal.length())
				maxPal = temp;
			
			//try getting even length maxPal with center at i+1
			temp = expand(str, i+1, i-1, i+1);
			if(temp.length() > maxPal.length())
				maxPal = temp;
		}
		return maxPal;
	}
	
	public static String expand(String str, int center, int left, int right) {
		int strLen = str.length();
		while(left >= 0 && right < strLen && str.charAt(left) == str.charAt(right)) {
			--left;
			++right;
		}
		
		//++left, --right to bring substring into range since these bounds could break loop above
		//also if the loop broke due to nonmatching char on both ends, 
		//then ++left and --right will remove the nonmatching ends and leave substring as longest palindrome at left+1 and right-1
		//substring will be longest palindrome possible at this center
		//which could just be 1 char (center) if can't expand left and right any further
		return str.substring(left+1, right);
	} 
}

