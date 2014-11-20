package QTree;

import DSTree.BinarySearchTree;
import DSTree.Node;

public class MirrorTrees {
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
		
		BinarySearchTree<Integer> mbst = mirror(bst);
		mbst.printLineByLine();
	}
	
	public static BinarySearchTree<Integer> mirror(BinarySearchTree<Integer> orig) {
		BinarySearchTree<Integer> mirror = new BinarySearchTree<Integer>();
		mirror.root = new Node<Integer>(orig.root.data);
		mirror(orig.root, mirror.root);
		return mirror;
	}
	
	public static void mirror(Node<Integer> orig, Node<Integer> mirr) {
		//if orig is null, ignore it and return upward
		if(orig == null)
			return;
		
		//if orig right has a node, clone it
		if(orig.right != null)
			mirr.left = new Node<Integer>(orig.right.data);
		
		//if orig left has a node, clone it
		if(orig.left != null)
			mirr.right = new Node<Integer>(orig.left.data);
		
		mirror(orig.left, mirr.right);
		mirror(orig.right, mirr.left);
	}
}
