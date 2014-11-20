package DSHashtable;

public class Bucket<K,V> {
	K key;
	V value;
	boolean valid;
	
	public Bucket(K key, V value) {
		this(key, value, false);
	}
	
	public Bucket(K key, V value, boolean valid) {
		this.key = key;
		this.value = value;
		this.valid = valid;
	}
}
