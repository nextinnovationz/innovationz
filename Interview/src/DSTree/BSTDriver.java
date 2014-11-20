package DSTree;

public class BSTDriver {
	public static void main(String[] args) {
		Integer[] treeA = {7, 4, 9, 3, 5, 8, 15, 20};
		Integer[] treeB = {8};
		BinarySearchTree<Integer> bstA = new BinarySearchTree<Integer>(treeA);
		BinarySearchTree<Integer> bstB = new BinarySearchTree<Integer>(treeB);
		//System.out.println("isSubtree: " + bstA.isSubtree(bstA.root, bstB.root));
		
		System.out.print("preorder recursive: ");
		bstA.preorderTraversalRecursive();
		System.out.print("preorder iterative: ");
		bstA.preorderTraversalIterative();
		
		System.out.print("inorder recursive: ");
		bstA.inorderTraversalRecursive();
		System.out.print("inorder iterative: ");
		bstA.inorderTraversalIterative();
		
		System.out.print("postorder recursive: ");
		bstA.postorderTraversalRecursive();
		System.out.print("postorder iterative: ");
		bstA.postorderTraversalIterative();
		
		/*
		//Integer[] arr = {16, 3, 5, 7, 9, 2, 1, 8, 4, 27};
		//Integer[] arr = {15, 8, 17, 3, 9, 25, 46, 1};
		//Integer[] arr = {7, 4, 9, 3, 5, 8, 15};
		//BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>(arr);
		
		Integer[] arr = {1, 3, 6, 7, 9, 10, 14, 21, 25};
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.buildBalancedBST(arr);
		bst.printLineByLine();
		System.out.println("bst size: " + bst.size);
		
		//System.out.println("parent(27): " + bst.getParent(27).data);
		//System.out.println("parent(4): " + bst.getParent(4).data);
		//System.out.println("parent(15): " + bst.getParent(15).data);
		
		//System.out.println("get 4th node: " + bst.getKthNode(4).data);
		//System.out.println("get 7th node: " + bst.getKthNode(7).data);
		
		System.out.println("get median node: " + bst.getMedian().data);
		
		System.out.println("inorder: ");
		bst.inorderTraversal();
		
		bst.printPath(bst.getMaxPath());
		
		System.out.println("isBalanced: " + bst.isBalanced());
		System.out.println("isBalanced: " + bst.isBalancedByMaxMin());
		System.out.println("lowest common ancestor: " + bst.getLowestCommonAncestor(new Node<Integer>(10), new Node<Integer>(25)).data);
		
		System.out.println("leaf counts: " + bst.getLeafCounts());
		System.out.println("diameter: " + bst.getDiameter());
		System.out.println("height: " + bst.getHeight());
		*/
	}
}
