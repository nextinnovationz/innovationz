package QJava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import DSTree.Node;

public class Compare {
	public static void main(String[] args) {
		String a = "hello";
		String b = "hello";
		String c = a;
		//string hash code is based on values of string 
		//not by memory address so equals() was overridden to compare hashCode() by value
		
		//equals() compare based on hashCode() or overridden by itself (without calling hashCode(), but don't work for hashtable)
		//== compare if two references point to same object
		//compareTo() and compare() requires implementing Comparable<T> and Comparator<T> interfaces (not available unless implemented)
		
		//in this case, "hello" is same object created by compiler 
		System.out.println("a hash: " + a.hashCode());
		System.out.println("b hash: " + b.hashCode());
		System.out.println("c hash: " + c.hashCode());
		System.out.println("a == b:" + (a == b));  //compiler created same string object when String constructor not invoked
		System.out.println("a.equals(b): " + (a.equals(b)));  //string overridden equals() and hashCode() by value
		
		String d = new String("hello");
		System.out.println("\nd hash: " + d.hashCode());  //same hash code by value
		System.out.println("a == d: " + (a == d));  //String constructor invoked so this is a different string object
		System.out.println("a.equals(d): " + (a.equals(d)));  //still comparing with equals() by value 
		
		String e = new String("hello");
		System.out.println("\ne hash: " + e.hashCode());  //same hash code by value 
		System.out.println("d == e: " + (d == e));  //string constructor invoked so this is a different string object
		
		Node<Integer> f = new Node<Integer>(1);
		Node<Integer> g = new Node<Integer>(1);
		System.out.println("\nf hash: " + f.hashCode());  //hashCode() not overridden in Node so its by default Object's memory address
		System.out.println("g hash: " + g.hashCode());  //by Object's memory address
		System.out.println("f == g: " + (f == g));  //not the same object by references
		System.out.println("f.equals(g): " + (f.equals(g)));  //equals() not overridden in Node so its by comparing hashCode() which are different
		System.out.println("f.data.equals(g.data): " + (f.data.equals(g.data)));  //comparing integer data by value
		
		System.out.println();
		testString();
	}
	
	//Comparable<Item> interface only provides compareTo(Item arg0) 1 argument
	//so must do this inside the class that implements the Comparable interface, thus Item
	
	//Comparator<Item> interface only provides compare(Item arg0, Item arg1) 2 arguments
	//so can create a separate class for this interface if needed that accepts the two objects to compare
	static class Item implements Comparable<Item>, Comparator<Item> {
		public int key;
		public String data;
		public Item(int key, String data) {
			this.key = key;
			this.data = data;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(!(obj instanceof Item)) {
				return false;
			} else if(obj == this) {
				return true;
			} else {
				Item i = (Item) obj;
				return (data.compareTo(i.data) == 0) && (key == i.key); 
			}
		}

		@Override
		public int hashCode() {
			//create a new string that appends the key to the data and take its string hashCode
			//because primitive int and Integer don't have hashCode()
			return new String(data + key).hashCode();
		}
		
		@Override
		public int compareTo(Item arg0) {
			//compare by key (Integer comparisons) first
			//if some key, then compare by data (String comparisons) second
			int keyCompare = Integer.compare(key, arg0.key);
			if(keyCompare == 0)
				return data.compareTo(arg0.data);
			else
				return keyCompare;
		}

		@Override
		public int compare(Item arg0, Item arg1) {
			//compare by key (Integer comparisons) first
			//if some key, then compare by data (String comparisons) second
			int keyCompare = Integer.compare(arg0.key, arg1.key);
			if(keyCompare == 0)
				return arg0.data.compareTo(arg1.data);
			else
				return keyCompare;
		}
	}
	
	//creating a comparator class that implements the interface instead of doing it in the user class
	//this is useful for algorithms or data structures that requires a comparator object 
	//ie. priority queue, sorting algorithms, etc
	static class ItemComparator implements Comparator<Item> {
		@Override
		public int compare(Item arg0, Item arg1) {
			//compare by key (Integer comparisons) first
			//if some key, then compare by data (String comparisons) second
			int keyCompare = Integer.compare(arg0.key, arg1.key);
			if(keyCompare == 0)
				return arg0.data.compareTo(arg1.data);
			else
				return keyCompare;
		}
	}
	
	public static void changeString(String orig) {
		orig = "world";
		orig = orig.substring(0, 2);
	}
	
	public static void changeArrayList(ArrayList<String> arr) {
		arr.add("c");
		arr.add("d");
	}
	
	public static void changeArray(String[] arr) {
		arr[2] = "c";
		arr[3] = "d";
	}
	
	public static void testString() {
		//java pass by value for String and primitives (argument into method are copies of the original)
		//modify input string, but input is copied so changes not affected on original
		//need to have a return value and set original string to return value to actually make change
		String str = new String("hello");
		System.out.println("orig: " + str);
		changeString(str); 
		System.out.println("after: " + str);		
		
		//java pass by reference for Objects, ie collections
		//object is actually modified 
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("a");
		arr.add("b");
		System.out.println("before: " + arr.toString());
		changeArrayList(arr);
		System.out.println("after: " + arr.toString());
		
		//java pass by reference for Objects, ie collections
		//object is actually modified 
		String[] arr2 = new String[5];
		arr2[0] = "a";
		arr2[1] = "b";
		System.out.println("before: " + Arrays.toString(arr2));
		changeArray(arr2);
		System.out.println("after: " + Arrays.toString(arr2));
	}
}
