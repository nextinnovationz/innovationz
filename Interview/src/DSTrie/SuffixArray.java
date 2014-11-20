package DSTrie;

import java.util.Arrays;

public class SuffixArray {
	public String str;
	public String[] suffixes;
	
	public SuffixArray(String str) {
		this.str = str;
		this.suffixes = new String[str.length()];
		createSuffixes();
	}
	
	public void createSuffixes() {
		//generate all possible suffixes including whole str
		//each suffix removes a char from front till end 
		int len = str.length();
		for(int i = 0; i < len; ++i) {
			suffixes[i] = str.substring(i, len);
		}
		
		Arrays.sort(suffixes);
	}
	
	public int[] longestCommonPrefixLengths() {
		int[] lcp = new int[suffixes.length-1];
		String a, b;
		int aidx, bidx;
		
		//check each suffixes[i] and suffixes[i-1] for lcp[i]
		//prefix must start matching chars from index 0 
		for(int i = 1; i < suffixes.length; ++i) {
			aidx = 0;
			bidx = 0;
			a = suffixes[i-1];
			b = suffixes[i];
			while(aidx < a.length() && bidx < b.length()) {
				if(a.charAt(aidx) != b.charAt(bidx)) {
					//aidx and bidx should be the same up until aidx-1 
					lcp[i-1] = aidx-1;
					break;
				} else{
					++aidx;
					++bidx;
				}
			}
			
			//in case a and b are same length and everything matched so the if-stmt never executed
			if(aidx == a.length())  //matched all a, b could be longer but remaining won't match a
				lcp[i-1] = aidx-1;
			else if(bidx == b.length())  //matched all b, a could be longer but remaining won't match b
				lcp[i-1] = bidx-1;
		}
		
		return lcp;
	}
	
	public String longestCommonPrefix() {
		int[] lcp = longestCommonPrefixLengths();

		//find longest common prefix length in the array
		int maxlen = lcp[0], maxidx = 0;
		for(int i = 0; i < lcp.length; ++i) {
			if(lcp[i] > maxlen) {
				maxlen = lcp[i];
				maxidx = i;
			}
		}
		
		//get the suffix from suffix array (using maxidx from lcp array)
		//the longest common prefix is the substring from 0 to maxlen+1 (because substring excludes last index)
		//between suffixes[maxidx] and suffixes[maxidx+1]
		return suffixes[maxidx].substring(0, maxlen+1);
	}
}
