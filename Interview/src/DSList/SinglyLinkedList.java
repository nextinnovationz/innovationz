package DSList;

public class SinglyLinkedList<T> {
	public Node<T> head, tail;
	public int length;
	
	public SinglyLinkedList() {
		this.head = null;
		this.tail = null;
		this.length = 0;
	}
	
	public boolean isEmpty() {
		return length == 0;
	}
	
	public int size() {
		return length;
	}
	
	public void insertBack(T data) {
		Node<T> n = new Node<T>(data);
		if(length == 0) {
			head = n;
			tail = n;
		} else {
			tail.next = n;
			tail = n;
		}
		++length;
	}
	
	public void insertFront(T data) {
		Node<T> n = new Node<T>(data);
		if(length == 0) {
			head = n;
			tail = n;
		} else {
			n.next = head;
			head = n;
		}
		++length;
	}

	public Node<T> remove(T data) {
		if(length > 0) {
			Node<T> curr = head, prev = head;
			while(curr != null) {
				if(curr.data == data) {
					if(curr == head) {  //remove head
						prev = head.next;
						head.next = null;
						head = prev;  //new head
					} else {  //remove middle or tail
						prev.next = curr.next;
						if(curr == tail) {  //new tail
							tail = curr.next;
						}
						curr.next = null;
					}
					--length;
					return curr;  //this curr still points to the deleted node
				}
				prev = curr;  //stay 1 step behind
				curr = curr.next;  //move 1 step forward
			}
		}
		return null;
	}
	
	public boolean find(T data) {
		if(length > 0) {
			Node curr = head;
			while(curr != null) {
				if(curr.data == data) {
					return true;
				}
				curr = curr.next;
			}
		}
		return false;
	}
	
	public Node<T> getNodeByData(T data) {
		if(length > 0) {
			Node<T> curr = head;
			while(curr != null && curr.data != data) {
				curr = curr.next;
			}
			return curr;
		}
		return null;
	}
	
	public Node<T> getNodeByIndex(int idx) {
		if(length > 0 && idx < length) {
			Node<T> curr = head;
			while(idx > 0) {
				curr = curr.next;
				--idx;
			}
			return curr;
		}
		return null;
	}
	
	public void print() {
		Node<T> curr = head;
		while(curr != null) {
			System.out.print(curr.data + " -> ");
			curr = curr.next;
		}
		System.out.print("//");
		System.out.println();
	}
	
	public void print(Node<T> start, Node<T> end) {
		Node<T> curr = start;
		while(curr != null && curr != end) {
			System.out.print(curr.data + " -> ");
		}
		if(curr != null)
			System.out.print(curr.data + " -> ");
		System.out.println();
	}
}

