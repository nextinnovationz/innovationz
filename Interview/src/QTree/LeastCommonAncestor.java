package QTree;

import java.util.Stack;

import DSTree.BinarySearchTree;
import DSTree.Node;

public class LeastCommonAncestor {
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
		
		//find LCA or FCA in BST
		System.out.println("LCA: " + getLCABST(bst, bst.getNode(3), bst.getNode(6)).data);
		
		//find LCA or FCA in BT
		System.out.println("LCA: " + getLCABTWithParentPointers(bst, bst.getNode(3), bst.getNode(6)).data);
		System.out.println("LCA: " + getLCABTWithoutParentPointers(bst, bst.getNode(3), bst.getNode(6)).data);
		System.out.println("LCA: " + getLCABTInplace(bst, bst.getNode(3), bst.getNode(6)).data);
	}
	
	public static Node<Integer> getLCABST(BinarySearchTree<Integer> bst, Node<Integer> a, Node<Integer> b) {
		Node<Integer> curr = bst.root;
		
		//find curr that is between a and b for LCA
		while(curr != null) {
			if(curr.data > a.data && curr.data > b.data) {
				curr = curr.left;
			} else if(curr.data < a.data && curr.data < b.data) {
				curr = curr.right;
			} else {  //curr is between a and b so its LCA
				return curr;
			}
		}
		return null;
	}
	
	public static Node<Integer> getLCABTWithParentPointers(BinarySearchTree<Integer> bst, Node<Integer> a, Node<Integer> b) {
		Node<Integer> root = bst.root, currA = a, currB = b;
		int alen = 0, blen = 0;
		
		while(a != null && a != root) {
			a = a.parent;
			++alen;
		}
		
		while(b != null && b != root) {
			b = b.parent;
			++blen;
		}
		
		if(alen > blen) {
			int i = 0, diff = alen-blen;
			while(i < diff && a != null) {
				a = a.parent;
				++i;
			}
		} else if(alen < blen) {
			int i = 0, diff = blen-alen;
			while(i < diff && b != null) {
				b = b.parent;
				++i;
			}
		} 
		
		//restart a and b at their original nodes using their copies
		while(currA != null && currB != null && currA != currB) {
			currA = currA.parent;
			currB = currB.parent;
		}
		
		//make sure the the loop broke due to finding LCA and not because of a null
		if(currA != null && currB != null && currA == currB) {
			return currA; 
		}
		
		return null;
	}
	
	public static Node<Integer> getLCABTWithoutParentPointers(BinarySearchTree<Integer> bst, Node<Integer> a, Node<Integer> b) {
		//build root to a and root to b paths using DFS (preorder traversal) on the tree
		Stack<Node<Integer>> pathA = depthFirstSearch(bst.root, a, new Stack<Node<Integer>>());
		Stack<Node<Integer>> pathB = depthFirstSearch(bst.root, b, new Stack<Node<Integer>>());
		
		//either a or b don't exist in tree so path don't have it
		if((!pathA.isEmpty() && pathA.peek() != a) || (!pathB.isEmpty() && pathB.peek() != b))
			return null;
	
		//find last node that pathA and pathB shares before diverging (root to a,b)
		//or find first node that pathA and pathB shares (a,b to root)
		int alen = pathA.size(), blen = pathB.size();
		if(alen-blen > 0) {
			int diff = alen-blen;
			while(diff > 0) {
				pathA.pop();
			}
		} else if(blen-alen > 0) {
			int diff = blen-alen;
			while(diff > 0) {
				pathB.pop();
			}
		}
		
		while(!pathA.isEmpty() && !pathB.isEmpty() && pathA.peek() != pathB.peek()) {
			pathA.pop();
			pathB.pop();
		}
		
		//first shared node is on top of both paths
		if(!pathA.isEmpty() && !pathB.isEmpty())
			return pathA.peek();
		
		return null;
	}
	
	public static Stack<Node<Integer>> depthFirstSearch(Node<Integer> curr, Node<Integer> target, Stack<Node<Integer>> path) {
		//cannot find target in current path since it reached null
		if(curr == null)
			return path;
		
		//always add curr node to end of path at each nonnull level
		path.add(curr);
		
		//just return the current path once target is found on this level
		if(curr == target)
			return path;
		
		//no target found yet, so keep searching left
		Stack<Node<Integer>> leftResult = depthFirstSearch(curr.left, target, path);
		
		//check if target was on this path, if so done and return path
		if(leftResult.peek() == target)
			return leftResult;
		
		//target was not in left path so try searching right
		Stack<Node<Integer>> rightResult = depthFirstSearch(curr.right, target, path);
		
		//check if target was on this path, if so done and return path
		if(rightResult.peek() == target)
			return rightResult;
		
		//target not found in left nor right paths
		//remove last added node (curr on this level and return up to backtrack)
		path.pop();
		return path;
	}
	
	public static Node<Integer> getLCABTInplace(BinarySearchTree<Integer> bst, Node<Integer> a, Node<Integer> b) {
		if(bst == null || bst.size == 0)
			return null;
		
		if(a == null || b == null)
			return null;
		
		return getLCABTInplace(bst.root, a, b);
	}
	
	public static Node<Integer> getLCABTInplace(Node<Integer> curr, Node<Integer> a, Node<Integer> b) { 
		//try to determine which sides (subtree) the two nodes are in based on curr node
		boolean aInLeftSub = nodeInSubtree(curr.left, a);
		boolean bInLeftSub = nodeInSubtree(curr.left, b);
		
		//true false or false true
		//a and b are in different sides, so curr is lca
		if(aInLeftSub != bInLeftSub)
			return curr;
		
		//true true
		//both in left subtree so look left
		//false false
		//both in right subtree so look right
		if(aInLeftSub)
			return getLCABTInplace(curr.left, a, b);
		else 
			return getLCABTInplace(curr.right, a, b);
	}
	
	public static boolean nodeInSubtree(Node<Integer> sub, Node<Integer> target) {
		if(sub == null)
			return false;
		
		if(sub == target)
			return true;
		
		return nodeInSubtree(sub.left, target) || nodeInSubtree(sub.right, target);
	}
}
