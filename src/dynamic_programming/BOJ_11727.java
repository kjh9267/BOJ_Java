package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/11727
 *
 */

public class BOJ_11727 {
	public static int[] dp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		dp = new int[N + 1];
		System.out.println(solve(N));
	}
	public static int solve(int N) {
		if(N <= 1)
			return 1;
		if(dp[N] != 0)
			return dp[N];
		dp[N] = (solve(N - 2) * 2) % 10_007 + solve(N - 1);
		return dp[N] % 10_007;
	}
}
