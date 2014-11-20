package QGeneral;

import java.util.ArrayList;
import java.util.Iterator;

public class EqualSumSets {
	public static void main(String[] args) {
		ArrayList<Integer> s = new ArrayList<Integer>();
		s.add(3);
		s.add(2);
		s.add(4);
		s.add(1);
		s.add(6);
		s.add(3);
		s.add(2);
		s.add(1);
		
		ArrayList<Integer>[] result = equalSum(s);
		for(ArrayList<Integer> sub : result) {
			Iterator<Integer> itr = sub.iterator();
			while(itr.hasNext()) {
				System.out.print(itr.next() + " ");
			}
			System.out.println();	
		}
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Integer>[] equalSum(ArrayList<Integer> s) {
		int sum = 0;
		for(int i : s) {
			sum += i;
		}
	
		if((sum & 1) == 1)  //odd sum so impossible to divide into equal
			return null;
		
		//even sum so its possible
		//unless if the elements in s cannot be divided into half sum properly
		ArrayList<Integer> sa = new ArrayList<Integer>();
		ArrayList<Integer> sb = new ArrayList<Integer>();
		
		//to avoid concurrency modification problem
		//store all elements to be deleted into this set
		//after iteration, delete all in this set from s
		//can also use a list iterator to remove in same iteration
		ArrayList<Integer> del = new ArrayList<Integer>();
		
		//try to fill sa with half the sum
		sum /= 2;
		for(int i : s) {
			if(sum - i >= 0) {
				sa.add(i);
				del.add(i);
				sum -= i;
			}
			
			if(sum == 0) {
				break;
			}
		}
		
		//can't fill sa with elements for half sum
		if(sum > 0) 
			return null;
		
		s.removeAll(del);
		del.removeAll(del);
		
		for(int i : s) {
			sb.add(i);
			del.add(i);
		}
		
		s.removeAll(del);
		
		ArrayList<Integer>[] result = (ArrayList<Integer>[]) (new Object[2]);
		return result;
	}
}
