package DSHashtable;

import java.util.LinkedList;

public class ExternalChainHashtable<K,V> implements Operations<K,V> {
	public int filledBuckets;
	public int totalBuckets;
	public final static int INITIAL_SIZE = 10;
	public final static double LOAD_FACTOR = 0.75;
	public LinkedList<Bucket<K,V>>[] table;
	
	public ExternalChainHashtable() {
		this(INITIAL_SIZE);
	}
	
	public ExternalChainHashtable(int initial) {
		this.table = (LinkedList<Bucket<K,V>>[])(new Object[initial]);
		this.filledBuckets = 0;
		this.totalBuckets = 0;
	}

	@Override
	public void add(K key, V value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(K key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public V find(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void regrow() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(K key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		
	}
}
