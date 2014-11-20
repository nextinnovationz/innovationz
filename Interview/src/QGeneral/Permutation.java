package QGeneral;

public class Permutation {
	public static void main(String[] args) {
		//a string of length n has n! permutations -> O(n!)
		String s = "abcd";
		System.out.println("count:" + permutation(s));
	}
	
	public static int permutation(String s) {
		if(s == null || s.length() == 0)
			return 0;
		
		boolean[] used = new boolean[s.length()];
		return permutation(new StringBuilder(), s, used, 0);
	}
	
	public static int permutation(StringBuilder build, String orig, boolean[] used, int count) {	
		//base case to stop recursion
		if(build.length() == orig.length()) {
			System.out.println(build.toString());
			return 1;  
		} else {
			int cnt = 0;
			for(int i = 0; i < orig.length(); ++i) {
				//current char was used already so skip it
				if(used[i])
					continue;
				
				//current char was not used yet so use it now
				used[i] = true;
				build.append(orig.charAt(i));
				cnt += permutation(build, orig, used, count);
				used[i] = false;
				build.deleteCharAt(build.length()-1);
			}
			return count + cnt;
		}
	}
}
