package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/2748
 *
 */

public class BOJ_2748 {
	public static long[] dp;
	
	public static long solve(int N) {
		if(N == 0)
			return 0;
		if(N == 1)
			return 1;
		if(dp[N] != 0)
			return dp[N];
		dp[N - 1] = solve(N - 1);
		dp[N - 2] = solve(N - 2);
		return dp[N - 1] + dp[N - 2];
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		dp = new long[N + 1];
		
		System.out.println(solve(N));
	}
}
