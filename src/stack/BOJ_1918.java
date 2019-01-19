package stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

public class BOJ_1918 {
	public static HashMap<Character, Integer> priority;

	public static void main(String[] args) throws Exception{
		Stack<Character> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String expression = br.readLine();
		int len = expression.length();
		
		init();
		
		for(int i = 0; i < len; i++) {
			char token = expression.charAt(i);
			
			if(token == '(')
				stack.push(token);
			else if(token == '*' || token == '/' || token == '+' || token == '-') {
				while(true) {
					if(stack.isEmpty()) {
						stack.push(token);
						break;
					}
					char top = stack.peek();
					int curPriority = priority.get(token);
					int topPriority = priority.get(top);

					if(curPriority > topPriority) {
						stack.push(token);
						break;
					}
					
					sb.append(stack.pop());
				}
			}
			else if(token == ')') {
				while(true) {
					char top = stack.pop();
					if(top == '(')
						break;
					else
						sb.append(top);
				}
			}
			else {
				sb.append(token);
			}
		}
		len = stack.size();
		while(len-- > 0) {
			sb.append(stack.pop());
		}
		
		System.out.print(sb);
	}
	
	public static void init() {
		priority = new HashMap<>();
		priority.put('(', 0);
		priority.put('-', 1);
		priority.put('+', 1);
		priority.put('*', 2);
		priority.put('/', 2);
	}
}
