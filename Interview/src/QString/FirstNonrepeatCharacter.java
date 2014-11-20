package QString;

import Exception.InvalidInputException;
import Exception.NoResultException;

public class FirstNonrepeatCharacter {
	public static void main(String[] args) {
		try {
			String str = "google";
			System.out.println("nonrepeat: " + findFirstNonrepeat(str));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static char findFirstNonrepeat(String a) throws NoResultException, InvalidInputException {
		if(a == null || a.length() == 0)
			throw new InvalidInputException("bad string input");
		
		int[] lookup = new int[256];
		int alen = a.length();
		for(int i = 0; i < alen; ++i) {
			++lookup[a.charAt(i)];
		}
		
		for(int j = 0; j < alen; ++j) {
			if(lookup[a.charAt(j)] == 1)
				return a.charAt(j);
		}
		
		throw new NoResultException("all chars repeated");
	}
}
