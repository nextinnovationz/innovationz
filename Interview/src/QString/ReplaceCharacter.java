package QString;

public class ReplaceCharacter {
	public static void main(String[] args) {
		char[] str = "this is a simple sentence\0.".toCharArray();
		char[] rep = "%20".toCharArray();
		char tar = ' ';
		System.out.println("inserted: " + new String(reverseRightToLeft(generateCharArray(str, rep, tar), rep, tar)));
	}
	
	public static char[] generateCharArray(char[] orig, char[] replace, char target) {
		int targetCnt = 0, oldLen = 0;
		while(orig[oldLen] != '\0') {  //find end of old string in orig to get old len
			if(orig[oldLen] == target)
				++targetCnt;
			++oldLen;
		}
		
		int newLen = oldLen + (targetCnt * 2);
		char[] newArr = new char[newLen];
		for(int i = 0; i < oldLen; ++i) {
			newArr[i] = orig[i];
		}
		return newArr;
	}
	
	//orig will have enough space to fit all replacements
	public static char[] reverseRightToLeft(char[] orig, char[] replace, char target) {
		int targetCnt = 0, oldLen = 0;
		while(orig[oldLen] != '\0') {  //find end of old string in orig to get old len
			if(orig[oldLen] == target)
				++targetCnt;
			++oldLen;
		}

		int newLen = oldLen + (targetCnt * 2);
		int repLen = replace.length-1;
		int curr = newLen-1, rcurr = repLen, old = oldLen-1;

		while(curr >= 0 && curr > old) {
			if(orig[old] == target) {  //need to replace, insert in reverse order all the replacement chars
				while(rcurr >= 0) {
					orig[curr] = replace[rcurr];
					--rcurr;
					--curr;
				}
				rcurr = repLen;  //reset rcurr to end of replacement set for next replacement
			} else {  //don't replace, just copy old char to new position
				orig[curr] = orig[old];
				--curr;
			}
			--old;
		}
		
		return orig; 
	}
}
