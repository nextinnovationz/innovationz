package QList;

import DSList.Node;
import DSList.SinglyLinkedList;

public class RemoveDuplicates {
	public static void main(String[] args) {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.insertBack(4);
		list.insertBack(4);
		list.insertBack(3);
		list.insertBack(3);
		list.insertBack(5);
		list.insertBack(5);
		list.insertBack(8);
		list.insertBack(7);
		list.insertBack(7);
		//removeDuplicatesKeepNone(list);
		removeDuplicatesKeepOne(list);
		list.print();
	}
	
	/*
	 * 
	 */
	public static void removeDuplicatesKeepNone(SinglyLinkedList<Integer> list) {
		Node<Integer> curr = list.head, dup = curr.next;
		boolean hasDup = false;
		
		//detect and remove dups for each node in list
		while(curr != null) {
			System.out.println("curr: " + curr.data);
			
			//from dup, find the next distinct node different from curr
			while(dup != null && dup.data == curr.data) {
				System.out.println("\tdup: " + dup.data);
				dup = dup.next;
				hasDup = true;
			}
						
			//check if curr has dup
			if(hasDup) {
				//next distinct node is not null and curr has dup
				if(dup != null && dup != curr) {
					System.out.println("\treplacing " + curr.data + " with " + dup.data);
					curr.data = dup.data;
					curr.next = dup.next;	
				} else {  //next distinct node is null so no more replacement can be done
					System.out.println("\tno replacement since it is the last dup element, \n\tneed to find prev to set its next to null");
					
					//use temp to find the prev node before curr 
					//set prev's next to null since everything is dup from that point till null
					Node<Integer> temp = list.head;
					while(temp.next != curr) {
						temp = temp.next;
					}
					
					//delete all remaining dups
					temp.next = null;
					curr = temp;
					curr = curr.next;
				}
			} else {  //no dup for curr, move on to next node
				System.out.println("\tno replacement since it has no dup");
				curr = curr.next;
			}
			
			//dup is always 1 step ahead of curr
			if(curr != null)
				dup = curr.next;
			
			//reset dup flag for next node check
			hasDup = false;
			
			//System.out.println("\t\tnew curr: " + curr.data + "\tnew dup: " + dup.data);
		}
	}
	
	public static void removeDuplicatesKeepOne(SinglyLinkedList<Integer> list) {
		Node<Integer> curr = list.head, dup = curr.next;
		boolean hasDup = false;
		
		while(curr != null) {
			while(dup != null && dup.data == curr.data) {
				dup = dup.next;
				hasDup = true;
			}
			
			if(hasDup) {
				curr.next = dup;
			}
			
			if(curr != null)
				curr = curr.next;
			
			if(curr != null)
				dup = curr.next;
			
			hasDup = false;
		}
	}
}
