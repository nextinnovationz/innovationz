package QString;

public class StringTokenizer {
	public static void main(String[] args) {
		String str = "This is a sentence.";
		String delim = " ";
		Tokenizer st = new Tokenizer(str, delim);
		System.out.println(st.tokenize());
		System.out.println(st.tokenize());
		System.out.println(st.tokenize());
		System.out.println(st.tokenize());
		System.out.println(st.tokenize());
	}
	
	static class Tokenizer {
		public String str, delim;
		public int start, end;
		public boolean gotLast;
		
		public Tokenizer(String str, String delim) {
			this.str = str;
			this.delim = delim;
			this.start = 0;
			this.end = 0;
			this.gotLast = false;
		}
		
		public String tokenize() {
			//in case this call is trying to get last token
			//don't return last token if it already got last token before
			if(gotLast) 
				return null;
			
			int slen = str.length(), dlen = delim.length(), dIdx = 0;

			while(end < slen) {
				//found 1st delimiter char in str
				if(str.charAt(end) == delim.charAt(dIdx)) {
					//continue searching str for rest of delimiter
					while(end < slen && dIdx < dlen && str.charAt(end) == delim.charAt(dIdx)) {
						++end;
						++dIdx;
					}
					//all delimiter char found, tokenize the substring [start, end)
					if(dIdx == dlen) {
						String sub = substring(str, start, end);
						//place start for next string after last char of delimiter for next tokenize
						start = end;
						dIdx = 0;
						return sub;
					}
				}
				++end;
			}
			
			//only allow tokenizer to return last token once
			//any subsequent calls will not return the same last token again
			if(end == slen) {
				gotLast = true;
			}
			
			//no more delimiter found so just return the remaining str
			return substring(str, start, end);
		}
		
		public static String substring(String str, int start, int end) {
			StringBuilder sub = new StringBuilder();
			int curr = start;
			while(curr < end) {
				sub.append(str.charAt(curr));
				++curr;
			}
			return sub.toString();
		}	
	}
}
