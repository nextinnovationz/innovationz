package QTree;

import java.util.ArrayList;

import DSTree.BinarySearchTree;
import DSTree.Node;

public class FindSumPath {
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
		bst.insert(16);
		bst.insert(17);
		bst.insert(18);
		bst.printLineByLine();
		int sum = 35;
		//printPath(findSumCompletePath(bst, sum));
		findAllSumPaths(bst, sum);
	}
	
	/*
	public static ArrayList<Node<Integer>> findSumPath(BinarySearchTree<Integer> bst, int sum) {
		if(bst == null)
			return null;
					
		ArrayList<Node<Integer>> path = new ArrayList<Node<Integer>>();
		findSumPath(bst.root, sum, 0, path);
		if(pathSum(path) == sum)
			return path;
		return null;
	}
	
	public static ArrayList<Node<Integer>> findSumPath(Node<Integer> curr, int sum, int currSum, ArrayList<Node<Integer>> path) {
		//found a matching path so base case reached
		//path can end anywhere as long as sum is reached
		if(currSum == sum) {
			printPath(path);
			return path;
		}
		
		//must try left and right if they are not null 
		//because in BST curr itself could be larger than sum or if included larger than sum
		//and a smaller element could be on its left so path may still exist
		//a path doesn't have to go from root to a leaf
		//however, when skipping curr, it must start a new path at curr.left, curr.right
		//because cannot have a path with a skipped node
		//like finding a path in two different trees
		
		//just skip curr if it doesn't fit in path
		if(curr.data > sum || currSum + curr.data > sum) {
			System.out.println("skipping: " + curr.data);
			ArrayList<Node<Integer>> left = new ArrayList<Node<Integer>>(), right = new ArrayList<Node<Integer>>();
			if(curr.left != null) {
				left = findSumPath(curr.left, sum, 0, left);
			}
			
			//only try going right if left can't find a path
			if(pathSum(left) != sum && curr.right != null) {
				right = findSumPath(curr.right, sum, 0, right);
			}
			
			//since passing in new arrays for left and right, cannot use nulls to distinguish success
			//need to actually sum the paths for comparison
			if(pathSum(left) == sum) {
				return left;
			} else if(pathSum(right) == sum){
				return right;
			} else {  //excluding this node, can't find path, so just return 
				return path;
			}
			
		} else {  //include curr if it fits in path
			currSum += curr.data;
			path.add(curr);
			
			ArrayList<Node<Integer>> left = null, right = null;
			if(curr.left != null) {
				left = findSumPath(curr.left, sum, currSum, path);
			}
			
			//only try going right if left can't find a path
			if(left == null && curr.right != null) {
				right = findSumPath(curr.right, sum, currSum, path);
			}
			
			if(left == null && right == null) {  //left and right recursions didn't find path
				//undo all the additions when returning backup so higher up can do backtracking
				currSum -= curr.data;
				path.remove(path.size()-1);
				return path;
			} else if(left == null) {  //left recursion found a path 
				return right;
			} else {  //right recursion found a path
				return left;
			}	
		}
	}
	*/
	
	public static ArrayList<Node<Integer>> findSumCompletePath(BinarySearchTree<Integer> bst, int sum) {
		if(bst == null)
			return null;
					
		ArrayList<Node<Integer>> path = new ArrayList<Node<Integer>>();
		findSumCompletePath(bst.root, sum, 0, path);
		if(pathSum(path) == sum)
			return path;
		return null;
	}
	
	
	public static ArrayList<Node<Integer>> findSumCompletePath(Node<Integer> curr, int sum, int currSum, ArrayList<Node<Integer>> path) {
		//System.out.println("\tcurr: " + curr.data + "\tcurrSum: " + currSum);
		//found a matching path so base case reached
		//path can end anywhere as long as sum is reached
		if(currSum == sum) {
			printPath(path);
			return path;
		}
		
		currSum += curr.data;
		path.add(curr);
	
		if(curr.left != null) {
			findSumCompletePath(curr.left, sum, currSum, path);
		} 
		
		if(curr.right != null) {
			findSumCompletePath(curr.right, sum, currSum, path);
		}
		
		currSum -= curr.data;
		path.remove(path.size()-1);
		return path;
	}
	
	
	public static int pathSum(ArrayList<Node<Integer>> path) {
		int sum = 0;
		for(Node<Integer> n: path) {
			sum += n.data;
		}
		return sum;
	}
	
	public static void printPath(ArrayList<Node<Integer>> path) {
		if(path == null) {
			System.out.print("no path");
			return;
		}
		
		for(Node<Integer> n: path) {
			System.out.print(n.data + " ");
		}
		System.out.println();
	}
	
	public static void printPath(ArrayList<Node<Integer>> path, int start, int end) {
		if(path == null || end < start) {
			System.out.print("no path");
			return;
		}
		
		for(int i = start; i < end; ++i) {
			System.out.print(path.get(i).data + "  ");
		}
		System.out.println();
	}
	
	public static void findAllSumPaths(BinarySearchTree<Integer> bst, int sum) {
		if(bst == null)
			return;
					
		ArrayList<Node<Integer>> path = new ArrayList<Node<Integer>>();
		findAllSumPaths(bst.root, sum, path);
	}
	
	public static void findAllSumPaths(Node<Integer> curr, int sum, ArrayList<Node<Integer>> path) {		
		//System.out.println("\ncurr: " + curr.data);
		
		//add current node to path
		path.add(curr);
		
		//check from curr node up to root if any subpaths exist that adds up to the given sum
		//print all subpaths that exist
		int temp = 0;
		for(int i = path.size()-1; i >= 0; --i) {
			//System.out.println("\tchecking sum: " + temp);
			temp += path.get(i).data;
			if(temp == sum) {
				//System.out.println("\t\tprinting path");
				printPath(path, i, path.size());
			}
		} 
		
		//go left and right to find more paths
		if(curr.left != null)
			findAllSumPaths(curr.left, sum, path);
		if(curr.right != null) 
			findAllSumPaths(curr.right, sum, path);
		
		//done with current node, so remove everything the current node contributed to 
		//go back upward to find more paths in other branches
		path.remove(path.size()-1);
	}
}
