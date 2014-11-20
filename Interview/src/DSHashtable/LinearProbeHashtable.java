package DSHashtable;

public class LinearProbeHashtable<K,V> implements Operations<K,V> {
	public int filledBuckets;
	public int totalBuckets;
	public final static int INITIAL_SIZE = 10;
	public final static double LOAD_FACTOR = 0.75;
	public Bucket<K,V>[] table;
	
	public LinearProbeHashtable() {
		this(INITIAL_SIZE);
	}
	
	@SuppressWarnings("unchecked")
	public LinearProbeHashtable(int initial) {
		this.table = (Bucket<K,V>[])(new Object[initial]);
		this.filledBuckets = 0;
		this.totalBuckets = 0;
	}
	
	@Override
	public void add(K key, V value) {
		//check if the current table needs to be regrow
		if((double)filledBuckets/(double)totalBuckets >= 0.75) {
			regrow();
		}
		
		int index = (int)key.hashCode()%totalBuckets;
		if(table[index] == null) {  //index is empty so just add new bucket
			Bucket<K,V> b = new Bucket<K,V>(key, value, true);
			table[index] = b;
		} else {  //collision at index (key hashes to the same index)
			if(table[index].key == key) {  //same key, just replace value
				table[index].value = value;
				table[index].valid = true;
			} else {  //different key, need to handle collision
				//linear probe
				//increment index until it finds a free spot to insert
				//use %totalBuckets to wrap index around to the front
				//if it can't find a free spot before reaching end
				int i = index+1;
				while(i < totalBuckets && table[i] != null) {
					++i;
				}
				
				//no more free space from index+1 to end of table
				//wrap index to 0 and search to index-1
				if(i >= totalBuckets) {
					i = 0;
					while(i < index && table[i] != null) {
						++i;
					}
				}
				
				//i is the free space found from the search 
				Bucket<K,V> b = new Bucket<K,V>(key, value, true);
				table[i] = b; 
			}
		}
		++filledBuckets;
	}
	
	@Override
	public V find(K key) {
		int index = (int)key.hashCode()%totalBuckets;
		if(table[index].key == key && table[index].valid) {  //key at index match the search key
			return table[index].value;
		} else {  //it got collided so need to do linear search
			int i = index;
			++i;
			//search from index+1 to totalBuckets-1
			while(i < totalBuckets && table[i] != null && table[i].key != key) {
				++i;
			}
			if(i >= totalBuckets) {  //i when beyond the totalBuckets and still no match
				i = 0;
				//search from 0 to index-1
				while(i < index && table[i] != null && table[i].key != key) {
					++i;
				}
				if(i >= index) {  //still can't find match, so don't exist
					return null;
				}
				
				//i was still less than index and found a match
				return table[i].value;
			} 
			
			//i was still less than totalBuckets and found a match
			return table[i].value;
		}
	}
	
	@Override
	public void regrow() {
		//new size is 2*oldsize+1
		totalBuckets = (totalBuckets*2)+1;
		Bucket<K,V>[] temp = (Bucket<K,V>[])(new Object[(int)totalBuckets]);
		int oldSize = table.length;
		
		//rehash all elements in old table into new table using new size
		for(int i = 0; i < oldSize; ++i) {
			if(table[i] != null) {
				temp[table[i].key.hashCode()%totalBuckets] = table[i];
			}
		}
		
		table = temp;  //make table point to new table
	}

	@Override
	public void remove(K key) {
		int index = (int)key.hashCode()%totalBuckets;
		if(table[index].key == key && table[index].valid) {  //key at index match the search key
			table[index].valid = false;
		} else {  //it got collided so need to do linear search
			int i = index;
			++i;
			//search from index+1 to totalBuckets-1
			while(i < totalBuckets && table[i] != null && table[i].key != key) {
				++i;
			}
			if(i >= totalBuckets) {  //i when beyond the totalBuckets and still no match
				i = 0;
				//search from 0 to index-1
				while(i < index && table[i] != null && table[i].key != key) {
					++i;
				}
				if(i >= index) {  //still can't find match, so don't exist
					return;
				}
				
				//i was still less than index and found a match
				table[i].valid = false;
			} 
			
			//i was still less than totalBuckets and found a match
			table[i].valid = false;
		}
	}

	@Override
	public boolean contains(K key) {
		int index = (int)key.hashCode()%totalBuckets;
		if(table[index].key == key && table[index].valid) {  //key at index match the search key
			return true;
		} else {  //it got collided so need to do linear search
			int i = index;
			++i;
			//search from index+1 to totalBuckets-1
			while(i < totalBuckets && table[i] != null && table[i].key != key) {
				++i;
			}
			if(i >= totalBuckets) {  //i when beyond the totalBuckets and still no match
				i = 0;
				//search from 0 to index-1
				while(i < index && table[i] != null && table[i].key != key) {
					++i;
				}
				if(i >= index) {  //still can't find match, so don't exist
					return false;
				}
				
				//i was still less than index and found a match
				return true;
			} 
			
			//i was still less than totalBuckets and found a match
			return true;
		}
	}

	@Override
	public void print() {
		for(int i = 0; i < totalBuckets; ++i) {
			System.out.print("index: " + i);
			if(table[i] == null)
				System.out.print("\t(NULL,NULL)");
			else
				System.out.print("\t(" + table[i].key + "," + table[i].value + ")");
			System.out.println();
		}
		System.out.println();
	}
}
