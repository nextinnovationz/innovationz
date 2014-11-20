package DSHeap;

public interface HeapOperations {
	public void heaplifyUp();
	public void heaplifyDown();
	public int extractRoot();
	public void insert(int i);
	public void buildHeap(int[] arr);
}
