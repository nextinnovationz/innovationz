package QList;

import DSList.Node;
import DSList.SinglyLinkedList;

public class Partition {
	public static void main(String[] args) {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.insertBack(8);
		list.insertBack(3);
		list.insertBack(3);
		list.insertBack(9);
		list.insertBack(6);
		list.insertBack(2);
		list.insertBack(4);
		list.print();
		partition(list, new Node<Integer>(6));
		list.print();
	}

	public static void partition(SinglyLinkedList<Integer> list, Node<Integer> pivot) {
		Node<Integer> smallHead = null, smallTail = null, bigHead = null, bigTail = null, curr = list.head;
		while(curr != null) {
			//remove curr head node (do it each time)
			list.head = curr.next;
			
			//detach its next so its a standalone node by itself
			curr.next = null;
			
			if(curr.data < pivot.data) {
				if(smallHead == null) {
					//head node of small sublist
					smallHead = curr;
					smallTail = curr;
				} else {
					//append to small sublist
					smallTail.next = curr;
					smallTail = curr;
				}
			} else if(curr.data > pivot.data) {
				if(bigHead == null) {
					//head node of big sublist
					bigHead = curr;
					bigTail = curr;
				} else {
					//append to big sublist
					curr.next = null;
					bigTail.next = curr;
					bigTail = curr;
				}
			}
			
			//next node is the head again since all previous nodes were deleted
			curr = list.head;
		}
		
		//pivot is tail of small list (middle)
		smallTail.next = pivot;
		
		//the big list is the 2nd half of the list
		pivot.next = bigHead;
		
		//fix head and tail of whole list
		list.head = smallHead;
		list.tail = bigTail;
	}
}
