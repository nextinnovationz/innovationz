package DSTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Queue;
import java.util.Stack;

public class BinarySearchTree<T extends Comparable<T>> {
	public Node<T> root;
	public int size;
	
	public BinarySearchTree() {
		this.root = null;
		this.size = 0;
	}
	
	public BinarySearchTree(T[] data) {
		this.root = null;
		this.size = 0;
		
		for(T i: data) {
			insert(i);
		}
	}
	
	public void insert(T data) {
		Node<T> n = new Node<T>(data);
		if(size == 0) {
			root = n;
		} else {
			Node<T> curr = root;
			while(curr != null) {
				if(curr.data.compareTo(data) == 1) {
					if(curr.left == null) {
						curr.left = n;
						n.parent = curr;
						break;
					} else {
						curr = curr.left;
					}
				} else {
					if(curr.right == null) {
						curr.right = n;
						n.parent = curr;
						break;
					} else {
						curr = curr.right;
					}
				}
			}
		}
		++size;
	}
	
	public Node<T> getNode(T data) {
		if(size == 0)
			return null;
		return getNode(root, data);
	}
	
	public Node<T> getNode(Node<T> curr, T data) {
		if(curr == null)
			return null;
		
		if(curr.data.compareTo(data) == 1)
			return getNode(curr.left, data);
		else if(curr.data.compareTo(data) == -1)
			return getNode(curr.right, data);
		else 
			return curr;
	}
	
	public void printLineByLine() {
		Node<T> curr = root;
		
		if(curr == null) {
			System.out.println("empty tree");
			return;
		}
		
		int currLvl = 1, nextLvl = 0;
		Queue<Node<T>> q = new ArrayDeque<Node<T>>();
		q.add(curr);
		
		while(!q.isEmpty()) {
			while(currLvl > 0) {
				curr = q.remove();
				System.out.print(curr.data + "  ");
				
				if(curr.left != null) {
					q.add(curr.left);
					++nextLvl;
				}
				if(curr.right != null) {
					q.add(curr.right);
					++nextLvl;
				}
				
				--currLvl;
			}
			
			System.out.println();
			currLvl = nextLvl;
			nextLvl = 0;
		}
		System.out.println();
	}
	
	public Node<T> getParent(T data) {
		if(data == null)
			return null;
		
		return getParentHelper(data, root);
	}
	
	public Node<T> getParentHelper(T data, Node<T> n) {
		//stop at leaf so this function will never call on null node as argument
		//the fact that this call is at a leaf node means that it is not the target 
		//so its parent is also not the result (this path fails)
		if(n.left == null && n.right == null) {
			return null;
		}
		
		Node<T> left = null, right = null;
		
		//the following will check the children to see if it match the data
		//if so, the current node can be returned as parent so it is not necessary to go any further
		
		//check left subtree
		if(n.left != null) {
			if(n.left.data == data) {
				return n;
			} else {
				left = getParentHelper(data, n.left);
			}
		}
		
		//check right subtree
		if(n.right != null) {
			if(n.right.data == data) {
				return n;
			} else {
				right = getParentHelper(data, n.right);
			}	
		}
		
		//return the parent node if it is not null which means it failed
		if(left == null)
			return right;
		else
			return left;
	}
	
	//not 0th index based counting
	public Node<T> getKthNode(int k) {
		if(root == null)
			return root;
		return getKthNodeHelper(root, new Counter(k));
	}

	public Node<T> getKthNodeHelper(Node<T> n, Counter cnt) {
		Node<T> target = null;
		if(n.left != null)
			target = getKthNodeHelper(n.left, cnt);
		
		//base case, stop here 
		if(cnt.count == 1)
			target = n;

		--cnt.count;
		
		//if target was not null, so this level's n is the kth node, then no need to recurse the right subtree
		//in case the left subtree returned the result so current count is not 1, then target != null to avoid going to right
		if(target == null && n.right != null)
			target = getKthNodeHelper(n.right, cnt);
		
		return target;
	}
	
	//private class to store counter (like pass by reference so it is global in recursion)
	private class Counter {
		private int count;
		private Counter(int c) {
			this.count = c;
		}
	}
	
	public Node<T> getMedian() {
		return getKthNode(size/2+1);  //add 1 to compensate for 1-index based counting
	}
	
	public void inorderTraversalRecursive() {
		inorderTraversalRecursive(root);
		System.out.println();
	}
	
