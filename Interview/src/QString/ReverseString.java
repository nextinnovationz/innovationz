package QString;

public class ReverseString {

	public static void main(String[] args) {
		char[] s = "hello world!".toCharArray();
		reverseString(s);
		System.out.println("reverse: " + new String(s));
		reverseSentence(s);
		System.out.println("reverse sentence: " + new String(s));
	}
		
	public static void reverseString(char[] str, int start, int end) {
		int left = start, right = end-1;
		char temp;
		
		//swap every char from start to end (stop in middle)
		//if odd length, then middle is not swapped because left == right so it stops
		while(left < right) {
			temp = str[left];
			str[left] = str[right];
			str[right] = temp;
			++left;
			--right;
		}
	}
	
	public static void reverseString(char[] str) {
		if(str == null || str.length == 0)
			return;
		reverseString(str, 0, str.length);
	}
	
	public static void reverseSentence(char[] str) {
		int pos = 0, space = 0;
		int len = str.length;
		
		//find space delimiter to break words
		//swap all chars between [pos,space) for each word
		while(pos < len && space < len) {
			if(str[space] == ' ') {  //found a space to delimit
				reverseString(str, pos, space);  //reverse [pos, space) for this word
				pos = space + 1;  //assume only 1 space delimiting between two words
				space = pos;  //start finding next space again 
			} else {
				++space;
			}
		}
		
		//last word don't have any space at the end 
		reverseString(str, pos, space);
	}
}
