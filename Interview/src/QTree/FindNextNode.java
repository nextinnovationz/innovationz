package QTree;

import DSTree.BinarySearchTree;
import DSTree.Node;

public class FindNextNode {
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
		
		System.out.println("successor: " + getBSTSucessor(bst, bst.getNode(6)).data);
		System.out.println("successor: " + getBSTSucessor(bst, bst.getNode(8)).data);
		System.out.println("predecessor: " + getBSTPredecessor(bst, bst.getNode(19)).data);
		System.out.println("predecessor: " + getBSTPredecessor(bst, bst.getNode(5)).data);
	}
	
	public static Node<Integer> getBSTSucessor(BinarySearchTree<Integer> bst, Node<Integer> curr) {
		if(bst == null || bst.size == 0 || curr == null)
			return null;
		
		Node<Integer> self = curr;
		
		if(self.right != null) {  //have right child, so get leftmost node in right subtree
			self = self.right;
			
			//in right subtree, get leftmost node as successor
			while(self.left != null)
				self = self.left;
			
		} else {  //no right child, so find parent with self as left child or null
			//look upward from self via parent pointers
			//until it reach a self that is a left child
			//return its parent
			Node<Integer> parent = self.parent;
			while(parent != null && parent.left != self) {
				//System.out.println("self: " + self.data + "\tparent: " + parent.data);
				self = parent;
				parent = self.parent;
			}
			
			//the successor is parent of left self
			//return self outside so make self its parent for return later
			self = parent;
			
			//successor don't exist when the loop broke due to root's parent is null
			if(parent == null && self.left != curr)
				return null;
		}
		
		//either ancestor or descendant is successor based 
		return self;
	}
	
	public static Node<Integer> getBSTPredecessor(BinarySearchTree<Integer> bst, Node<Integer> curr) {
		if(bst == null || bst.size == 0 || curr == null)
			return null;
		
		Node<Integer> self = curr;
		if(self.left != null) {  //left child is not null, so predecessor is the rightmost node in left subtree
			self = self.left;
			while(self.right != null) 
				self = self.right;
			
		} else {  //left child is null, so predecessor is the parent of right child if it exist
			Node<Integer> parent = self.parent;
			while(parent != null && parent.right != self) {
				self = parent;
				parent = self.parent;
			}
			self = parent;
			
			if(parent == null && self.right != curr) 
				return null;
		}
		
		return self;
	}
}
