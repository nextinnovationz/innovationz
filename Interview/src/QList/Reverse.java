package QList;

import java.util.Stack;

import DSList.Node;
import DSList.SinglyLinkedList;

public class Reverse {
	public static void main(String[] args) {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		
		/*
		for(int i = 0; i < 10; ++i) {
			list.insertBack(i);
		}
		*/
		
		/*
		// 698473
		list.insertFront(8);
		list.insertFront(9);
		list.insertBack(4);
		list.insertBack(7);
		list.insertFront(6);
		list.insertBack(3);
		
		reverse2(list);
		list.print();
		*/
		
		list.insertBack(1);
		list.insertBack(2);
		list.insertBack(3);
		list.insertBack(4);
		list.insertBack(5);
		list.insertBack(6);
		list.insertBack(7);
		list.insertBack(8);
		list.insertBack(9);
		list.print();
		//reverseGroup(list, 3);
		//reverseRecursive(list);
		//reverseIterativeStack(list);
		reverseGroupK(list, 3);
		list.print();
	}
	
	public static void reverse(SinglyLinkedList<Integer> list) {
		if(list.head != null && !list.isEmpty()) {
			Node<Integer> prev = null, curr = list.head, next = list.head.next;
			while(curr != null) {
				next = curr.next;  //save next
				curr.next = prev;  //set new next to prev (reverse)
				prev = curr;  //make curr as prev for next iteration
				curr = next;  //go to old next for next iteration
			}
			
			//use old tail as new head to fix the list structure to be usable
			if(curr == null) {
				list.head = prev;  //prev was the last node in the old list so curr was null
			}
		}
	}
	
	//same as above, but different order of operations to reverse
	public static void reverse2(SinglyLinkedList<Integer> list) {
		if(list.head != null && !list.isEmpty()) {
			Node<Integer> prev = null, curr = list.head, next = list.head.next;
			while(curr != null) {
				curr.next = prev;
				prev = curr;
				curr = next;
				if(next != null)
					next = next.next;
			}
			
			//use old tail as new head to fix the list structure to be usable
			if(curr == null) {
				list.head = prev;  //prev was the last node in the old list so curr was null
			}
		}
	}
	
	public static void reverseRecursive(SinglyLinkedList<Integer> list) {
		if(list == null || list.size() == 0)
			return;
		
		//set new head to old list's last node that gets return all the way up here
		//also pass in null as head's prev so that it can be the new tail of reversed list
		list.head = reverseRecursive(list.head, null);
	}
	
	public static Node<Integer> reverseRecursive(Node<Integer> curr, Node<Integer> prev) {
		if(curr.next == null) {  //last node, new head for reverse list
			curr.next = prev;  //reverse
			return curr;  //return new head all the way up
		} else {
			//pass in next node and curr node to recurse (curr node is used as prev node for next node)
			Node<Integer> newHead = reverseRecursive(curr.next, curr); 
			curr.next = prev;  //reverse
			return newHead;   //return new head (last node) when done at each level
		}
	}
	
	public static void reverseIterativeStack(SinglyLinkedList<Integer> list) {
		if(list == null || list.size() == 0)
			return;
		
		Stack<Node<Integer>> s = new Stack<Node<Integer>>();
		Node<Integer> curr = list.head;
		
		//push all nodes on stack 
		while(curr != null) {
			s.push(curr);
			curr = curr.next;
		}
		
		//new head is the old tail, pop it
		list.head = s.pop();
		curr = list.head;
		
		//set curr node's next to each pop (top of stack, curr's previous)
		while(!s.isEmpty()) {
			curr.next = s.pop();  //set curr (previous pop's next to its prev)
			curr = curr.next;  
		}
		
		//fix tail (old head's next points to null)
		curr.next = null;
	}
	
	public static Node<Integer> reverseK(Node<Integer> start, Node<Integer> end) {
		Node<Integer> prev = null, curr = start, next = curr.next;
		while(curr != end) {
			curr.next = prev;  //reverse curr->prev
			prev = curr;  //make curr as prev for the next iteration
			curr = next;  //set next curr 
		}
		
		//return old head of group
		return start;
	}
	
	public static void reverseGroupK(SinglyLinkedList<Integer> list, int k) {
		if(list == null || list.size() == 0)
			return;
		
		list.head = reverseGroupK(list.head, k);
	}
	
	public static Node<Integer> reverseGroupK(Node<Integer> head, int k) {
		Node<Integer> oldstart, cut, start = head, end = head;
		int cnt = k;
		
		while(start != null) {  //still has more nodes so more groups
			while(cnt >= 0 && end != null) {  //find end of next group of k or less nodes
				end = end.next;
				--cnt;
			}

			cnt = k;  //reset count for next group
			cut = end;  //head of next group (reverse group will stop at this node)
			oldstart = reverseK(start, end);  //return tail of previous reversed group
			if(oldstart == head) {  //update list head on 1st group reverse
				head = oldstart;
			}
			oldstart.next = cut;  //connect tail of previous reversed to head of next original
			start = cut;  //head of next group
			end = cut;  //head of next group
			
			print();
		}
		
		return head;
	}
	
	/*
	public static Node<Integer> reverse(SinglyLinkedList<Integer> list, Node<Integer> start, Node<Integer> stop) {
		//System.out.println("start: " + start.data + "\tstop: " + stop.data);
		
		//stop is the last node in this section of list to reverse
		Node<Integer> prev = stop, curr = start, next = start.next;
		while(curr != stop) {
			next = curr.next;  //save next
			curr.next = prev;  //set new next to prev (reverse)
			prev = curr;  //make curr as prev for next iteration
			curr = next;  //go to old next for next iteration
		}
		
		if(start == list.head) {  //reverse 1st group so update list head
			list.head = prev;
		}
		
		//returns reversed head
		return prev;
	}
	
	public static void reverseGroup(SinglyLinkedList<Integer> list, int groupSize) {
		Node<Integer> start = list.head, end = list.head, cut = null, preCut = null;
		int count = 1;
		while(end != null) {
			while(end != null && groupSize > count) {  //find stop node for this group
				end = end.next;
				++count;
			}
			count = 1;
			if(end != null) {
				cut = end.next;  //head of next group k
			} else {
				cut = end;
			}
					
			if(start != list.head)
				preCut = end;
			
			//connect previous group tail to current group head
			if(preCut != null)
				preCut.next = reverse(list, start, cut);
			else
				reverse(list, start, cut);
			
			end = cut;
			start = end;
		}
	}
	*/
}
