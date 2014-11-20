package QSearchSort;

public class SearchStringWithSpaces {
	public static void main(String[] args) {
		
	}
	
	public static String search(String[] strs, String target) {
		int left = 0, len = strs.length, right = len-1, mid = 0;
		while(left <= right) {
			mid = (left+right)/2;
			
			//middle string is empty, don't know which direction to go
			if(strs[mid].compareTo("") == 0) {
				//find nearest nonempty string left or right
				while(left <= right) {
					if(left-1 >= 0 && strs[left-1].compareTo("") != 0) {
						mid = left-1;
						break;
					}
					else if(right+1 < len && strs[right+1].compareTo("") != 0) {
						mid = right+1;
						break;
					}
					--left;
					++right;
				}
			}
			
			//compare nonempty middle string with target
			int comp = strs[mid].compareTo(target); 
			if(comp > 0) {
				right = mid-1;
			} else if(comp < 0) {
				left = mid+1;
			} else {
				return strs[mid];
			}
		}
		return null;
	}
}
