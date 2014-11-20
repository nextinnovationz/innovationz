package QList;

import DSList.Node;
import DSList.SinglyLinkedList;

public class MergeLists {
	public static void main(String[] args) {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.insertBack(4);
		list.insertBack(3);
		list.insertBack(7);
		list.insertBack(2);
		list.insertBack(0);
		list.insertBack(9);
		list.insertBack(13);
		//list.print();
		
		SinglyLinkedList<Integer> list2 = new SinglyLinkedList<Integer>();
		list2.insertBack(12);
		list2.insertBack(14);
		list2.insertBack(8);
		list2.insertBack(9);
		list2.insertBack(0);
		list2.insertBack(7);
		list2.insertBack(7);
		//list2.print();
		
		SinglyLinkedList<Integer> merge = mergeUnsortedLists(list, list2);
		merge.print();
	}
	
	public static SinglyLinkedList<Integer> mergeSortedLists(SinglyLinkedList<Integer> aList, SinglyLinkedList<Integer> bList) {
		if(aList == null || aList.length == 0)
			return bList;
		
		if(bList == null || bList.length == 0)
			return aList;
		
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		Node<Integer> acurr = aList.head, bcurr = bList.head;
		
		//set head for new list first
		if(acurr.data <= bcurr.data) {
			list.head = acurr;
			list.tail = acurr;
			acurr = acurr.next;
		} else {
			list.head = bcurr;
			list.tail = bcurr;
			bcurr = bcurr.next;
		}
		
		//merge remaining nodes in aList and bList (via tail insertion)
		while(acurr != null && bcurr != null) {
			if(acurr.data <= bcurr.data) {
				list.tail.next = acurr;
				list.tail = acurr;
				acurr = acurr.next;
			} else {
				list.tail.next = bcurr;
				list.tail = bcurr;
				bcurr = bcurr.next;
			}
		}
		
		//copy remaining aList into tail if aList was longer
		while(acurr != null) {
			list.tail.next = acurr;
			list.tail = acurr;
			acurr = acurr.next;
		}
		
		//copy remaining bList into tail if bList was longer
		while(bcurr != null) {
			list.tail.next = bcurr;
			list.tail = bcurr;
			bcurr = bcurr.next;
		}
		
		return list;
	}
	
	public static SinglyLinkedList<Integer> mergeUnsortedLists(SinglyLinkedList<Integer> aList, SinglyLinkedList<Integer> bList) {
		if(aList == null || aList.length == 0)
			return bList;
		
		if(bList == null || bList.length == 0)
			return aList;
	
		sortList(aList);
		//aList.print();
		sortList(bList);
		//bList.print();
		return mergeSortedLists(aList, bList);
	}
	
	public static void sortList(SinglyLinkedList<Integer> list) {
		Node<Integer> unsorted = list.head.next, sortedEnd = list.head, curr = list.head, prev = null, cut = unsorted.next;
	
		//keep sorting until end of list
		while(unsorted != null) {
			//save the next node of unsorted 
			cut = unsorted.next;
			
			do {  //sortedEnd and curr starts at head so need to loop at least once using do-while
				if(unsorted.data <= curr.data) {
					//sorted is larger so unsorted must be in front of it
					//sorted is head so unsorted becomes new head
					if(prev == null) {
						unsorted.next = list.head;
						list.head = unsorted;
					} else {  //sorted is not head so just reconnect like a middle node
						unsorted.next = prev.next;
						prev.next = unsorted;
					}
					
					//done inserting, reconnect sortedEnd's next to unsorted's next
					sortedEnd.next = cut;
					break;
				} else {
					//move to next node to continue searching for a spot to insert
					prev = curr;
					curr = curr.next;
				}
			} while(curr != unsorted);  //keep check unsorted from head to sortedEnd until it reached itself (next to sortedEnd)
			
			//broke out of loop when unsorted is larger than sortedEnd
			//just leave unsorted as it is and make it the new sortedEnd
			if(curr == unsorted) {
				sortedEnd = sortedEnd.next;
			}
			
			//either unsorted was inserted within sorted range or it was larger than all sorted
			//so just leave it and increment by 1 to continue
			//the code above will update sortedEnd as needed so here just update unsorted for next element to sort
			unsorted = cut;
			
			//reset curr so next insertion starts again at head
			curr = list.head;
			prev = null;
		}
	}
}
