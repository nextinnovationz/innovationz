package QBit;

import DBit.Bit;

public class SameNumberOfOnes {
	public static void main(String[] args) {
		int num = Bit.convertBinaryToInteger("1000101110");
		System.out.println(Bit.printBinary(num));
		System.out.println(Bit.printBinary(greater(num)));
	}
	
	//1000101110  find pos 01 from right 
	//1000111110  set pos 0 to 1
	//1000110000  clear all from pos to right
	//1000111100  add oneCnt-1 1's back to right 
	//find a 0 to the left of a 1 (01 adjacent from right to left)
	public static int greater(int num) {
		int pos = 0, prev = 0, curr = 0;
		int oneCnt = 0, zeroCnt = 0;  //keep track of 0's and 1's count to the right of pos
		while(pos < 32) {  //while not at the last (leftmost) bit, keep checking
			curr = num & (1 << pos);  //get current bit 
			if(curr == 0) {
				if(prev == 1) {  //found 01, curr is 0 and prev is 1
					break;
				}
				
				//don't include the 0 at pos so only increment cnt if not 01
				++zeroCnt;
			} else {
				++oneCnt;
			}

			++pos;  //keep moving to left by 1 to find 01
			prev = curr;  //keep track of 1 bit to the right for 01's 1
		}
		
		//set the pos's 0 to a 1 (to make new number greater than num)
		num |= (1 << pos);
		
		//now there is oneCnt+1 1's and zeroCnt-1 0's
		//now there is 1 more 1's and 1 less 0's in new number
		//need to clear an existing 1 to a 0 to make new number has same number of 0's and 1's as old num
		
		//first, clear all bits on right of pos (pos, 0]
		int clearMask = ((~0) << (32-pos-1)) >>> (32-pos-1);
		num &= clearMask;
		
		//second, set oneCnt-1 1's to right of pos (pos, 0]
		//so the from pos onward right, there are oneCnt 1's just like old num
		//total bits right of pos = (oneCnt)+(zeroCnt-1)
		int setMask = ((~0) << (32-(zeroCnt+1))) >>> (32-(zeroCnt+1));
		
		//need to align 1st set bit of mask to pos-1 (right of pos) 
		//the leftmost 1 in mask will align with pos-1 so << (pos - 1 - (oneCnt-1)) 
		setMask <<= (pos-1-(oneCnt-1));
		num |= setMask;
		
		return num;
	}
}
