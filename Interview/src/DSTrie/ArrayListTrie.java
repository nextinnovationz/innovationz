package DSTrie;

import java.util.ArrayList;

public class ArrayListTrie {
	public ArrayListNode root;
	
	public ArrayListTrie() {
		this.root = new ArrayListNode(' ');
	}
	
	public void insert(String word) {
		int len = word.length();
		ArrayListNode curr = root, temp = new ArrayListNode(' '), child = null;
		char c;
		for(int i = 0; i < len; ++i) {
			c = word.charAt(i);
			temp.data = c;
			child = getChild(curr.children, temp);
			if(child == null) {
				child = new ArrayListNode(c);
				curr.children.add(child);
				//System.out.println("add node: " + c);
			} 
			curr = child;
		}
		++curr.wordCount;
	}
	
	public ArrayListNode getChild(ArrayList<ArrayListNode> children, ArrayListNode target) {
		for(ArrayListNode t : children) {
			if(target.data == t.data) {
				return t;
			}
		}
		return null;
	}
	
	public boolean hasWord(String word) {
		int len = word.length();
		ArrayListNode curr = root, temp = new ArrayListNode(' '), child = null;
		char c;
		for(int i = 0; i < len; ++i) {
			c = word.charAt(i);
			temp.data = c;
			child = getChild(curr.children, temp);
			if(child != null) {
				curr = child;
			} else {
				return false;
			}
		}
		
		return true;
	}
	
	public void printTrie() {
		printTrie(root, new StringBuilder());
	}
	
	public void printTrie(ArrayListNode n, StringBuilder w) {
		//always append curr char
		w.append(n.data);
		
		//if curr char has some count, then it was end for some words
		if(n.wordCount > 0) {
			System.out.println("word: " + w.toString() + "  count: " + n.wordCount);
		}
		
		//in case curr is also an intermediate char for more words, keep going
		for(ArrayListNode temp : n.children) {
			printTrie(temp, w);
		}
		
		//done with curr, backtrack and remove curr char
		w.deleteCharAt(w.length()-1);
	}
}
