package QList;

import DSList.Node;
import DSList.SinglyLinkedList;

public class FindIntersection {
	public static void main(String[] args) {
		SinglyLinkedList<Integer> aList = new SinglyLinkedList<Integer>();
		aList.insertBack(4);
		aList.insertBack(3);
		aList.insertBack(7);
		aList.insertBack(8);
		
		SinglyLinkedList<Integer> bList = new SinglyLinkedList<Integer>();
		bList.insertBack(5);
		bList.insertBack(2);
		
		Node<Integer> intersect = new Node<Integer>(100);
		aList.tail.next = intersect;
		aList.tail = intersect;
		++aList.length;
		
		bList.tail.next = intersect;
		bList.tail = intersect;
		++bList.length;

		bList.insertBack(11);
		bList.insertBack(12);
		bList.insertBack(13);
		bList.insertBack(15);
		bList.insertBack(85);
		
		System.out.println("intersect: " + findIntersection(aList, bList).data);
	}
	
	public static Node<Integer> findIntersection(SinglyLinkedList<Integer> aList, SinglyLinkedList<Integer> bList) {
		int aLen = findListLength(aList), bLen = findListLength(bList);
		Node<Integer> aCurr = aList.head, bCurr = bList.head;
		
		//move longer list by the diff amount, then move together until intersection
		if(aLen > bLen) {
			int diff = aLen-bLen;
			while(diff > 0) {
				aCurr = aCurr.next;
				--diff;
			}
		} else if(bLen > aLen) {
			int diff = bLen-aLen;
			while(diff > 0) {
				bCurr = bCurr.next;
				--diff;
			}
		}
		
		//both starting point are the same length now
		while(aCurr != bCurr) {
			aCurr = aCurr.next;
			bCurr = bCurr.next;
		}
		
		return aCurr;  //if null, then no intersection
	}
	
	public static int findListLength(SinglyLinkedList<Integer> list) {
		int count = 0;
		Node<Integer> curr = list.head;
		while(curr != null) {
			curr = curr.next;
			++count;
		}
		return count;
	}
}
