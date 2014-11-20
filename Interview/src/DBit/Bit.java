package DBit;

public class Bit {
	public static void main(String[] args) {
		double frac = 0.75;
		System.out.println("0.73: " + printBinaryBySubtraction(frac));
		
		int whole = 13;
		System.out.println("13: " + printBinaryBySubtraction(whole));
		
		int neg = -13;
		System.out.println("-13: " + printBinaryBySubtraction(neg));
		System.out.println("-13: " + printBinary(neg));
	}
	
	//this also reverse the int by converting int to binary using /2 and %2 technique (without swapping bits)
	public static String printBinary(int num) {
		boolean isNeg = false;
		if(num < 0) {  //do binary for positive version, then flip all bits and add 1 for negative binary
			isNeg = true;
			num = -num;
			//System.out.println("num became neg after flip due to 2's complement");
		}
		
		StringBuilder sb = new StringBuilder();
		int n = num, remainder = 0;
		while(n > 0) {
			remainder = n%2;
			if(isNeg) {  //flip 1 to 0 or 0 to 1 now since its needs to be negated and add 1 for neg representation
				remainder = remainder ^ 1;  //0^1=1, 1^1=0 (so essentially like flipping a single bit)
			}
			sb.append(remainder);
			n /= 2;
		}
		int zero = sb.length();
		while(zero < 32) {  //add leading 0s to form 32bit string if its less than that
			if(isNeg) {  //add 0 if num is positive, add 1 if num is negative since bits are negated
				sb.append('1');
			} else {
				sb.append('0');
			}
			++zero;
		}

		//division method produce the reverse binary string so need to reverse it
		if(isNeg) {
			return increment(sb.reverse().toString().toCharArray(), 31); 
		} else {
			return sb.reverse().toString();
		}
	}
	
	public static String increment(char[] str, int idx) {
		while(idx >= 0 && str[idx] == '1') {
			str[idx] = '0';
			--idx;
		}
		
		if(idx >= 0) {
			str[idx] = '1';
		}
		
		return new String(str);
	}
	
	public static String printBinaryBySubtraction(double num) {
		StringBuilder bin = new StringBuilder();
		
		boolean isNeg = false;
		double bitValue = 0.0;
		
		if(num > 0 && num < 1) {  //fractional num
			bitValue = 0.5;  //2^-1 = 1/2^1 = 0.5
			bin.append('.');
		} else if(num > 0) {
			bitValue = Math.pow(2, 31);  //2^31 (starting at index 31)
		} else if(num < 0) {
			bitValue = Math.pow(2, 31);  //2^31 (starting at index 31)
			isNeg = true;
			num = -num;  //make num positive to use the same subtraction method, just negate and add 1 to get negative back
		}

		double diff = 0.0;
		int bit = 0;
		
		while(num > 0.0) {
			diff = num - bitValue;
			
			if(diff >= 0.0) {  //current bit position can still be used for subtraction
				//System.out.println("\tset");
				num = diff;
				//System.out.println("\t\tnum: " + num);
				bit = 1;
			} else if(diff < 0.0) {  //current bit position can't be used for subtraction
				//System.out.println("\tdon't set");
				bit = 0;
			}
			
			if(isNeg) { //if num is neg, all bits will get flip at the end anyway so do it now
				bit ^= 1;
			}
			
			bit += '0';  //convert int to char (by adding '0'=48 and casting it to a char)
			bin.append((char)bit);
			bitValue /= 2.0;  //divide by 2 for next position
		}
		
		
		//System.out.println("\t\tlen: " + bin.toString().length());
		if(isNeg) {  //add 1 after all bits are negated to get negative representation of num
			return increment(bin.toString().toCharArray(), 31); 
		}
		
		//no need to reverse string in this case because the binary is generated left to right in order
		//also since bitValue starts at 2^32, it starts the binary string at position 32 (index 31) so the final length will be 32bit
		return bin.toString();
	}
	
	public static int convertBinaryToInteger(String bin, int startIdx) {
		//System.out.println("bin len: " + bin.length());
		int num = 0, val = (int)Math.pow(2.0, startIdx);  //start at bit 32 (idx 31)
		int pos = 0, len = bin.length();
		char c;
		while(pos < len) {
			c = bin.charAt(pos);
			if(c == '1')
				num += val;
			val >>= 1;
			++pos;
		}
		return num;
	}
	
	public static int convertBinaryToInteger(String bin) {
		int pos = 0, len = bin.length();
		int num = 0, val = (int)Math.pow(2.0, (len-1));  //start at bit 32 (idx 31)
		char c;
		while(pos < len) {
			c = bin.charAt(pos);
			if(c == '1')
				num += val;
			val >>= 1;
			++pos;
		}
		return num;
	}
}
