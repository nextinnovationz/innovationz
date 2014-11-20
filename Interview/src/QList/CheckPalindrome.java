package QList;

import java.util.Stack;

import DSList.Node;
import DSList.SinglyLinkedList;
import Exception.InvalidInputException;

public class CheckPalindrome {
	public static void main(String[] args) {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.insertBack(5);
		list.insertBack(4);
		list.insertBack(2);
		list.insertBack(4);
		list.insertBack(5);
		try {
			System.out.println("palindrome: " + isPalindromeRecursive(list));
			System.out.println("palindrome: " + isPalindromeBetter(list));
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SinglyLinkedList<Integer> list2 = new SinglyLinkedList<Integer>();
		list2.insertBack(5);
		list2.insertBack(4);
		list2.insertBack(4);
		list2.insertBack(5);
		try {
			System.out.println("palindrome: " + isPalindromeRecursive(list2));
			System.out.println("palindrome: " + isPalindromeBetter(list2));
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SinglyLinkedList<Integer> list3 = new SinglyLinkedList<Integer>();
		list3.insertBack(5);
		list3.insertBack(4);
		list3.insertBack(3);
		list3.insertBack(5);
		try {
			System.out.println("palindrome: " + isPalindromeRecursive(list3));
			System.out.println("palindrome: " + isPalindromeBetter(list3));
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SinglyLinkedList<Integer> list4 = new SinglyLinkedList<Integer>();
		list4.insertBack(5);
		list4.insertBack(4);
		list4.insertBack(3);
		list4.insertBack(4);
		list4.insertBack(8);
		try {
			System.out.println("palindrome: " + isPalindromeRecursive(list4));
			System.out.println("palindrome: " + isPalindromeBetter(list4));
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean isPalindrome(SinglyLinkedList<Integer> list) throws InvalidInputException {
		if(list == null || list.size() == 0)
			throw new InvalidInputException("invalid linked list input");
		
		Stack<Integer> stack = new Stack<Integer>();
		Node<Integer> slow = list.head, fast = list.head;
		
		//used to determine even or odd length list
		//if it is odd, then don't match the last element since its the middle of an odd list
		int length = 0;
		
		//find middle of the list 
		while(fast != null) {
			fast = fast.next;
			if(fast != null) {
				fast = fast.next;
				slow = slow.next;
				++length;
			}
		}
		
		System.out.println("length: " + length);
		System.out.println("slow: " + slow.data);
		
		//from middle to the tail, add to stack
		while(slow != null) {
			stack.push(slow.data);
			slow = slow.next;
		}
		
		//reset to head
		slow = list.head;
		
		//don't match the middle in case list was odd
		if((length & 1) == 1) {
			//compare front half with end half 
			while(stack.size() > 1) {
				if(slow.data != stack.pop()) {
					return false;
				}
			}
		} else {
			//compare front half with end half 
			while(!stack.isEmpty()) {
				if(slow.data != stack.pop()) {
					return false;
				}
			}
		}
	
		return true;
	}
	
	public static boolean isPalindromeRecursive(SinglyLinkedList<Integer> list) throws InvalidInputException {
		if(list == null || list.size() == 0)
			throw new InvalidInputException("invalid linked list input");
		
		return isPalindromeRecursive(list.head, list.head) != null;
	}
	
	public static Node<Integer> isPalindromeRecursive(Node<Integer> curr, Node<Integer> comp) {
		//let recursion go to null (after tail) and return 1st node in list
		if(curr.next == null) {
			//System.out.println("reached end of list, start returning and comparing");
			if(curr.data == comp.data)
				return comp.next;
			else
				return null;
		} 
		
		Node<Integer> ret = isPalindromeRecursive(curr.next, comp);
		
		//the return node should go forward to the middle at each return
		//check the data between front (comp) and back (curr) 
		if(ret != null && ret.data == curr.data) {
			//System.out.println("comparing " + ret.data + " vs. " + curr.data);
			return ret.next;
		} else {
			//System.out.println("found break point in palindrome");
			return null;
		}
	}
	
	public static Node<Integer> isPalindromeBetter(Node<Integer> slow, Node<Integer> fast) {
		//keep trying to find middle with slow and fast
		fast = fast.next;
		
		//if fast.next is null, just leave it it will be caught below 
		//to compare slow (same as previous level) and slow.next (fast) -> two nodes in list so check them
		//this will make middle of even list point to 2nd middle instead of 1st
		if(fast != null) { 
			fast = fast.next;
			slow = slow.next;
		}
		
		//determine list length based on location of fast relative to slow
		if(fast == null) {  //even length
			//System.out.println("even length");
			//slow is at 2nd middle and fast if null
			//so return 2nd middle up so it can be compared with 1st middle
			//the ret below will catch the return in higher level and move slow to slow.next to continue comparing
			return slow;
		} else if(fast.next == null) {  //odd length
			//System.out.println("odd length");
			return slow.next;  //start comparing two halves from middle outward left and right
		}
		
		//if fast is not at the end, keep do finding middle recursively
		//ret is slow if list is even (to compare 2nd middle with 1st middle)
		//ret is slow.next if list is odd (only one middle so start comparing left and right of middle)
		Node<Integer> ret = isPalindromeBetter(slow, fast);  

		//only reach this piece in function when base case is reached so middle is found and returned upward
		if(ret != null && ret.data == slow.data) {  //matched, need to keep checking left and right
			//System.out.println("ret: " + ret.data + " vs. slow: " + slow.data);
			return ret.next;
		} else {  //don't match so not palindrome
			//System.out.println("ret: " + ret.data + " vs. slow: " + slow.data);
			return null;
		}
	}
	
	public static boolean isPalindromeBetter(SinglyLinkedList<Integer> list) {
		//this also includes when list only has one node which is palindrome 
		if(list == null || list.size() < 2)
			return true;
		
		//helper function will return null if not palindrome
		Node<Integer> ret = isPalindromeBetter(list.head, list.head);
		
		//the return node is always the last node in list (even and odd)
		//so compare last node and head node for final check if its not full
		return ret != null && ret.data == list.head.data;
	}
}
