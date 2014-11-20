package DSTrie;

import java.util.HashMap;

public class HashMapNode {
	public char data;
	public HashMap<Character,HashMapNode> children;
	public int wordCount;
	
	public HashMapNode(char data) {
		this.data = data;
		this.children = new HashMap<Character,HashMapNode>();
		this.wordCount = 0;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof HashMapNode)) {
			return false;
		} else if(obj == this) {
			return true;
		} else {
			return data == ((HashMapNode)obj).data;
		}
	}
	
	@Override
	public int hashCode() {
		return data;
	}
}
