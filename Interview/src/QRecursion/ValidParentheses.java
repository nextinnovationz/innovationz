package QRecursion;

public class ValidParentheses {
	public static void main(String[] args) {
		generate(5);
	}
	
	public static void generate(int n) {
		int leftRemain = n, rightRemain = n;
		StringBuilder sb = new StringBuilder();
		generate(sb, leftRemain, rightRemain, 2* n);
	}
	
	public static void generate(StringBuilder build, int leftRemain, int rightRemain, int totalLen) {
		if(build.length() == totalLen) {  //len is 2n so print it
			System.out.println(build.toString());
			return;
		} else {
			if(leftRemain > 0) {  //keep adding ( if there are more to add
				build.append("(");
				generate(build, leftRemain-1, rightRemain, totalLen);
				build.deleteCharAt(build.length()-1);
			} 
			if(rightRemain > leftRemain) {  //add ) only if there are more ) remain than (
				build.append(")");
				generate(build, leftRemain, rightRemain-1, totalLen);
				build.deleteCharAt(build.length()-1);
			}
		}
	}
}
