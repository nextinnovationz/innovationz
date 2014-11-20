package QArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class Top3Patterns {
	public static void main(String[] args) {
		HashMap<String, String[]> data = new HashMap<String, String[]>();
		data.put("john", new String[]{"a", "b", "c", "a", "d", "e", "f", "d"});
		data.put("andy", new String[]{"a", "e", "c", "a", "b", "c", "a", "b", "c"});
		data.put("kelly", new String[]{"g", "i", "c"});
		data.put("bryan", new String[]{"b", "i", "c", "a", "b", "d", "e", "f"});
		data.put("david", new String[]{"w", "e", "f", "d", "e", "f"});
		data.put("stephy", new String[]{"z", "a", "b", "c", "q", "i", "c"});
		data.put("laura", new String[]{"g", "i", "c", "g", "q", "i", "c"});
		data.put("mike", new String[]{"g", "i", "c", "l", "g", "i", "c", "k", "g", "i", "c"});
		int k = 2;
		List<String[]> topK = getTopKPatterns(data, k);
		System.out.println("top " + k + " patterns");
		for(String[] top : topK) {
			System.out.println(Arrays.toString(top));
		}
	}
		
	public static List<String[]> getTopKPatterns(HashMap<String, String[]> data, int k) {
		//given <user, list of items visited>
		//create <pattern, count> for each pattern 
		//use sliding window of size k on each entry <user, list of items visited>
		//if there are less than k in last group, ignore it since its not a valid k size pattern
		HashMap<Integer, Pattern> patternCounts = new HashMap<Integer, Pattern>();
		PriorityQueue<Pattern> pq = new PriorityQueue<Pattern>(k, new PatternComparator(patternCounts));
		Pattern min = null;
		int start = 0, end = 0, idx = 0, minCnt = 0, currCnt = 0, listLen = 0;
		for(String[] visited : data.values()) {  //each String[]
			System.out.println("\n" + Arrays.toString(visited));
			listLen = visited.length;
			if(listLen < k) {  //String[] is less than k so no pattern
				continue;
			} else if(listLen == k) {  //String[] is exactly k so just use whole thing as pattern
				System.out.println("pattern: " + Arrays.toString(visited));
				Pattern p = new Pattern(visited);
		
				//default count is 1 before looking up
				//change this if pattern already exist, get its count
				//or leave it as it is for new pattern
				currCnt = 1;
				
				//a new k size pattern, update count or add it
				if(patternCounts.containsKey(p.key)) {
					System.out.println("\t\told pattern: " + Arrays.toString(p.value));
					currCnt = patternCounts.get(p.key).count;
					++currCnt;
					++patternCounts.get(p.key).count;
					p.count = currCnt;
					
				} else {
					System.out.println("\t\tnew pattern: " + Arrays.toString(p.value));
					patternCounts.put(p.key, p);
				}
				
				//since pq is being modified as the code generate each k size sequence
				//the counts of a sequence can change
				//the pq only holds the sequence and not its count
				//in the comparator, it lookups the hashtable to get the latest count for each element in pq
				//so its guaranteed to be correct without the need to manually update element count in pq
				if(pq.size() < k && !pq.contains(p)) {
					pq.add(p);
				} else {
					min = pq.peek();
					minCnt = patternCounts.get(min.key).count;
					
					//only replace root of pq if current element is not already in pq and its count is greater than root
					if(currCnt > minCnt && !pq.contains(p)) {
						System.out.println("\t\treplacing: " + Arrays.toString(min.value) + " with " + Arrays.toString(p.value));
						pq.remove();  //remove min root of pq
						pq.add(p);  //add in pattern with greater count
					} 
				}
			} else {  //String[] is more than k so may have more than 1 patterns
				while(end < listLen) {  //each string in String[]
					//create a new String[] for each pattern to hash into table as key
					//otherwise, it will be hashing the same String[] as key each time
					String[] pattern = new String[k];
					
					//form a new k size pattern
					for(end = start; (end-start+1) <= k && end < listLen; ++end) {
						pattern[idx] = visited[end];
						++idx;
					}
					
					System.out.println("pattern: " + Arrays.toString(pattern));

					Pattern p = new Pattern(pattern);
					
					//not enough items for the last pattern
					if((end-start+1) < k) {
						System.out.println("\tcan't form last pattern for this user");
						break;
					} else {
						System.out.println("\tadding new pattern");
					}
					
					//reset for next pattern
					idx = 0;
					//slide window to the right by 1
					++start;
					
					//default count is 1 before looking up
					//change this if pattern already exist, get its count
					//or leave it as it is for new pattern
					currCnt = 1;
					
					//a new k size pattern, update count or add it
					if(patternCounts.containsKey(p.key)) {
						System.out.println("\t\told pattern: " + Arrays.toString(p.value));
						currCnt = patternCounts.get(p.key).count;
						++currCnt;
						++patternCounts.get(p.key).count;
						p.count = currCnt;
						
					} else {
						System.out.println("\t\tnew pattern: " + Arrays.toString(p.value));
						patternCounts.put(p.key, p);
					}
					
					//since pq is being modified as the code generate each k size sequence
					//the counts of a sequence can change
					//the pq only holds the sequence and not its count
					//in the comparator, it lookups the hashtable to get the latest count for each element in pq
					//so its guaranteed to be correct without the need to manually update element count in pq
					if(pq.size() < k && !pq.contains(p)) {
						pq.add(p);
					} else {
						min = pq.peek();
						minCnt = patternCounts.get(min.key).count;
						
						//only replace root of pq if current element is not already in pq and its count is greater than root
						if(currCnt > minCnt && !pq.contains(p)) {
							System.out.println("\t\treplacing: " + Arrays.toString(min.value) + " with " + Arrays.toString(p.value));
							pq.remove();  //remove min root of pq
							pq.add(p);  //add in pattern with greater count
						} 
					}
				}
				
				//initialize for next user's list of items
				idx = 0;
				start = 0;
				end = 0;
			}
		}
		
		System.out.println("\nfinal hashtable");
		for(int key : patternCounts.keySet()) {
			Pattern p = patternCounts.get(key);
			System.out.println(Arrays.toString(p.value) + " - " + p.count);
		}
		
		System.out.println();
		
		List<String[]> result = new ArrayList<String[]>();
		while(!pq.isEmpty()) {
			result.add(pq.remove().value);
		}
		return result;
	}
	
	static class Pattern {
		public int key, count;
		public String[] value;
		
		//the key for each pattern is the hashCode of the string
		//so same String[] will create same string thus same hashCode
		//if same set of elements, but different order, then string hashCode will differ
		//have constructor auto generate hashCode 
		public Pattern(String[] value) {
			this.value = value;
			this.count = 1;
			this.key = hashCode();
		}
		
		public boolean equals(Object obj) {
			if(!(obj instanceof Pattern))
				return false;
			else if(obj == this)
				return true;
			else if(((Pattern)obj).hashCode() == hashCode())
				return true;
			else
				return false;
		}
		
		@Override
		public int hashCode() {
			StringBuilder sb = new StringBuilder();
			for(String s : value) {
				sb.append(s);
			}
			return sb.toString().hashCode();
		}
	}
	
	static class PatternComparator implements Comparator<Pattern> {
		public HashMap<Integer, Pattern> patternCounts;
		
		//use hashtable to ensure each comparison uses the latest count for each pattern
		public PatternComparator(HashMap<Integer, Pattern> patternCounts) {
			this.patternCounts = patternCounts; 
		}
		@Override
		public int compare(Pattern arg0, Pattern arg1) {
			return patternCounts.get(arg0.key).count - patternCounts.get(arg1.key).count;
		}
	}
}	
