package QString;

public class Compression {
	public static void main(String[] args) {
		String a = "aaaabbbccdeefg";
		System.out.println("final: " + compress(a));
	}
	
	public static String compress(String s) {
		int pos = 1, len = s.length(), count = 1;
		StringBuilder sb = new StringBuilder();
		char prev = s.charAt(0);
		
		while(pos < len) {
			if(s.charAt(pos) == prev)
				++count;
			else {
				sb.append(prev);
				sb.append(count);
				prev = s.charAt(pos);
				count = 1;
			}
			++pos;
		}
		
		sb.append(prev);
		sb.append(count);
		
		System.out.println("compressed: " + sb.toString() + "\tlen: " + sb.length());
		System.out.println("orig: " + s + "\tlen: " + s.length());
		
		if(len > sb.length())
			return sb.toString();
		else
			return s;
	}
}
