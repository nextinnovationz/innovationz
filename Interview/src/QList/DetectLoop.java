package QList;

import DSList.Node;
import DSList.SinglyLinkedList;

public class DetectLoop {
	public static void main(String[] args) {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.insertBack(1);
		list.insertBack(2);
		list.insertBack(3);
		list.insertBack(4);
		list.insertBack(5);
		list.getNodeByIndex(4).next = list.getNodeByIndex(2);
		System.out.println("has loop: " + hasLoop(list));
		System.out.println("loop start: " + findLoopStart(list).data);
		removeLoop(list);
		list.print();
	}
	
	public static boolean hasLoop(SinglyLinkedList<Integer> list) {
		Node<Integer> slow = list.head, fast = slow;
		//fast and slow points to same head so must use do-while loop 
		//to make fast and slow different after it run once
		do {  
			fast = fast.next;
			if(fast != null) {
				fast = fast.next;
				slow = slow.next;
			} else {  //fast reached null so it doesn't have a loop
				return false;
			}
		} while (fast != slow);
		
		//fast and slow eventually met after encountering loop so never reached null
		return true;
	}
	
	public static Node<Integer> findLoopStart(SinglyLinkedList<Integer> list) {
		Node<Integer> slow = list.head, fast = list.head;
		//fast and slow points to same head so must use do-while loop 
		//to make fast and slow different after it run once
		do {
			fast = fast.next;
			if(fast != null) {
				fast = fast.next;
				slow = slow.next;
				//System.out.println("fast: " + fast.data + "\tslow: " + slow.data);
			} else {  //fast reached null so it doesn't have a loop
				return null;
			}
		} while(fast != slow);

		//move slow to next so its different from fast
		slow = slow.next;
		int loopLen = 1;  //including self
		
		//count the number of nodes it takes for slow to meet with fast again
		//this is the loop length
		while(fast != slow) {
			slow = slow.next;
			++loopLen;
		}
		
		//reset both slow and fast to head
		fast = list.head;
		slow = list.head;
		
		//move fast forward by loop length
		while(loopLen > 0) {
			fast = fast.next;
			--loopLen;
		}
		
		//move fast and slow together by 1 each time
		//when they meet again, that node is the start of the loop
		while(fast != slow) {
			slow = slow.next;
			fast = fast.next;
		}
		
		return slow;
	}
	
	public static void removeLoop(SinglyLinkedList<Integer> list) {
		Node<Integer> slow = list.head, fast = slow;
		do {
			fast = fast.next;
			if(fast != null) {
				fast = fast.next;
				slow = slow.next;
				//System.out.println("fast: " + fast.data + "\tslow: " + slow.data);
			} else {  //fast reached null so it doesn't have a loop
				return;
			}
		} while(fast != slow);
		
		//move slow to next so its different from fast
		slow = slow.next;
		int loopLen = 1;  //including self
		
		//count the number of nodes it takes for slow to meet with fast again
		//this is the loop length
		while(fast != slow) {
			slow = slow.next;
			++loopLen;
		}
		
		//save loopLen to use later
		int temp = loopLen;
		
		//reset both slow and fast to head
		fast = list.head;
		slow = fast;
		
		//move fast forward by loop length
		while(loopLen > 0) {
			fast = fast.next;
			--loopLen;
		}
		
		//move fast and slow together by 1 each time
		//when they meet again, that node is the start of the loop
		while(fast != slow) {
			slow = slow.next;
			fast = fast.next;
		}

		//from start of loop, iterate loopLen-1 node to reach the prev node of that start of loop
		while(temp > 1) {
			slow = slow.next;
			--temp;
		}
		
		//set prev node's next to null so that it doesn't loop backward to form cycle
		slow.next = null;
	}
}

