package DSTrie;

public class Driver {
	public static void main(String[] args) {
		String s = "I was a wasitr at a lb";
		SuffixArray sf = new SuffixArray(s);
		System.out.println(sf.longestCommonPrefix());
		
		ArrayListTrie t = new ArrayListTrie();
		t.insert("banana");
		t.insert("ana");
		t.insert("ban");
		t.insert("apple");
		t.insert("appleseed");
		t.insert("ban");
		t.insert("go");
		t.insert("zebra");
		t.insert("got");
		t.printTrie();
		
		System.out.println();
		
		HashMapTrie mt = new HashMapTrie();
		mt.insert("banana");
		mt.insert("ana");
		mt.insert("ban");
		mt.insert("apple");
		mt.insert("appleseed");
		mt.insert("ban");
		mt.insert("go");
		mt.insert("zebra");
		mt.insert("got");
		mt.printTrie();
	}
}
