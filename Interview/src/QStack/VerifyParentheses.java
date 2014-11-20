package QStack;

import java.util.Stack;

public class VerifyParentheses {
	public static void main(String[] args) {
		String a = "()()()";
		String b = "(())()";
		String c = "())()";
		String d = ")";
		String e = "(";
		System.out.println(isValid(a));
		System.out.println(isValid(b));
		System.out.println(isValid(c));
		System.out.println(isValid(d));
		System.out.println(isValid(e));
		
		String f = "[({})]";
		String g = "[](){}";
		String h = "[(}]()";
		System.out.println(isValidBraces(f));
		System.out.println(isValidBraces(g));
		System.out.println(isValidBraces(h));
	}
	
	public static boolean isValid(String pattern) {
		Stack<Character> openStack = new Stack<Character>();
		int pos = 0;
		char curr;
		while(pos < pattern.length()) {
			curr = pattern.charAt(pos);
			if(curr == '(') {  
				openStack.push(curr);
			} else if(curr == ')') {
				if(openStack.isEmpty()) {  
					return false;
				} else {
					openStack.pop();
				}
			} else {
				break;
			}
			++pos;
		}
		return openStack.isEmpty();  //stack should be empty if all () eliminates
	}
	
	public static boolean isValidBraces(String pattern) {
		Stack<Character> openStack = new Stack<Character>();
		int pos = 0;
		char curr, top;
		while(pos < pattern.length()) {
			curr = pattern.charAt(pos);
			//push any open into stack 
			if(curr == '(' || curr == '{' || curr == '[') {  
				openStack.push(curr);
			} else if(curr == ')' || curr == ']' || curr == '}') {
				//have a close with no open to match so must be invalid
				if(openStack.isEmpty()) {  
					return false;
				} else {
					top = openStack.pop();
					
					//see if close match the appropriate open
					if(curr == ')' && (top == '[' || top == '{'))
						return false;
					if(curr == ']' && (top == '{' || top == '('))
						return false;
					if(curr == '}' && (top == '[' || top == '('))
						return false;
				}
			} else {
				break;
			}
			++pos;
		}
		return openStack.isEmpty();  //stack should be empty if all open and close eliminates
	}
}
