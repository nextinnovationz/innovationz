package QTree;

import java.util.Stack;

import DSTree.BinarySearchTree;
import DSTree.Node;

public class FindKthNode {
	public static void main(String[] args) {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.insert(15);
		bst.insert(8);
		bst.insert(20);
		bst.insert(5);
		bst.insert(9);
		bst.insert(3);
		bst.insert(6);
		bst.insert(19);
		bst.insert(31);
		bst.printLineByLine();
		
		//actually 5th node since it starts at 0
		System.out.println("4th: " + findKByCountingNodes(bst, 4).data);  
		
		//actually 5th node since it starts at 0
		System.out.println("4th: " + findKByCountingK(bst, 4).data);
		
		//actually 5th node since it starts at 0 (k stops when k=0)
		System.out.println("4th: " + findKByCountingKIterative(bst, 4).data);  
	}
	
	public static Node<Integer> findKByCountingNodes(BinarySearchTree<Integer> bst, int k) {
		if(bst == null || k < 0 || k >= bst.size) 
			return null;
		
		Node<Integer> curr = bst.root;
		int leftCount = 0;
		
		//finding the kth node is actually finding a node in bst 
		//that has k nodes to its left so its actually the k+1th node
		while(leftCount != k) {
			leftCount = countNode(curr.left);
			if(k > leftCount) {  //look right
				curr = curr.right;
				k -= leftCount + 1;
			} else if(k < leftCount) {  //look left
				curr = curr.left;
			} else {  //curr is the kth node and its left subtree has k-1 nodes in it
				return curr;
			}
		}
		return null;
	}
	
	public static int countNode(Node<Integer> n) {
		if(n == null)
			return 0;
		return countNode(n.left) + countNode(n.right) + 1;
	}
	
	static class Counter {
		public int count;
		public Counter(int count) {
			this.count = count;
		}
	}
	
	//recursive inorder traversal to count k 
	public static Node<Integer> findKByCountingK(BinarySearchTree<Integer> bst, int k) {
		if(bst == null || k < 0 || k >= bst.size) 
			return null;
		return findKByCountingK(bst.root, new Counter(k));
	}
	
	public static Node<Integer> findKByCountingK(Node<Integer> curr, Counter k) {
		Node<Integer> target = null;
		
		//try finding kth in left subtree
		if(curr.left != null)
			target = findKByCountingK(curr.left, k);
		
		//k reached 0 so current node is kth 
		//not null since left and right subtrees are traversed with they are not null
		if(k.count == 0) { 
			target = curr;
		} 
		
		--k.count;
		
		//left is not kth so its null and k is not 0 so target is still null
		//and right not null so try find kth in right subtree
		if(target == null && curr.right != null)
			target = findKByCountingK(curr.right, k);
		
		return target;
	}
	
	//iterative inorder traversal to count k
	public static Node<Integer> findKByCountingKIterative(BinarySearchTree<Integer> bst, int k) {
		if(bst == null || k < 0 || k >= bst.size) 
			return null;
		
		Stack<Node<Integer>> visit = new Stack<Node<Integer>>();
		
		Node<Integer> curr = bst.root;
		while(curr.left != null) {  //push curr if it has a left child for L
			visit.push(curr);
			curr = curr.left;
		}
		
		//need to push last leftmost node since curr.left is null and broke loop, curr is not null
		visit.push(curr);
		
		while(!visit.isEmpty()) {
			curr = visit.pop();  //pop element for N
			
			if(k == 0) {  //if count is k then return curr
				return curr;
			}
			
			--k;  //count is not k so keep going inorder
			
			if(curr.right != null) {  //N has a right child so push right R
				visit.push(curr.right);  
				curr = curr.right;
				while(curr.left != null) {  //from the right child see if it has left and keep pushing until no more left
					visit.push(curr.left);
					curr = curr.left;
				}
			}
		}
		
		return null;
	}
}
