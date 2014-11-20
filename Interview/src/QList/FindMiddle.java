package QList;

import DSList.Node;
import DSList.SinglyLinkedList;

public class FindMiddle {
	public static void main(String[] args) {
		SinglyLinkedList<Integer> evenList = new SinglyLinkedList<Integer>();
		evenList.insertBack(1);
		evenList.insertBack(2);
		evenList.insertBack(3);
		evenList.insertBack(4);
		evenList.print();
		System.out.println("even middle: " + findMiddle(evenList).data);
		
		SinglyLinkedList<Integer> oddList = new SinglyLinkedList<Integer>();
		oddList.insertBack(1);
		oddList.insertBack(2);
		oddList.insertBack(3);
		oddList.insertBack(4);
		oddList.insertBack(5);
		oddList.print();
		System.out.println("odd middle: " + findMiddle(oddList).data);
	}
	
	public static Node<Integer> findMiddle(SinglyLinkedList<Integer> list) {
		Node<Integer> fast = list.head, slow = list.head;
		do {  //since fast and slow starts at head, have loop go at least once unconditionally 
			fast = fast.next;
			if(fast != null) {  //only move slow if fast can be moved twice
				fast = fast.next;
				slow = slow.next;
			} 
		} while(fast != null);  //keep searching until fast reached null, slow will be middle
		return slow;
	}
}
