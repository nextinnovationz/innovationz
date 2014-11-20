package DSHashtable;

public class Driver {
	public static void main(String[] args) {
		String str = "ho yin pun";
		LinearProbeHashtable<Character, Integer> lpt = new LinearProbeHashtable<Character, Integer>();	
		hash(str, lpt);
		lpt.print();
	}
	
	public static void hash(String str, Operations<Character, Integer> table) {
		int len = str.length();
		for(int i = 0; i < len; ++i) {
			if(table.contains(str.charAt(i)))
				table.add(str.charAt(i), table.find(str.charAt(i)));
			else
				table.add(str.charAt(i), 1);
		}
	}
}
