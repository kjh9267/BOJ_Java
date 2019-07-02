package line_sweeping;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/1725
 *
 */

public class BOJ_1725 {
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Integer> stack = new Stack<Integer>();
		int N = Integer.parseInt(br.readLine());
		int[] data = new int[N + 2];
		int res = 0;
		
		for(int i = 1; i < N + 1; i++)
			data[i] = Integer.parseInt(br.readLine());
		
		stack.push(0);
		for(int idx = 1; idx < N + 2; idx++) {
			while(!stack.isEmpty() && data[idx] <= data[stack.peek()]) {
				int cur = stack.pop();
				int top = stack.isEmpty() ? 0 : stack.peek();
				res = Math.max(res, data[cur] * (idx - top - 1));
			}
			stack.push(idx);
		}
		System.out.println(res);
	}
}
