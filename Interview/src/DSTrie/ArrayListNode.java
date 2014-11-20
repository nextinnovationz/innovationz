package DSTrie;

import java.util.ArrayList;

public class ArrayListNode {
	public char data;
	public ArrayList<ArrayListNode> children;
	public int wordCount;
	
	public ArrayListNode(char data) {
		this.data = data;
		this.children = new ArrayList<ArrayListNode>();
		this.wordCount = 0;
	}
	
	//two arraylistnode objects are equal when they are either the same object or have same data field value
	//originally the Object class's equals() are compare by using hashCode() which is the memory address of the object
	//if the object already override the hashCode(), then its not memory address (like String has its own hash)
	//since equals() is overriden, hashCode() must also be overriden in case this object is used in hashtable
	//because hashtable uses an object's hashcode to compute the index 
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof ArrayListNode)) {
			return false;
		} else if(obj == this) {
			return true;
		} else {
			return data == ((ArrayListNode)obj).data;
		}
	}
	
	//hashcode for this object is just the data field value
	//so collision is possible 
	@Override
	public int hashCode() {
		return data;
	}
}
