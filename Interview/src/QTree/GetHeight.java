package QTree;

import DSTree.BinarySearchTree;
import DSTree.Node;

public class GetHeight {
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
		
		System.out.println("height by edge: " + getHeightByEdge(bst.root));
		System.out.println("height by node: " + getHeightByNode(bst.root));
	}
	
	public static int getHeightByEdge(Node<Integer> curr) {
		//no node so -1 height
		//if 1 node, then after return max(-1,-1)+1 = 0 edge
		if(curr == null)
			return -1;
		
		//height is the max(left height, right height)+1 (including self)
		return Math.max(getHeightByEdge(curr.left), getHeightByEdge(curr.right)) + 1;
	}
	
	public static int getHeightByNode(Node<Integer> curr) {
		//no node so 0 height
		//if 1 node, then after return max(0,0)+1 = 1 node
		if(curr == null)
			return 0;
		
		//height is the max(left height, right height)+1 (including self)
		return Math.max(getHeightByNode(curr.left), getHeightByNode(curr.right)) + 1;
	}
}
