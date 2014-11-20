package QTree;

import DSTree.BinarySearchTree;
import DSTree.Node;

public class FindDiameter {
	public static void main(String[] args) {
		
	}
	
	public static int diameter(BinarySearchTree<Integer> bst) {
		if(bst == null || bst.size == 0)
			return 0;
		return diameter(bst.root);
	}
	
	public static int diameter(Node<Integer> curr) {
		int leftHeight = height(curr.left);
		int rightHeight = height(curr.right);
		int leftDiameter = diameter(curr.left);
		int rightDiameter = diameter(curr.right);
		
		//diameter is longest leaf to leaf path in a tree
		//so the max height of left and max height of right subtrees (then decide if curr should be included)
		//leftHeight+rightHeight+1 = diameter including curr
		//leftDiameter = diameter of left subtree (excluding curr)
		//rightDiameter = diameter of right subtree (excluding curr)
		return Math.max(leftHeight+rightHeight+1, Math.max(leftDiameter, rightDiameter));
	}
	
	//node based height
	public static int height(Node<Integer> curr) {
		if(curr == null)
			return 0;
		
		//height of leftsubtree (excluding self)
		//height of rightsubtree (excluding self)
		//take the max of the two heights and add self to it (for height of curr)
		return Math.max(height(curr.left), height(curr.right)) + 1;
	}
}
