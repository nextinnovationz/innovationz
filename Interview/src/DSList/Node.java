package DSList;

public class Node<T> {
	public T data;
	public Node<T> prev, next, random;
	
	public Node(T data) {
		this.data = data;
		this.prev = null;
		this.next = null;
		this.random = null;
	}
}
