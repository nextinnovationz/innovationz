package QString;

public class RemoveCharacter {
	public static void main(String[] args) {
		char[] a = "i'm an apple from new york".toCharArray();
		char[] exa = "poe".toCharArray();
		System.out.println(new String(remove(a, setupExclusionTable(exa))));
	}
	
	public static char[] remove(char[] orig, boolean[] excludeSet) {
		int curr = 0, free = 0;
		while(curr < orig.length) {
			if(!excludeSet[orig[curr]]) {  //curr is good, copy it to free position
				orig[free] = orig[curr];
				++free;  //move free 1 to the right
			} 
			++curr;  //always move curr to check each char in orig
		}
		
		//free does not point to end of string so it still contains garbage from original
		//just set them all to spaces to hide visibility (or copy from 0 to free to create a new string)
		while(free < orig.length) {
			orig[free] = ' ';
			++free;
		}
		return orig;
	}
	
	public static boolean[] setupExclusionTable(char[] ex) {
		boolean[] table = new boolean[256];
		int pos = 0;
		while(pos < ex.length) {
			table[ex[pos]] = true;
			++pos;
		}
		return table;
	}
}
