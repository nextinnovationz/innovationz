package DSTrie;

public class HashMapTrie {
	public HashMapNode root;
	
	public HashMapTrie() {
		this.root = new HashMapNode(' ');
	}
	
	public void insert(String word) {
		int len = word.length();
		HashMapNode curr = root, child = null;
		char c;
		for(int i = 0; i < len; ++i) {
			c = word.charAt(i);
			if(!curr.children.containsKey(c)) {
				child = new HashMapNode(c);
				curr.children.put(c, child);
				curr = child;
				//System.out.println("add node: " + c);
			} else {
				curr = curr.children.get(c);
			}
		}
		++curr.wordCount;
	}
	
	public boolean hasWord(String word) {
		int len = word.length();
		HashMapNode curr = root;
		char c;
		for(int i = 0; i < len; ++i) {
			c = word.charAt(i);
			if(curr.children.containsKey(c)) {
				curr = curr.children.get(c);
			} else {
				return false;
			}
		}
		
		return true;
	}
	
	public void printTrie() {
		printTrie(root, new StringBuilder());
	}
	
	public void printTrie(HashMapNode n, StringBuilder w) {
		//always append curr char
		w.append(n.data);
		
		//if curr char has some count, then it was end for some words
		if(n.wordCount > 0) {
			System.out.println("word: " + w.toString() + "  count: " + n.wordCount);
		}
		
		//in case curr is also an intermediate char for more words, keep going
		for(HashMapNode temp : n.children.values()) {
			printTrie(temp, w);
		}
		
		//done with curr, backtrack and remove curr char
		w.deleteCharAt(w.length()-1);
	}
}