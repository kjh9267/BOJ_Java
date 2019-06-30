package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/2133
 *
 */

public class BOJ_2133 {
	public static int[] dp;
	public static int N;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N + 1];
		Arrays.fill(dp, -1);
		System.out.println(solve(N));
	}
	
	public static int solve(int cur) {
		if(cur == 0)
			return 1;
		if(cur % 2 == 1)
			return 0;
		if(dp[cur] != -1)
			return dp[cur];
		dp[cur] = solve(cur - 2) * 3;
		for(int i = 0; i <= cur - 4; i += 2)
			dp[cur] += solve(i) * 2;
		return dp[cur];
	}
}
