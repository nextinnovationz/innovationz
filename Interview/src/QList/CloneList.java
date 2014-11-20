package QList;

import DSList.Node;
import DSList.SinglyLinkedList;

public class CloneList {
	public static void main(String[] args) {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.insertBack(1);
		list.insertBack(2);
		list.insertBack(3);
		list.insertBack(4);
		
		int[] rand = {3,1,4,2};
		addRandomPointers(list, rand);
		printList(list);
		
		SinglyLinkedList<Integer> clone = clone(list);
		printList(clone);
		
		SinglyLinkedList<Integer> clone2 = cloneSimple(list);
		printList(clone2);
	}
	
	public static SinglyLinkedList<Integer> cloneSimple(SinglyLinkedList<Integer> list) {
		SinglyLinkedList<Integer> cloneList = new SinglyLinkedList<Integer>();
		Node<Integer> curr = list.head;
		while(curr != null) {
			cloneList.insertBack(curr.data);
			curr = curr.next;
		}
		
		curr = list.head;
		Node<Integer> clone = cloneList.head;
		Node<Integer> countOrig = list.head;
		Node<Integer> countClone = cloneList.head;
		int randIdx = 0;
		while(curr != null && clone != null) {
			//fix next pointer for clone
			//find idx of orig's next node
			while(countOrig != curr.next) {
				countOrig = countOrig.next;
				++randIdx;
			}
			countOrig = list.head;
			//find the next node in clone list based on idx count
			while(randIdx > 0) {
				countClone = countClone.next;
				--randIdx;
			}
			//connect clone' next node 
			clone.next = countClone;
			countClone = cloneList.head;
			randIdx = 0;
			
			//fix random pointer for clone
			//find the idx of orig's random node
			while(countOrig != curr.random) {
				countOrig = countOrig.next;
				++randIdx;
			}
			countOrig = list.head;
			//find the random node in clone list based on idx count
			while(randIdx > 0) {
				countClone = countClone.next;
				--randIdx;
			}
			//connect clone's random node
			clone.random = countClone;
			countClone = cloneList.head;
			randIdx = 0;
			
			//advance to fix next clone node based on next curr node
			curr = curr.next;
			clone = clone.next;
		}
		
		return cloneList;
	}
	
	public static void printList(SinglyLinkedList<Integer> list) {
		Node<Integer> curr = list.head;
		while(curr != null) {
			if(curr.next != null)
				System.out.println("curr: " + curr.data + "\tnext: " + curr.next.data + "\trand: " + curr.random.data);
			else
				System.out.println("curr: " + curr.data + "\tnext: null\trand: " + curr.random.data);
			curr = curr.next;
		}
		System.out.println();
	}
	
	public static void addRandomPointers(SinglyLinkedList<Integer> list, int[] randIdx) {
		Node<Integer> curr = list.head, randNode = curr;
		for(int i: randIdx) {
			while(i > 1) {
				randNode = randNode.next;
				--i;
			}
			curr.random = randNode;
			curr = curr.next;
			randNode = list.head;
		}
	}
	
	public static SinglyLinkedList<Integer> clone(SinglyLinkedList<Integer> list) {
		SinglyLinkedList<Integer> cloneList = new SinglyLinkedList<Integer>();
		Node<Integer> curr = list.head;
		Node<Integer> next = null;
		
		//cloning each node
		while(curr != null) {
			//save curr's original next node
			next = curr.next;
			
			//create a new node as curr's new next
			//clone all curr's content into curr's new next (except for random pointer)
			//because the random pointer can point forward to a node without a clone so it will connect with original list which can't be detached later
			//need to traverse whole list again to update random pointer in each node
			curr.next = new Node<Integer>(curr.data);
			
			//connect curr's old next as curr's new next's next so list is connected again
			curr.next.next = next;
			
			//proceed to curr's old next which is curr's new next's next
			curr = curr.next.next;
		}
		
		//reset curr to original list head again (now with 2x length due to clone nodes)
		curr = list.head;
		while(curr != null && curr.next.next != null) {
			//add the random pointer to each clone node to point to the appropriate clone nodes
			curr.next.random = curr.random.next;
			
			//go to next orig node so next iteration will fix its clone's random pointer
			curr = curr.next.next;
		}
		
		//remove clone list from original list
		//curr is head of orig
		curr = list.head;
		
		//clone is head of clone (1 step ahead)
		Node<Integer> clone = curr;
		cloneList.head = clone;
		
		//keep detaching while curr is not null in original list and
		//there is still a clone node to detach
		while(curr != null && clone != null) {
			//connect curr's old next back to its next
			curr.next = clone.next;
			//move curr for next iteration
			curr = curr.next;
						
			//connect clone's next to next clone node (skip original node in middle)
			clone.next = clone.next.next;
			//move clone for next iteration
			clone = clone.next;
		}
		
		//fix original list's tail since it was previous pointing to the last clone node
		curr.next = null;
		
		return cloneList;
	}
}
