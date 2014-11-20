package QJava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Alias {
	public static void main(String[] args) {
		int[] arrA = {1,2,3,4,5};
		
		//deep copy each element with for-loop
		int[] arrB = new int[arrA.length];
		for(int i = 0; i < arrA.length; ++i) {
			arrB[i] = arrA[i] + 10;
		}
		System.out.println(Arrays.toString(arrA));
		System.out.println(Arrays.toString(arrB));
		
		//----------------------------------------------------------------------------
		//deep copy a variable into array with for-loop
		int[] arrC = new int[arrA.length];
		int a = 10;
		for(int i = 0; i < arrA.length; ++i) {
			arrC[i] = a;
		}
		a = 100;  //no effect for those elements in array
		System.out.println(Arrays.toString(arrC));
		
		//----------------------------------------------------------------------------
		ArrayList<Pair> pairs = new ArrayList<Pair>();
		Pair p = new Pair(1,2);
		for(int i = 0; i < 5; ++i) {
			pairs.add(p);
		}
		//p was a shallow copy in for-loop so it was alias in arraylist
		p.a = 5;
		p.b = 6;	
		for(Pair pr : pairs) {
			System.out.println(pr.a + "," +  pr.b);
		}
		
		//----------------------------------------------------------------------------
		p.a = 1;
		p.b = 2;
		pairs.clear();
		for(int i = 0; i < 5; ++i) {
			pairs.add(p.clone());  //clone p so a new object on heap is created instead of aliasing p
		}
		//p was cloned for each addition in arraylist so deep copy is made 
		//based on the overrided clone() in Pair class to make deep copy
		p.a = 5;
		p.b = 6;	
		for(Pair pr : pairs) {
			System.out.println(pr.a + "," +  pr.b);
		}
		
		//----------------------------------------------------------------------------
		String[] t = {"a","b","c"};
		HashMap<String[], Integer> map = new HashMap<String[], Integer>();
		map.put(t, 1);
		t[0] = "d";  //this modifies t
		map.put(t, 1);  //this will update the key since its the same t as before (map still size 1)
		for(String[] tmp : map.keySet()) {
			System.out.println(Arrays.toString(tmp));
		}
	}
	
	static class Pair {
		public int a, b;
		public Pair(int a, int b) {
			this.a = a;
			this.b = b;
		}
		
		@Override
		public Pair clone() {  //used to deep copy a Pair object by creating a new one on heap
			return new Pair(a,b);
		}
	}
}
