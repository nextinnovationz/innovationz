package DSGraph;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Graph<T> {
	public Set<Node<T>> adjList;
	public int size;
	public Node<T> start;
	public boolean directed;
	
	public Graph() {
		this(new HashSet<Node<T>>(), null, false);
	}
	
	public Graph(Set<Node<T>> adjList, Node<T> start, boolean directed) {
		this.adjList = adjList;
		this.size = 0;
		this.start = start;
		this.directed = directed;
	}
	
	public void depthFirstSearchRecursive() {
		if(start == null || adjList == null || adjList.size() == 0) {
			return;
		}
		depthFirstSearchRecursive(start);
		System.out.println();
	}
	
	public void depthFirstSearchRecursive(Node<T> curr) {
		System.out.print(curr.data + " ");
		
		//mark current node as visited to avoid revisiting (casuing loop in search)
		curr.visited = true;
		
		//no neighbors so return and try other paths (for directed graphs)
		//this base case don't work for undirected graphs (since each connected node will have at least 1 neighbor)
		//the base case for undirected graph is that all neigbhors has been visited already 
		//so for loop will break without any recursive calls
		if(curr.neighbors.size() == 0) {  
			return;
		}
	
		//visited each nonvisited neigbors recursively
		for(Node<T> n : curr.neighbors.keySet()) {
			if(!n.visited) {
				depthFirstSearchRecursive(n);
			}
		}
	}
	
	public void depthFirstSearchIterative() {
		if(start == null || adjList == null || adjList.size() == 0) {
			return;
		}
		
		Stack<Node<T>> s = new Stack<Node<T>>();
		s.push(start);
		start.visited = true;
		Node<T> curr;
		while(!s.isEmpty()) {
			curr = s.pop();
			System.out.print(curr.data + " ");
			for(Node<T> n : curr.neighbors.keySet()) {
				if(!n.visited) {
					n.visited = true;
					s.push(n);
				}
			}
		}
		System.out.println();
	}
	
	public void breadthFirstSearchIterative() {
		if(start == null || adjList == null || adjList.size() == 0) {
			return;
		}
		
		Queue<Node<T>> q = new ArrayDeque<Node<T>>();
		q.add(start);
		start.visited = true;
		Node<T> curr;
		while(!q.isEmpty()) {
			curr = q.remove();
			System.out.print(curr.data + " ");
			for(Node<T> n : curr.neighbors.keySet()) {
				if(!n.visited) {
					n.visited = true;
					q.add(n);
				}
			}
		}
		System.out.println();
	}
	
	public void topologicalSort() {
		if(start == null || adjList == null || adjList.size() == 0) {
			return;
		}
		
		//topological sort in reverse order
		//each node when it is assigned a post number is pushed to the stack
		Stack<Node<T>> topo = new Stack<Node<T>>();
		start.visited = true;
		topologicalSort(start, topo);
		
		//print topo stack by popping
		while(!topo.isEmpty()) {
			System.out.print(topo.pop().data + " ");
		}
		System.out.println();
	}
	
	public void topologicalSort(Node<T> curr, Stack<Node<T>> topo) {
		//go to each neighbor one at a time to explore more
		//keep exploring until a node don't have any neighbors 
		//this happens for DAG 
		for(Node<T> neigbhor: curr.neighbors.keySet()) {
			if(!neigbhor.visited) {
				neigbhor.visited = true;
				topologicalSort(neigbhor, topo);
			}
		}
		
		//done exploring all neigbhor paths
		//curr node is done visiting so assign post# (add to stack)
		topo.push(curr);
	}
	
	public void stronglyConnectedComponent() {
		if(start == null || adjList == null || adjList.size() == 0) {
			return;
		}
		
		//topological sort in reverse order
		//each node when it is assigned a post number is pushed to the stack
		Stack<Node<T>> topo = new Stack<Node<T>>();
		start.visited = true;
		topologicalSort(start, topo);
		
		//reverse all edges in G
		
	}
	
	//compare the distance cost between two nodes (used for PQ ordering)
	class NodeCostComparator implements Comparator<Node<T>> {
		@Override
		public int compare(Node<T> arg0, Node<T> arg1) {
			return arg0.cost-arg1.cost;
		}
	}
	
	public void dijkstra() {			
		//works with directed and undirected graphs
		
		//initialize all nodes's cost
		//start node has a cost of 0 (to itself) 
		//everything else has a cost of MAX
		for(Node<T> n : adjList) {
			n.cost = Integer.MAX_VALUE;
			n.previous = null;
		}
		start.cost = 0;
		
		//PQ to remove the min cost node at each round (node is done for sssp)
		PriorityQueue<Node<T>> pq = new PriorityQueue<Node<T>>(adjList.size(), new NodeCostComparator());
		
		//add all nodes to PQ 
		for(Node<T> n : adjList) {
			pq.add(n);
		}
		
		Node<T> done = null;
		
		while(!pq.isEmpty()) {
			//remove node with min cost from source
			done = pq.poll();
			
			System.out.println("removing " + done.data);

			//update all neighbor cost if using this node can reach source with lower cost
			for(Node<T> n : done.neighbors.keySet()) {
				System.out.println("\tchecking neighbor: " + n.data);
				
				//only try to update distance cost if neighbor is still in PQ
				//compare cost from (source ... to ... done ... to ... n) vs. (source ... to ... n)
				//see if going to n from source to done with the last edge from (done to n) is better
				//then just going from source to n without done in between them
				if(pq.contains(n) && n.cost > done.cost + done.neighbors.get(n)) {
					System.out.println("\t\told cost: " + n.cost);
					pq.remove(n);  //modification of nodes already in PQ will not update PQ, need to remove and readd with modification
					n.cost = done.cost + done.neighbors.get(n);
					n.previous = done;  //set n's previous to done (so taking edge(done,n))
					pq.add(n);  //add the node back into PQ will update it (otherwise, modification will not be reflected)
					System.out.println("\t\tnew lower cost: " + n.cost + "  prev: " + n.previous.data);
				}
			}
		}
		
		StringBuilder path = new StringBuilder();
		Node<T> prev = null;
		for(Node<T> n : adjList) {
			System.out.print("node: " + n.data + "  cost: " + n.cost + "  path: ");
			//keeping tracing backward using previous pointer in each node in path to 
			//construct node to source path in reverse order
			prev = n;
			while(prev != null) {
				path.append(prev.data);
				prev = prev.previous;
			}
			System.out.println(path.reverse().toString());
			path.setLength(0);
		}
	}
	
	public void prim() {
		//works with directed and undirected graphs
		
		//initialize all nodes's cost
		//start node has a cost of 0 (to itself) 
		//everything else has a cost of MAX
		for(Node<T> n : adjList) {
			n.cost = Integer.MAX_VALUE;
			n.previous = null;
		}
		start.cost = 0;
		
		//PQ to remove the min cost node at each round (node is done for sssp)
		PriorityQueue<Node<T>> pq = new PriorityQueue<Node<T>>(adjList.size(), new NodeCostComparator());
		
		//add all nodes to PQ 
		for(Node<T> n : adjList) {
			pq.add(n);
		}
		
		Node<T> done = null;
		
		while(!pq.isEmpty()) {
			//remove node with min cost from source
			done = pq.poll();
			
			System.out.println("removing " + done.data);

			//update all neighbor cost if using this node can reach source with lower cost
			for(Node<T> n : done.neighbors.keySet()) {
				System.out.println("\tchecking neighbor: " + n.data);
				
				//only similar to dijkstra except that update only uses the edge cost
				//not the entire path cost 
				if(pq.contains(n) && n.cost > done.neighbors.get(n)) {
					System.out.println("\t\told cost: " + n.cost);
					pq.remove(n);  //modification of nodes already in PQ will not update PQ, need to remove and readd with modification
					n.cost = done.neighbors.get(n);
					n.previous = done;  //set n's previous to done (so taking edge(done,n))
					pq.add(n);  //add the node back into PQ will update it (otherwise, modification will not be reflected)
					System.out.println("\t\tnew lower cost: " + n.cost + "  prev: " + n.previous.data);
				}
			}
		}
		
		//print out each edge taken by prim for MST
		for(Node<T> n : adjList) {
			if(n == start) {
				System.out.println("node: " + n.data + "  cost: " + n.cost + "  edge: (" + n.data + "," + n.data + ")");
			} else {
				System.out.println("node: " + n.data + "  cost: " + n.cost + "  edge: (" + n.data + "," + n.previous.data + ")");
			}
		}
	}
	
	public void kruskal() {
		
	}
	
	public void resetAllNodes() {
		for(Node<T> n : adjList) {
			n.visited = false;
		}
	}
}
