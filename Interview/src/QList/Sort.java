package QList;

import DSList.Node;
import DSList.SinglyLinkedList;

public class Sort {
	public static void main(String[] args) {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.insertBack(4);
		list.insertBack(3);
		list.insertBack(7);
		list.insertBack(2);
		list.insertBack(0);
		list.insertBack(9);
		list.insertBack(13);
		
		sort(list);
		list.print();
	}

	public static void sort(SinglyLinkedList<Integer> list) {
		Node<Integer> head = list.head, unsorted = head.next, sortedEnd = head, curr = head, prev = null;
		
		while(unsorted != null) {
			System.out.println("processing node: " + unsorted.data);
			
			//search from sortedStart to sortedEnd to try inserting unsorted node
			while(curr != null && curr != unsorted) {
				//found a larger node inside the sorted section, insert unsorted node in front of it
				if(unsorted.data.compareTo(curr.data) == -1) {
					//head needs to be updated
					if(prev == null) {
						System.out.println("\tupdating head");
						unsorted.next = curr;
						list.head = unsorted;
						sortedEnd = list.head;
					} else {  //middle or tail needs to be updated
						System.out.println("\tupdating middle or tail");
						prev.next = unsorted;
						unsorted.next = curr;
						sortedEnd = curr;
					}
					
					//updated sorted section's end
					break;
				}
				prev = curr;
				curr = curr.next;
			}
			
			//unsorted is the largest in the whole list, use prev to insert it as the tail
			//prev was the old tail
			if(curr == null) {
				unsorted.next = prev.next;
				prev.next = unsorted;
				sortedEnd = unsorted;
			}
			
			//if the unsorted node was bigger than all in the sorted section, then it was not inserted yet
			//so curr would still point to sortedEnd and not updated to unsorted
			else if(prev == sortedEnd) {
				unsorted.next = sortedEnd.next;
				sortedEnd.next = unsorted;
				sortedEnd = unsorted;
			}
			
			//next node to insert
			unsorted = sortedEnd.next;
			
			//connect sortedEnd to the next unsorted node before sorting this unsorted node in the next iteration
			//because its next needs to be saved 
			sortedEnd.next = unsorted.next;
			
			//reset for next unsorted insertion
			prev = null;
			curr = head;
		}
	}
}
