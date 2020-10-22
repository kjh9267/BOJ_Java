package dynamic_programming;

/**
 * @author Junho
 * 
 * @see https://www.acmicpc.net/problem/11726
 * 
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_11726 {
	private static int[] dp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		dp = new int[N + 1];
		System.out.println(solve(N));
	}

	private static int solve(int N) {
		if(N <= 1)
			return 1;
		if(dp[N] != 0)
			return dp[N];
		dp[N] = solve(N - 1) % 10_007 + solve(N - 2) % 10_007;
		return dp[N] % 10_007;
	}
}