	public void inorderTraversalRecursive(Node<T> n) {
		if(n == null)
			return;
		if(n.left != null)
			inorderTraversalRecursive(n.left);
		System.out.print(n.data + "  ");
		if(n.right != null)
			inorderTraversalRecursive(n.right);
	}
	
	public void inorderTraversalIterative() {
		Stack<Node<T>> s = new Stack<Node<T>>();
		Node<T> curr = root;
		
		while(curr != null) {
			//keep going left until null -> L
			s.push(curr);
			curr = curr.left;
			
			//reached leaf -> NR
			while(curr == null && !s.isEmpty()) {
				curr = s.pop(); //go back one level
				System.out.print(curr.data + "  ");
				curr = curr.right;  //try going right now
			}
		}
		
		System.out.println();
	}
	
	public void preorderTraversalRecursive() {
		preorderTraversalRecursive(root);
		System.out.println();
	}
	
	public void preorderTraversalRecursive(Node<T> n) {
		if(n == null)
			return;
		System.out.print(n.data + "  ");
		if(n.left != null)
			preorderTraversalRecursive(n.left);
		if(n.right != null)
			preorderTraversalRecursive(n.right);
	}
	
	public void preorderTraversalIterative() {
		Stack<Node<T>> s = new Stack<Node<T>>();
		s.push(root);
		Node<T> curr = null;
		while(!s.isEmpty()) {
			curr = s.pop();
			System.out.print(curr.data + "  ");
			if(curr.right != null)
				s.push(curr.right);
			if(curr.left != null)
				s.push(curr.left);
		}
		System.out.println();
	}
	
	public void postorderTraversalRecursive() {
		postorderTraversalRecursive(root);
		System.out.println();
	}
	
	public void postorderTraversalRecursive(Node<T> n) {
		if(n == null)
			return;
		if(n.left != null)
			postorderTraversalRecursive(n.left);
		if(n.right != null)
			postorderTraversalRecursive(n.right);
		System.out.print(n.data + "  ");
	}
	
	public void postorderTraversalIterative() {
		Stack<Node<T>> s1 = new Stack<Node<T>>();
		Stack<Node<T>> s2 = new Stack<Node<T>>();
		Node<T> curr = root;
		s1.push(root);
		while(!s1.isEmpty()) {
			curr = s1.pop();
			s2.push(curr);
			if(curr.left != null)
				s1.push(curr.left);
			if(curr.right != null)
				s1.push(curr.right);
		}
		
		while(!s2.isEmpty()) {
			System.out.print(s2.pop().data + "  ");
		}
		
		System.out.println();
	}
	
	public Stack<Node<T>> getMaxPath() {
		if(root == null) 
			return null;
		
		int maxLen = getHeight(root) + 1;
		Stack<Node<T>> path = new Stack<Node<T>>();
		return getMaxPath(root, path, maxLen);
	}
	
	public Stack<Node<T>> getMaxPath(Node<T> n, Stack<Node<T>> path, int maxLen) {
		//don't add null, just return previous path
		if(n == null)
			return path;
		
		//always add current node since its not null
		path.push(n);
		
		//check if base case is reached
		if(path.size() == maxLen) 
			return path;
		
		//not base case so go to left subtree 
		Stack<Node<T>> left = getMaxPath(n.left, path, maxLen);
		Stack<Node<T>> right = null;
		
		//path should be untouched if it is max, otherwise remove the current node (last node in path)
		//if left subtree don't have the max path, try the right subtree
		//otherwise, just return the max path without going right
		if(left.size() < maxLen) {
			right = getMaxPath(n.right, path, maxLen);
		} else {
			return left;
		}
		
		//check if the right subtree has the max path, if it does, just return it untouched upward
		if(right.size() == maxLen) {
			return right;
		}
		
		//if neither left nor right has maxpath, then just remove current node from current path and return upward
		//the nodes upward will try other paths going down if they exist
		path.pop();
		return path;
	}
	
	public int getHeight() {
		if(root == null)
			return -1;
		return getHeight(root);
	}
	
	public int getHeight(Node<T> n) {
		if(n == null)
			return -1;
		return Math.max(getHeight(n.left), getHeight(n.right)) + 1;
	}
	
	public void printPath(Collection<Node<T>> c) {
		if(c == null || c.size() == 0)
			return;
		
		for(Node<T> n: c) 
			System.out.print(n.data + "  ");
		
		System.out.println();
	}
	
	public boolean isBalanced() {
		if(root == null)
			return true;
		return isBalanced(root);
	}
	
