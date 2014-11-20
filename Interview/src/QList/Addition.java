package QList;

import DSList.Node;
import DSList.SinglyLinkedList;

public class Addition {
	public static void main(String[] args) {
		/*
		 * 1234
		 * 39689
		 * 40923
		 */
		int a = 1234;
		int b = 39689;
		SinglyLinkedList<Integer> aList = convertNumberToList(a);
		SinglyLinkedList<Integer> bList = convertNumberToList(b);
		SinglyLinkedList<Integer> sumList = add(aList, bList);
		sumList.print();
	}
	
	public static SinglyLinkedList<Integer> convertNumberToList(int num) {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		while(num > 0) {  //convert num to list, but in reverse order
			list.insertBack(num%10);
			num /= 10;
		}
		return list;
	}
	
	public static SinglyLinkedList<Integer> add(SinglyLinkedList<Integer> numA, SinglyLinkedList<Integer> numB) {
		SinglyLinkedList<Integer> sum = new SinglyLinkedList<Integer>();
		int carry = 0;
		Node<Integer> currA = numA.head, currB = numB.head;
		while(currA != null && currB != null) {  //add until either a or b ends first if one is shorter
			sum.insertBack((carry + currA.data + currB.data)%10);
			carry = (carry + currA.data + currB.data)/10;
			currA = currA.next;
			currB = currB.next;
		}
		
		while(currA != null) {  //if a is longer, continue adding a to its carry until done
			sum.insertBack((carry + currA.data)%10);
			carry = (carry + currA.data)/10;
			currA = currA.next;
		}
		
		while(currB != null) {  //if b is longer, continue adding b to its carry until done
			sum.insertBack((carry + currB.data)%10);
			carry = (carry + currB.data)/10;
			currB = currB.next;
		}
		
		return sum;
	}
}
