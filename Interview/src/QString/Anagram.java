package QString;

public class Anagram {
	public static void main(String[] args) {
		String a = "apple";
		String b = "pljea";
		System.out.println("isAnagram: " + isAnagram(a,b));
	}
	
	public static boolean isAnagram(String a, String b) {
		if(a == null || b == null)
			return false;
		
		if(a.length() != b.length())
			return false;
		
		int[] lookup = new int[256];
		int aLen = a.length(), bLen = b.length();
				
		//count occurrences of each char in hashtable
		for(int i = 0; i < aLen; ++i) {
			++lookup[a.charAt(i)];
		}
		
		//cancel out each char count in hashtable
		for(int j = 0; j < bLen; ++j) {
			if(lookup[b.charAt(j)] == 0)
				return false;
			else
				--lookup[b.charAt(j)];
		}
		
		//if all chars canceled out, then lookup entries should be all 0
		for(int k = 0; k < aLen; ++k) {
			if(lookup[a.charAt(k)] > 0)
				return false;
		}
		
		return true;
	}
}
