package QList;

import DSList.Node;
import DSList.SinglyLinkedList;

public class FlattenList {
	public static void main(String[] args) {
		
	}
	
	public static void flatten(SinglyLinkedList<Integer> list) {
		if(list == null || list.size() < 2)
			return;
		
		Node<Integer> curr = list.head, firstTail = curr;
		
		//find tail node of 1st level
		while(firstTail.next != null) {
			firstTail = firstTail.next;
		}
		
		//start flattening list (append all levels to 1st level)
		while(curr != null) {
			//curr has a children so it needs to be flatten
			if(curr.random != null) {
				//append children to 1st level tail to bring it up to same level
				firstTail.next = curr.random;
				firstTail = firstTail.next;
			}
			//keep going on 1st level
			curr = curr.next;
		}
	}
	
	public static void unflatten(SinglyLinkedList<Integer> list) { 
		if(list == null || list.size() < 2)
			return;
		
		unflatten(list.head);
	}
	
	public static void unflatten(Node<Integer> n) {
		Node<Integer> curr = n, prevTail = curr;
		while(curr != null) {
			//curr has a children so its child list is appended to 1st level
			if(curr.random != null) {
				//find the node before child list's head
				//this is the old tail before flattening curr's child level
				//set old tail's next to null to detach child level from 1st level
				while(prevTail.next != curr.random) {
					prevTail = prevTail.next;
				}
				
				//detach old tail from child list
				prevTail.next = null;
				
				
				//now separated child level also has its children levels appended to its old tail
				//recursively unflatten 
				unflatten(curr.random);
			}			
			
			//now continue in current level
			curr = curr.next;
			
			//reset tail to curr 
			prevTail = curr;
		}
	}
}
