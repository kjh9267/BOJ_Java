package divide_conquer;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/11729
 *
 */

public class BOJ_11729 {
	public static int cnt;
	public static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		solve(N, 1, 3, 2);
		System.out.println(cnt);
		System.out.println(sb);
	}
	
	public static void solve(int N, int from, int to, int by) {
		if(N == 0)
			return;
		cnt += 1;
		solve(N - 1, from, by, to);
		sb.append(from).append(' ').append(to).append('\n');
		solve(N - 1, by, to, from);
	}
}
