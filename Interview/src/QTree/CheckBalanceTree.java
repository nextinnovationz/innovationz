package QTree;

import DSTree.BinarySearchTree;
import DSTree.Node;

public class CheckBalanceTree {
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
		bst.insert(7);
		bst.printLineByLine();
		
		System.out.println("isBalanced: " + isBalanced(bst));
		System.out.println("isBalanced: " + isBalancedSlow(bst));
		System.out.println("isBalanced: " + isBalancedEasy(bst));
	}
	
	public static boolean isBalanced(BinarySearchTree<Integer> bst) {
		if(bst == null || bst.size == 0)
			return true;
		
		return isBalanced(bst.root) != -1;
	}
	
	public static int isBalanced(Node<Integer> curr) {
		if(curr == null)
			return 0;
		
		int leftHeight = isBalanced(curr.left);
		if(leftHeight == -1)
			return -1;
		
		int rightHeight = isBalanced(curr.right);
		if(rightHeight == -1)
			return -1;
		
		if(leftHeight - rightHeight > 1 || leftHeight-rightHeight < -1) {
			System.out.println("node " + curr.data + " is not balanced");
			return -1;
		}
		return Math.max(leftHeight, rightHeight) + 1;
	}
	
	public static boolean isBalancedSlow(BinarySearchTree<Integer> bst) {
		if(bst == null || bst.size == 0)
			return true;
		
		return isBalancedSlow(bst.root);
	}
	
	public static boolean isBalancedSlow(Node<Integer> curr) {
		if(curr == null)
			return true;
		
		boolean leftBal = isBalancedSlow(curr.left);
		boolean rightBal = isBalancedSlow(curr.right);
		
		if(!leftBal || !rightBal)
			return false;
		
		int leftHeight = height(curr.left);
		int rightHeight = height(curr.right);
		
		if(leftHeight - rightHeight > 1 || leftHeight - rightHeight < -1) {
			System.out.println("node " + curr.data + " is not balanced");
			return false;
		}
			
		return true;
	}
	
	public static int height(Node<Integer> curr) {
		if(curr == null)
			return 0;
		
		return Math.max(height(curr.left), height(curr.right)) + 1;
	}
	
	public static boolean isBalancedEasy(BinarySearchTree<Integer> bst) {
		if(bst == null || bst.size == 0)
			return true;
		
		return height(bst.root) - minHeight(bst.root) < 2;
	}
	
	public static int minHeight(Node<Integer> curr) {
		if(curr == null)
			return 0;
		
		return Math.min(height(curr.left), height(curr.right)) + 1;
	}
}
