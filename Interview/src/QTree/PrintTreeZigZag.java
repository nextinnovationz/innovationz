package QTree;

import java.util.Stack;

import DSTree.BinarySearchTree;
import DSTree.Node;

public class PrintTreeZigZag {
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
		
		printZigZag(bst);
	}
	
	public static void printZigZag(BinarySearchTree<Integer> bst) {
		Stack<Node<Integer>> odd = new Stack<Node<Integer>>();
		Stack<Node<Integer>> even = new Stack<Node<Integer>>();
		odd.push(bst.root);
		Node<Integer> pop = null;
		
		while(!odd.isEmpty() || !even.isEmpty()) {
			if(odd.isEmpty()) {  //on even level, need to prepare nodes for next odd level
				while(!even.isEmpty()) {
					pop = even.pop();
					System.out.print(pop.data + "  ");
					
					//push right to left so it prints left to right
					if(pop.right != null)
						odd.push(pop.right);
					if(pop.left != null)
						odd.push(pop.left);
				}
			} else {   //on odd level, need to prepare nodes for next even level
				while(!odd.isEmpty()) { 
					pop = odd.pop();
					System.out.print(pop.data + "  ");
					
					//push left to right so it prints right to left
					if(pop.left != null)
						even.push(pop.left);
					if(pop.right != null)
						even.push(pop.right);
				}
			}
			System.out.println();
		}
	}
}
