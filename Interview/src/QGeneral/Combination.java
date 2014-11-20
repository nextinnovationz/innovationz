package QGeneral;

import java.util.ArrayList;
import java.util.Collections;

public class Combination {
	public static void main(String[] args) {
		//a string of length n has n(n+1)/2 combinations -> O(n^2) 
		String test = "abcd";
		ArrayList<String> all = combination(test);
		Collections.sort(all);
		System.out.println(all.toString());
		System.out.println();
		ArrayList<String> all2 = combinationRecursive(test);
		Collections.sort(all2);
		System.out.println(all2.toString());
	}
	
	public static ArrayList<String> combination(String s) {
		if(s == null || s.length() == 0)
			return null;
		
		ArrayList<String> all = new ArrayList<String>();
		boolean[] combo = new boolean[s.length()];
		
		//keep generating bit combinations until it reached the end when all bits are set (maximum number)
		while(!generateCombo(combo)) {
			all.add(print(combo, s));
		}
		
		return all;
	}
	
	public static boolean generateCombo(boolean[] combo) {
		int i = combo.length-1;
		while(i >= 0 && combo[i]) {  
			combo[i] = false;
			--i;
		}
		
		if(i >= 0)  
			combo[i] = true;
		
		//no need to check each bit
		//if all bits are set, then the while loop above will reach the end and i < 0
		return i < 0;  
		
		/*
		//check if all bits are set
		//if so, then all combinations has been generated so the while loop in caller breaks
		i = 0;
		while(i < combo.length) {
			if(!combo[i])
				return false;
			++i;
		}
		return true;
		*/
	}
	
	public static String print(boolean[] combo, String s) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < s.length(); ++i) {
			if(combo[i]) {  //print each set character from bit array for this combination
				//System.out.print(s.charAt(i));
				sb.append(s.charAt(i));
			}
		}
		//System.out.println();
		return sb.toString();
	}
	
	public static ArrayList<String> combinationRecursive(String str) {
		ArrayList<String> all = new ArrayList<String>();
		combinationRecursive(str, new StringBuilder(), 0, all);
		return all;
	}
	
	public static void combinationRecursive(String orig, StringBuilder build, int pos, ArrayList<String> all) {
		//pos will start at that index that was passed from caller (caller's pos+1)
		//since combination don't have a fixed length (1 to len), no need to start at index 0
		for(int i = pos; i < orig.length(); ++i) {
			build.append(orig.charAt(i));  //add this char
			//System.out.println(build);  //print this combination
			all.add(build.toString());
			combinationRecursive(orig, build, i+1, all);  //recurse for more combination with remaining len-i+1 chars
			build.deleteCharAt(build.length()-1);  //remove this char 
		}
	}
	
	
}
