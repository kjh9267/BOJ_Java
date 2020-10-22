package stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/2304
 *
 */

public class BOJ_2304 {
	private static Stick[] data;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		data = new Stick[N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			data[i] = new Stick(x, y);
		}
		Arrays.sort(data);
		System.out.println(solve(N));
	}

	private static int solve(int N) {
		int res = 0;
		Stack<Stick> stack = new Stack<Stick>();
		stack.push(new Stick(data[0].x, 0));
		for(int i = 0; i < N; i++) {
			if(stack.peek().y < data[i].y) {
				Stick s = stack.pop();
				stack.push(data[i]);
				res += s.y * (data[i].x - s.x);
			}
		}
		res += stack.pop().y;
		stack.push(new Stick(data[N - 1].x, 0));
		for(int i = N - 1; i >= 0; i--) {
			if(stack.peek().y <= data[i].y) {
				Stick s = stack.pop();
				res += s.y * (s.x - data[i].x);
				stack.push(data[i]);
			}
		}
		return res;
	}

	private static class Stick implements Comparable<Stick>{
		int x, y;
		public Stick(int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(Stick o) {
			return this.x - o.x;
		}
	}
}
