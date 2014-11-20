package QTree;

import DSTree.BinarySearchTree;
import DSTree.Node;

public class BuildBSTFromPreAndInorder {
	public static void main(String[] args) {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.insert(20);
		bst.insert(10);
		bst.insert(25);
		bst.insert(3);
		bst.insert(15);
		bst.insert(21);
		bst.insert(32);
		
		int[] preorder = {20,10,3,15,25,21,32};
		int[] inorder = {3,10,15,20,21,25,32};
		BinarySearchTree<Integer> bst2 = build(preorder, inorder);
		bst2.printLineByLine();
		bst2.preorderTraversalRecursive();
		bst2.inorderTraversalRecursive();
	}
	
	public static BinarySearchTree<Integer> build(int[] preorder, int[] inorder) {
		if(preorder == null || inorder == null || preorder.length != inorder.length)
			return null;
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.root = build(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1);
		return bst;
	}
	
	public static Node<Integer> build(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
		//preorder NLR
		//inorder LNR
		
		if(preStart < 0 || preStart >= preorder.length ||)
			return null;
		
		int preRoot = preorder[preStart];
		int inLeft = inStart, inRight = inStart;
		for(; inRight < inEnd; ++inRight) {
			if(inorder[inRight] == preRoot) {
				break;
			}
		}
		
		int preLeft = preStart+1, preRight = preLeft + (inRight-inLeft-1) - 1;
		
		Node<Integer> parent = new Node<Integer>(preRoot);
		System.out.println("curr: " + parent.data);
		parent.left = build(preorder, preLeft, preRight, inorder, inLeft, inRight);
		parent.right = build(preorder, preRight+1, preEnd, inorder, inRight+1, inEnd);
		return parent;
	}
}
