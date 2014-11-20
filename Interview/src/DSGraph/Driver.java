package DSGraph;

import java.util.HashSet;
import java.util.Set;

public class Driver {
	public static void main(String[] args) {
		Set<Node<String>> nodes = new HashSet<Node<String>>();
		Node<String> A = new Node<String>("A");
		Node<String> B = new Node<String>("B");
		Node<String> C = new Node<String>("C");
		Node<String> D = new Node<String>("D");
		Node<String> E = new Node<String>("E");
		Node<String> F = new Node<String>("F");
		Node<String> G = new Node<String>("G");
		Node<String> H = new Node<String>("H");
		Node<String> I = new Node<String>("I");
	
		//int cost = 0;
		A.addNeighbor(B, 8);
		A.addNeighbor(F, 3);
		
		B.addNeighbor(C, 6);
		B.addNeighbor(D, 2);
		//B.addNeighbor(A, cost);
		
		F.addNeighbor(I, 1);
		F.addNeighbor(H, 8);
		//F.addNeighbor(A, cost);
		
		C.addNeighbor(G, 4);
		C.addNeighbor(E, 4);
		//C.addNeighbor(B, cost);
		
		//D.addNeighbor(B, cost);
		D.addNeighbor(E, 2);
		
		//E.addNeighbor(C, cost);
		//E.addNeighbor(D, cost);
		
		//G.addNeighbor(C, cost);
		//H.addNeighbor(F, cost);
		//I.addNeighbor(F, cost);
		
		nodes.add(A);
		nodes.add(B);
		nodes.add(C);
		nodes.add(D);
		nodes.add(E);
		nodes.add(F);
		nodes.add(G);
		nodes.add(H);
		nodes.add(I);
		
		Graph<String> g = new Graph<String>(nodes, A, true);
		g.depthFirstSearchIterative();
		g.resetAllNodes();
		g.depthFirstSearchRecursive();
		g.resetAllNodes();
		g.breadthFirstSearchIterative();
		g.resetAllNodes();
		g.topologicalSort();
		g.dijkstra();
		g.prim();
	}
}
