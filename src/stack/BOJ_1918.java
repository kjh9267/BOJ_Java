package stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

/**
 * 
 * @author Junho
 *		postfix notation
 * @see https://www.acmicpc.net/problem/1918
 * 
 */
public class BOJ_1918 {
	public static HashMap<Character, Integer> priority;
	public static Stack<Character> stack;
	public static StringBuilder sb;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String expression = br.readLine();
		
		init();
		solve(expression);
		
		System.out.print(sb);
	}
	
	public static void init() {
		sb = new StringBuilder();
		stack = new Stack<>();
		priority = new HashMap<>();
		priority.put('(', 0);
		priority.put('-', 1);
		priority.put('+', 1);
		priority.put('*', 2);
		priority.put('/', 2);
	}
	
	public static void solve(String expression) {
		int len = expression.length();
		for(int i = 0; i < len; i++) {
			char token = expression.charAt(i);
			if(token == '(')
				stack.push(token);
			else if(token == '*' || token == '/' || token == '+' || token == '-')
				operator(token);
			else if(token == ')')
				rightBracket();
			else
				sb.append(token);
		}
		len = stack.size();
		while(len-- > 0) {
			sb.append(stack.pop());
		}
	}
	
	public static void operator(char token) {
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
	
	public static void rightBracket() {
		while(true) {
			char top = stack.pop();
			if(top == '(')
				break;
			else
				sb.append(top);
		}
	}
}