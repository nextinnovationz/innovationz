package QTree;

import java.util.Stack;

import DSTree.BinarySearchTree;
import DSTree.Node;

public class CheckSubtrees {
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
		
		BinarySearchTree<Integer> bst2 = new BinarySearchTree<Integer>();
		bst2.insert(15);
		bst2.insert(8);
		bst2.insert(22); 
		bst2.insert(5);
		bst2.insert(9);
		bst2.insert(3);
		bst2.insert(6);
		
		System.out.println("isSubtree: " + isSubtree(bst, bst2));
	}
	
	public static boolean isSubtree(BinarySearchTree<Integer> subA, BinarySearchTree<Integer> subB) {
		if(subA == null && subB == null)
			return true;
		
		return isSubtree(subA.root, subB.root);
	}
	
	public static boolean isSubtree(Node<Integer> currA, Node<Integer> rootB) {
		//rootB is null so no need to match anymore
		if(currA != null && rootB == null)
			return true;
		
		//can't find rootB in subA
		if(currA == null && rootB != null)
			return false;
		
		//used to short circuit the last statement
		//if both nodes matched and both subtrees matched, then no need to try left or right of remaining tree
		boolean match = false;
		
		//both not null so go check data
		if(currA != null && rootB != null) {
			//found a node in subA that match root of subB, check subtree
			if(currA.data == rootB.data) {
				match = subtreeMatch(currA.left, rootB.left) && subtreeMatch(currA.right, rootB.right);
			} 
		} else {  //both null so match
			return true;
		}
		
		//try going left or right in subA and see if either may contain subB's root to start checking
		return match || isSubtree(currA.left, rootB) || isSubtree(currA.right, rootB);
	}	
	
	public static boolean subtreeMatch(Node<Integer> subA, Node<Integer> subB) {	
		//subB is at a not null, but subA is at a null, then no match 
		//because subA don't have that node 
		if(subA == null && subB != null)
			return false;
		
		//subB is null, but subA is not null, match
		//subB don't need that node since the other parts may still match completely
		if(subA != null && subB == null)
			return true;
		
		//null match null
		if(subA == null && subB == null)
			return true;
		
		//both nodes are not null so must match their data
		if(subA != null && subB != null) {
			//match so keep trying to match left and right of both subtrees
			if(subA.data == subB.data) {
				return subtreeMatch(subA.left, subB.left) && subtreeMatch(subA.right, subB.right);
			} else {  //nodes don't matched
				return false;
			}
		}
	
		//any other case don't match
		return false;
	}
	
	/*
	public static boolean isSubtreeIterative(Node<Integer> subA, Node<Integer> subB) {
		Stack<Node<Integer>> s = new Stack<Node<Integer>>();
		Node<Integer> curr = subA;
		int stop = 0;
		s.push(curr);
		
		while(!s.isEmpty()) {
			curr = s.pop();
			if(curr.data == subB.data) {
				stop = s.size();
				do {
					curr = s.pop();
					if(curr.data != subB.data) 
						break;
					
					if(curr.right != null)
						s.push(curr.right);
					if(curr.left != null)
						s.push(curr.left);
					
				} while(s.size() > stop);
			}
		}
	}
	*/
}
