package stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_1918 {
	
	
	public static void main(String[] args) throws Exception{
		Stack<Character> stack = new Stack<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String expression = br.readLine();
		int len = expression.length();
		
		for(int i = 0; i < len; i++) {
			char token = expression.charAt(i);
			
			if(token == '(')
				stack.push(token);
			else if(token == '*' || token == '/')
		}
	}
}
