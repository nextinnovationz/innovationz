package QTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import DSTree.BinarySearchTree;
import DSTree.Node;

public class TreeLevelLists {
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
		
		printTreeLevelLists(treeLevelLists(bst));
	}
	
	public static void printTreeLevelLists(ArrayList<LinkedList<Node<Integer>>> lists) {
		Iterator<LinkedList<Node<Integer>>> listitr = lists.iterator();
		while(listitr.hasNext()) {
			Iterator<Node<Integer>> nodeitr = listitr.next().iterator();
			while(nodeitr.hasNext()) {
				System.out.print(nodeitr.next().data + " -> ");
			}
			System.out.print("//");
			System.out.println();
		}
	}
	
	public static ArrayList<LinkedList<Node<Integer>>> treeLevelLists(BinarySearchTree<Integer> bst) {
		ArrayList<LinkedList<Node<Integer>>> treeList = new ArrayList<LinkedList<Node<Integer>>>();
		Node<Integer> curr = bst.root;
		Queue<Node<Integer>> q = new ArrayDeque<Node<Integer>>();
		q.add(curr);
		
		int currLvlCnt = 1, nxtLvlCnt = 0;
		
		while(!q.isEmpty()) {
			//curr level linked list
			LinkedList<Node<Integer>> currList = new LinkedList<Node<Integer>>();
			treeList.add(currList);
			
			//handle current level nodes
			while(currLvlCnt > 0) {
				curr = q.remove();
				
				//add popped node to curr level list
				currList.add(curr);
				
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
		
		return treeList;
	}
}
