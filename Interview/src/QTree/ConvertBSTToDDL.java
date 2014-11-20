package QTree;

import DSTree.BinarySearchTree;
import DSTree.Node;

public class ConvertBSTToDDL {
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
		
		print(convertBSTToDDL(bst));
	}
	
	public static Node<Integer> convertBSTToDDL(BinarySearchTree<Integer> bst) {
		Node<Integer> n = bst.root;
		
		//keep rotating if root has left child
		while(n.left != null) {
			n = rotateRight(n);
		}
		
		//leftmost node has right rotated to new root of tree
		bst.root = n;
		n = n.right;  //root has no more left child
		
		while(n != null) {
			//rotate all reachable left child along this n
			while(n.left != null) {
				n = rotateRight(n);
			}
			
			//n has no more left child, go to right child now
			//may right child has some left child
			n = n.right;  
		}

		System.out.println("head: " + n.data);
		
		//now only a long right chain tree with right pointer (next pointer)
		//need to add left pointer (prev pointer) back to each node's direct parent to form DDL
		Node<Integer> prev = null;
		while(n != null) {
			n.left = prev;  //use left to point to parent (as prev pointer)
			prev = n;  //save current n as prev for next node
			n = n.right;  //go right child for next node
		}
		
		return bst.root;
	}
	
	public static Node<Integer> rotateRight(Node<Integer> n) {
		if(n == null)
			return null;
		
		Node<Integer> newRoot = n.left;
		n.left = newRoot.right;  //move newRoot's right to oldRoot's left
		newRoot.right = n;  //make newRoot's right as oldRoot
		return newRoot;
	}
	
	public static void print(Node<Integer> root) {
		Node<Integer> curr = root;
		while(curr != null) {
			System.out.println(curr.data);
			curr = curr.right;
		}
	}
}
