package DSCircularBuffer;

public class RingBuffer<T> {
	public T[] store;
	public int size, capacity, head, tail;
	public static final int INITIAL_CAPACITY = 10;
	
	public RingBuffer() {
		this(INITIAL_CAPACITY);
	}
	
	@SuppressWarnings("unchecked")
	public RingBuffer(int init) {
		this.store = (T[]) new Object[init];
		this.size = 0;
		this.capacity = 0;
		this.head = 0;
		this.tail = 0;
	}
	
	public void add(T element) {
		if(size == capacity) 
			regrow();

		if(tail < capacity) {  //still has space in [head, arr.length)
			store[tail] = element;
		} else {  //no space so wrap to front of array to add
			tail = 0;
			store[tail] = element;
		}
		++size;
		++tail;  //points to next free space
	}
	
	public T removeHead() {
		if(size > 0) {
			T del = store[head];  //remove current head 
			++head;  //make next element the new head
			if(head == capacity) {  //wrap head around if it was the last element
				head = 0;
			}
			--size;
			return del;
		}
		return null;
	}
	
	public T removeTail() {
		if(size > 0) {
			if(tail-1 < 0) {  //if tail decremented to <0 index, then put it at the end of store (it was wrapped)
				tail = capacity-1;
			} else {  //tail points to free space, decrement it to get last element  
				--tail;  
			}
			//tail now points to free space since last tail was removed so next element can just overwrite it
			T del = store[tail];  //remove element at tail
			--size;
			return del;
		}
		return null;
	}
	
	public void regrow() {
		capacity = capacity * 2 + 1;
		T[] temp = (T[]) new Object[capacity];
		int len = store.length;
		int t = 0;
		
		//regrow will make head start at 0 and tail end at old capacity
		if(head < tail) {
			for(int i = head; i < tail; ++i) {
				temp[t] = store[i];
				++t;
			}
		} else {
			for(int i = head; i < len; ++i) {
				temp[t] = store[i];
				++t;
			}
			for(int j = 0; j < tail; ++j) {
				temp[t] = store[j];
				++t;
			}
		}
		
		store = temp;
		temp = null;
		
		head = 0;
		tail = t;
	}
	
	public void printBuffer() {
		int curr = head;
		if(curr > tail) {
			while(curr < capacity) {  //print [head, length)
				System.out.println(store[curr] + " ");
				++curr;
			}
			curr = 0;
			while(curr < tail) {  //print [0, tail]
				System.out.println(store[curr] + " ");
				++curr;
			}
		} else {
			while(curr < tail) {
				System.out.println(store[curr] + " ");
				++curr;
			}
		}
	}
}
