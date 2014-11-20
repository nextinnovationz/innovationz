package QTree;

import java.util.ArrayDeque;
import java.util.Queue;

import DSTree.BinarySearchTree;
import DSTree.Node;

public class PrintLineByLine {
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
		
		printLineByLine(bst);
	}
	
	public static void printLineByLine(BinarySearchTree<Integer> bst) {
		Node<Integer> curr = bst.root;
		Queue<Node<Integer>> q = new ArrayDeque<Node<Integer>>();
		q.add(curr);
		
		int currLvlCnt = 1, nxtLvlCnt = 0;
		
		while(!q.isEmpty()) {
			//handle current level nodes
			while(currLvlCnt > 0) {
				curr = q.remove();
				System.out.print(curr.data + "  ");
				
				if(curr.left != null) {
					q.add(curr.left);
					++nxtLvlCnt;
				} 
				
				if(curr.right != null) {
					q.add(curr.right);
					++nxtLvlCnt;
				}
				
				--currLvlCnt;
			}
			
			//swap curr and next level counts to move on to next level
			currLvlCnt = nxtLvlCnt;
			nxtLvlCnt = 0;
			System.out.println();
		}
	}
}
