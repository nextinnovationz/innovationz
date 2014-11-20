package QList;

import DSList.Node;
import DSList.SinglyLinkedList;

public class SeparateEvenOddLists {
	public static void main(String[] args) {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.insertBack(1);
		list.insertBack(2);
		list.insertBack(0);
		list.insertBack(3);
		list.insertBack(8);
		list.insertBack(4);
		list.insertBack(5);
		list.insertBack(7);
		list.insertBack(6);
		list.insertBack(9);
		list.insertBack(9);
		
		list.print();
		separate(list);
	}
	
	public static void separate(SinglyLinkedList<Integer> list) {
		SinglyLinkedList<Integer> even = new SinglyLinkedList<Integer>();
		SinglyLinkedList<Integer> odd = new SinglyLinkedList<Integer>();
		
		Node<Integer> curr = list.head, evenTail = null, oddTail = null;
		while(curr != null) {
			if((curr.data & 1) == 0) {
				if(evenTail == null) {
					evenTail = curr;
					even.head = curr;
				} else {
					evenTail.next = curr;
					evenTail = evenTail.next;
				}
			} else {
				if(oddTail == null) {
					oddTail = curr;
					odd.head = curr;
				} else {
					oddTail.next = curr;
					oddTail = oddTail.next;
				}
			}
			
			curr = curr.next;
		}
		
		evenTail.next = null;
		oddTail.next = null;

		even.print();
		odd.print();
	}
}
