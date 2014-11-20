package QTree;

import java.util.Stack;

import DSTree.BinarySearchTree;
import DSTree.Node;

public class Traversals {
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
		
		preorderIterative(bst);
		inorderIterative(bst);
	}
	
	public static void preorderIterative(BinarySearchTree<Integer> bst) {
		if(bst == null || bst.size == 0)
			return;
		
		Stack<Node<Integer>> s = new Stack<Node<Integer>>();
		Node<Integer> curr = bst.root;
		s.push(curr);
		
		while(!s.isEmpty()) {
			//visit curr as (N)
			curr = s.pop();
			System.out.print(curr.data + "  ");
			
			//go right to left so that it pops left to right (LR)
			if(curr.right != null)
				s.push(curr.right);
			
			if(curr.left != null)
				s.push(curr.left);
		}
		
		System.out.println();
	}
	
	public static void inorderIterative(BinarySearchTree<Integer> bst) {
		if(bst == null || bst.size == 0)
			return;
		
		Stack<Node<Integer>> s = new Stack<Node<Integer>>();
		Node<Integer> curr = bst.root;
		
		while(curr != null) {
			//keep going left until null
			s.push(curr);
			curr = curr.left;
			
			//no more left so enter loop
			while(curr == null && !s.isEmpty()) {
				curr = s.pop();
				
				//visit curr as (N)
				System.out.print(curr.data + "  ");
				
				//go to right now (R)
				//if right is null then this loop will continue up a level to find more rights
				//otherwise the loop will break and it will restart trying to find more left from this new curr
				curr = curr.right;
			}
		}
		
		System.out.println();
	}
	
	public static void postorderIterative() {
		
	}
}
