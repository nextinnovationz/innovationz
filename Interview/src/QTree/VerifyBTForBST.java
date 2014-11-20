package QTree;

import DSTree.BinarySearchTree;
import DSTree.Node;

public class VerifyBTForBST {
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
		
		System.out.println("isBST: " + isBST(bst));
	}
	
	public static boolean isBST(BinarySearchTree<Integer> bt) {
		if(bt == null)
			return true;
		
		int max = Integer.MAX_VALUE;
		int min = Integer.MIN_VALUE;
		
		return isBST(bt.root, min, max);
	}
	
	//use preorder traversal to check
	public static boolean isBST(Node<Integer> curr, int min, int max) {
		//null is always BST
		if(curr == null)
			return true;
		
		//violates BST property
		if(curr.data < min || curr.data > max)
			return false;
					
		//update min and max for left and right
		return isBST(curr.left, min, curr.data) && isBST(curr.right, curr.data, max);
	}
}
