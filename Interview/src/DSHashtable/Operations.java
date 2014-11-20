package DSHashtable;

public interface Operations<K,V> {
	public void add(K key, V value);
	public void remove(K key);
	public V find(K key);
	public void regrow();
	public boolean contains(K key);
	public void print();
}