	public boolean isBalanced(Node<T> n) {
		if(n == null)
			return true;
		
		int diff = getHeight(n.left) - getHeight(n.right);
		if(diff > -2 && diff < 2)
			return isBalanced(n.left) && isBalanced(n.right);
		return false;
	}
	
	public int getMaxHeight() {
		return getHeight(root);
	}
	
	public int getMinHeight() {
		if(root == null)
			return -1;
		return getMinHeight(root);
	}
	
	public int getMinHeight(Node<T> n) {
		if(n == null)
			return -1;
		return Math.min(getMinHeight(n.left), getMinHeight(n.right)) + 1;
	}
	
	public boolean isBalancedByMaxMin() {
		if(root == null)
			return true;
		
		int diff = getMaxHeight() - getMinHeight();
		return (diff < 2 && diff > -2);
	}
	
	public int getLeafCounts() {
		if(root == null)
			return 0;
		return getLeafCounts(root);
	}
	
	public int getLeafCounts(Node<T> n) {
		if(n == null)  
			return 0;
		
		if(n.left == null && n.right == null)
			return 1;
		
		return getLeafCounts(n.left) + getLeafCounts(n.right);
	}
	
	public void buildBalancedBST(T[] arr) {
		if(arr == null || arr.length == 0)
			return;

		root = buildBalancedBST(arr, 0, arr.length-1);
	}
	
	public Node<T> buildBalancedBST(T[] arr, int start, int end) {
		if(start > end || end < start)  //out of arr index bounds so return null children for caller to connect
			return null;
		
		int mid = (start + end)/2;
		Node<T> parent = new Node<T>(arr[mid]);
		++size;
		parent.left = buildBalancedBST(arr, start, mid-1);
		parent.right = buildBalancedBST(arr, mid+1, end);
		return parent;  //always return the current node upward for connection with parent
	}
	
	public int getDiameter() {
		if(root == null)
			return 0;
		return getDiameter(root);
	}
	
	public int getDiameter(Node<T> n) {
		if(n == null)
			return 0;
		
		//does diameter include current n?
		//check by finding max diameter from left subtree and right subtree
		//then compare max with the one including the n connecting both left and right subtree and take the longest 
		return Math.max(Math.max(getDiameter(n.left), getDiameter(n.right)), getHeight(n.left) + getHeight(n.right) + 1);
	}
	
	public Node<T> getLowestCommonAncestor(Node<T> a, Node<T> b) {
		if(a == null || b == null || root == null || root.left == null && root.right == null)
			return null;
		
		Node<T> curr = root;
		while(curr != null) {
			if(curr.data.compareTo(a.data) == 1 && curr.data.compareTo(b.data) == 1) {
				curr = curr.left;
			} else if(curr.data.compareTo(a.data) == -1 && curr.data.compareTo(b.data) == -1) {
				curr = curr.right;
			} else {
				return curr;
			}
		}
		
		return null;
	}
		
	public boolean isSubtree(Node<T> a, Node<T> b) {
		if(a == null && b == null || a != null && b == null) {
			return true;
		}
		
		//both are non null nodes so check their data
		if(a != null && b != null) {
			//found b's root in a
			if(a.data == b.data) {
				//from this node, try to match subtrees
				System.out.println("found b's root in a: (" + a.data + "," + b.data + ")");
				return match(a.left, b.left) && match(a.right, b.right);
			} else {
				//don't match so continue preorder traversal on a with isSubtree()
				//a match could be found in left or right subtrees (recursively)
				return isSubtree(a.left, b) || isSubtree(a.right, b);
			}
		}
		
		//cannot find b's root in a that matched subtrees
		return false;
	}
	
	public boolean match(Node<T> a, Node<T> b) {
		//both a and b reached a null node so it must match
		//or a is not null, but b is null 
		if(a == null && b == null || a != null && b == null) {
			return true;
		}
		
		//both are non null nodes so check their data
		if(a != null && b != null) {
			//current node match
			if(a.data == b.data) {
				//continue to match left and right nodes of the two subtrees
				System.out.println("matching: (" + a.data + "," + b.data + ")");
				return match(a.left, b.left) && match(a.right, b.right);
			} else 
				//mismatched nodes so this subtree is not b
				return false;
		}
		
		//a or b is null so don't match 
		return false;
	}
	
	public ArrayList<Node<T>> findMaxSumPath() {
		return null;
	}
}
