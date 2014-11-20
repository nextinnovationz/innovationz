package QGeneral;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AllSubsets {
	public static void main(String[] args) {
		int[] arr = {1,2,3,4};
		ArrayList<ArrayList<Integer>> all = allSubsets(arr);
		
		/*
		//sort arraylist of subsets based on each subset sum
		Collections.sort(all, new Comparator<ArrayList<Integer>>() {
			@Override
			public int compare(ArrayList<Integer> a, ArrayList<Integer> b) {
				int sumA = sum(a);
				int sumB = sum(b);
				
				if(sumA > sumB)
					return 1;
				else if(sumA < sumB)
					return -1;
				else
					return 0;
			}
			
			public int sum(ArrayList<Integer> c) {
				if(c == null || c.size() == 0)
					return 0;
				
				int sum = 0;
				for(Integer i : c) {
					sum += i;
				}
				
				return sum;
			}
		});
		*/
		
		//sort by subset size first, then by subset sum for same sizes
		Collections.sort(all, new Comparator<ArrayList<Integer>>() {
			@Override
			public int compare(ArrayList<Integer> a, ArrayList<Integer> b) {
				int sizeA = a.size();
				int sizeB = b.size();
				
				if(sizeA > sizeB)
					return 1;
				else if(sizeA < sizeB)
					return -1;
				else {
					int sumA = sum(a);
					int sumB = sum(b);
					
					if(sumA > sumB)
						return 1;
					else if(sumA < sumB)
						return -1;
					else
						return 0;
				}
			}
			
			public int sum(ArrayList<Integer> c) {
				if(c == null || c.size() == 0)
					return 0;
				
				int sum = 0;
				for(Integer i : c) {
					sum += i;
				}
				
				return sum;
			}
		});
		
		System.out.println(all.toString());
		System.out.println("size: " + all.size());
	}
	
	public static ArrayList<ArrayList<Integer>> allSubsets(int[] set) {
		//set of size n will have 2^n subsets including the empty set
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
	
		//add empty set
		result.add(new ArrayList<Integer>());
		
		//recursively add subsets for each element
		return allSubsets(set, 0, result);
	}
	
	public static ArrayList<ArrayList<Integer>> allSubsets(int[] set, int pos, ArrayList<ArrayList<Integer>> all) {
		//base case when all elements in set are considered
		if(pos == set.length) {
			return all;
		}
		
		//create a new set that contains all previous subsets and new ones including this element
		ArrayList<ArrayList<Integer>> newAll = new ArrayList<ArrayList<Integer>>();
		
		//add current element to each of the existing subsets to generate all new subsets for this element
		for(ArrayList<Integer> eachSub : all) {
			//add this subset into new set (without current element)
			newAll.add(deepCopyArrayList(eachSub));
			
			//add current element to this subset
			eachSub.add(set[pos]);
			
			//add new subset to new set (with current element)
			newAll.add(deepCopyArrayList(eachSub));
		}
		
		//recursive perform same steps on next element
		return allSubsets(set, pos+1, newAll);
	}
	
	public static ArrayList<Integer> deepCopyArrayList(ArrayList<Integer> orig) {
		//allocate a new array list in the heap
		ArrayList<Integer> copy = new ArrayList<Integer>();
		
		//copy each element into new array list
		for(Integer i : orig) {
			copy.add(i);
		}
		
		//return new array list
		return copy;
	}
}	
