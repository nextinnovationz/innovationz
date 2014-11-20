package QString;

public class MatchRegex {
	public static void main(String[] args) {
		String str = "aab";
		String regex = "a.a*b";
		System.out.println(match(str, regex));
	}
	
	public static boolean match(String str, String regex) {
		char[] strc = str.toCharArray();
		char[] regc = regex.toCharArray();
		int scurr  = 0, rcurr = 0, slen = str.length(), rlen = regex.length();
		while(scurr < slen && rcurr < rlen) {
			if(regc[rcurr] == '.') {
				++rcurr;
				char target = regc[rcurr];
				++rcurr;  
				int firstNotMatchIdx = matchOneOrMore(strc, scurr, target);
				//matched zero, so false
				if(firstNotMatchIdx == scurr) {
					return false;
				}
				//matched at least one so update string pointer to 1st nonmatching
				scurr = firstNotMatchIdx;
			} else if(regc[rcurr] == '*') {
				++rcurr;
				char target = regc[rcurr];
				++rcurr;
				//always match zero or more so just update string pointer to 1st nonmatching
				scurr = matchZeroOrMore(strc, scurr, target);
			} else if(strc[scurr] == regc[rcurr]) {
				//just regular char matching from str and regex so increment both pointers
				++rcurr;
				++scurr;
			} else {
				//regular char matching failed so str doesn't match regex
				return false;
			}
		}
		
		//matched all str and matched all regex
		if(scurr == slen && rcurr == rlen) {
			return true;
		}
		
		//not all str or not all regex was matched
		return false;
	}
	
	public static int matchZeroOrMore(char[] str, int strpos, char target) {
		int slen = str.length;
		
		//match zero or more of target starting from strpos (target+1 index)
		while(strpos < slen && str[strpos] == target) {
			++strpos;
		}
		//return index of 1st char not matching target
		//if no match at all, strpos is just target+1 index (same as parameter)
		return strpos;
	}
	
	public static int matchOneOrMore(char[] str, int strpos, char target) {
		int slen = str.length, start = strpos;
		
		//keep matching target until it reached end of str or no match
		while(start < slen && str[start] == target) {
			++start;
		}
		
		//check to see if at least one char match target
		//if start is the ame as strpos, then zero match so return target+1 index (same as parameter)
		if(start == strpos) {
			return strpos;
		}
		//at least one match so return the index of 1st nonmatching char
		return start;
	}
}
