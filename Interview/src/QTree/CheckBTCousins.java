package QTree;

import java.util.ArrayDeque;

import DSTree.BinarySearchTree;
import DSTree.Node;

public class CheckBTCousins {
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
		
		System.out.println(areCousins(bst.getNode(9), bst.getNode(19), bst));
		System.out.println(areCousins(bst.getNode(3), bst.getNode(6), bst));
		System.out.println(areCousins(bst.getNode(5), bst.getNode(31), bst));
		System.out.println(areCousins(bst.getNode(8), bst.getNode(20), bst));
	}
	
	public static boolean areCousins(Node<Integer> a, Node<Integer> b, BinarySearchTree<Integer> bst) {
		ArrayDeque<Node<Integer>> q = new ArrayDeque<Node<Integer>>();
		Node<Integer> curr = bst.root, parent = null;
		q.addFirst(curr);
		int currLvl = 1, nxtLvl = 0, foundLvl = 0, level = 0;
		
		while(!q.isEmpty()) {
			while(currLvl > 0) {
				curr = q.removeFirst();
				
				if(curr.left != null) {
					q.addLast(curr.left);
					++nxtLvl;
					
					//found a or b's parent (a or b is the left child of curr)
					if(curr.left == a || curr.left == b) {
						//if parent is null, then this is first node found so set its parent to curr
						if(parent == null) {
							parent = curr;
							
							//since curr's left child is a or b, then foundLvl is 1 level below current level
							foundLvl = level+1;
						} else {
							//so this second node found
							//check if a and b are on same level
							//check using first node's parent to determine if a and b share same parent
							return foundLvl == level+1 || parent.left == curr.left || parent.right == curr.left;
						}
					}
				} 
				
				if(curr.right != null) {
					q.addLast(curr.right);
					++nxtLvl;
					
					if(curr.right == a || curr.left == b) {
						if(parent == null) {
							parent = curr;
							foundLvl = level+1;
						} else {
							return foundLvl == level+1 || parent.left == curr.right || parent.right == curr.right;
						}
					}
				}
				
				--currLvl;
			}
			
			++level;
			currLvl = nxtLvl;
			nxtLvl = 0;
		}
		
		//didn't find the two nodes a and b in tree
		return false;
	}
}
