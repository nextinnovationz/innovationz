package DSTree;

public class Node<T extends Comparable<T>> {
	public T data;
	public Node<T> left, right, parent;
	
	public Node(T data) {
		this.data = data;
		this.left = null;
		this.right = null;
		this.parent = null;
	}
}
