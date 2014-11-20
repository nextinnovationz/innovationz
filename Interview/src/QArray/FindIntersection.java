package QArray;

import java.util.ArrayList;

public class FindIntersection {
	public static void main(String[] args) {
		int[] aList = {1,2,3,4,5,6,6,7,8};
		int[] bList = {4,5,5,6,9};
		ArrayList<Integer> common = findAllIntersections(aList, bList);
		System.out.println("commons: " + common);
	}
	
	public static ArrayList<Integer> findAllIntersections(int[] aList, int[] bList) {
		ArrayList<Integer> common = new ArrayList<Integer>();
		int apos = 0, bpos = 0, aLen = aList.length, bLen = bList.length;
		
		//once the shorter list ends, then the remaining in the longer list are not shared among them
		while(apos < aLen && bpos < bLen) {
			if(aList[apos] > bList[bpos])  //curr in aList is too big, find a curr in bList that is >=
				++bpos;
			else if(aList[apos] < bList[bpos])  //curr in bList is too big, find a curr in aList that is >=
				++apos;
			else {  //if curr is the same for both lists, then its shared
				common.add(aList[apos]);
				
				//need to update both pointers so it can proceed to find next intersection if any
				++apos;
				++bpos;
			}
		}
		return common;
	}
}


