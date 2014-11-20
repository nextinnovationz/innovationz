package QTree;

import DSTree.BinarySearchTree;
import DSTree.Node;

public class CountNodes {
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
		System.out.println("total nodes: " + countNodes(bst.root));
		System.out.println("total leaves: " + countLeaves(bst.root));
		System.out.println("total parents: " + countParents(bst.root));
	}
	
	public static int countNodes(Node<Integer> curr) {
		//null node so don't count
		if(curr == null)
			return 0;
		
		//count left, count right, include curr (+1)
		return countNodes(curr.left) + countNodes(curr.right) + 1;
	}
	
	public static int countLeaves(Node<Integer> curr) {
		//leaf node has not child 
		if(curr.left == null && curr.right == null) {
			System.out.println("\tleaf: " + curr.data);
			return 1;
		}
		
		//don't include curr because it is the parent of left and right so its not leaf
		return countLeaves(curr.left) + countLeaves(curr.right);
	}
	
	public static int countParents(Node<Integer> curr) {
		//leaf node is not a parent node so stop and return
		if(curr.left == null && curr.right == null) {
			return 0;
		} else {
			System.out.println("\tparent: " + curr.data);
		}
	
		//curr has left or right child so its a parent (+1)
		return countParents(curr.left) + countParents(curr.right) + 1;
	}
}
