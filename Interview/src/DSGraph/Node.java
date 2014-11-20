package DSGraph;

import java.util.HashMap;

public class Node<T> {
	public T data;
	public HashMap<Node<T>, Integer> neighbors;
	public int cost;
	public boolean visited;
	public int pre, post;
	public Node<T> previous;
	
	public Node(T data) {
		this(data, new HashMap<Node<T>, Integer>(), 0, false);
	}
	
	public Node(T data, HashMap<Node<T>, Integer> neighbors, int cost, boolean visited) {
		this.data = data;
		this.neighbors = neighbors;
		this.cost = cost;
		this.visited = visited;
		this.pre = 0;
		this.post = 0;
		this.previous = null;
	}
	
	public void addNeighbor(Node<T> n, int cost) {
		neighbors.put(n, cost);
	}
}
