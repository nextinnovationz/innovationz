package DSHeap;

public class MinHeap extends Heap{

	@Override
	public void heaplifyUp() {
		int curr = size, parent = curr/2;
		while(parent > 0 && heap[curr] < heap[parent]) {
			swap(curr, parent);
			curr = parent;
			parent /= 2;
		}
	}

	@Override
	public void heaplifyDown() {
		int parent = 1, left = 2*parent, right = 2*parent+1, smaller = 0;
		
		//check parent with left and right child to heaplify down
		//swap parent with smallest of left and right children if both exist
		//otherwise just swap it with the smaller child 
		//keep looping until parent is smallest
		while(heap[parent] > heap[left] || heap[parent] > heap[right]) {
			System.out.println("parent: " + parent + "\tleft: " + left + " right: " + right);
			if(left < size && right < size) {
				//compare parent to smaller of left and right child if both exist
				if(heap[left] > heap[right]) {
					smaller = right;
				} else {
					smaller = left;
				}
				
				if(heap[parent] > heap[smaller]) {
					swap(parent, smaller);
					parent = smaller;
				} else {
					break;
				}
			} else if(left < size) {  //only left child exist
				if(heap[left] < heap[parent]) {
					swap(parent, left);
					parent = left;
				}
			} else if(right < size) {  //only right child exist
				if(heap[right] < heap[parent]) {
					swap(parent, right);
					parent = right;
				}
			} else {  //no more children
				break;
			}
			
			left = 2*parent;
			right = 2*parent+1;
		}
	}

	@Override
	public int extractRoot() {
		if(size > 1) {
			int root = heap[1];
			heap[1] = heap[size-1];
			--size;
			heaplifyDown();
			return root;
		}
		return -1;
	}

	@Override
	public void insert(int i) {
		if(size >= capacity) {
			regrow();
		}
		heap[size] = i;
		heaplifyUp();
		++size;
	}

	@Override
	public void buildHeap(int[] arr) {
		for(int i : arr) {
			insert(i);
		}
	}
}
