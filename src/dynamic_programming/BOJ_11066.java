package dynamic_programming;

/**
 * @author Junho
 * 
 * @see https://www.acmicpc.net/problem/11066
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class BOJ_11066 {
	public static long[][] dp;
	public static int[] acc, data;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		while(T-- > 0) {
			int K = Integer.parseInt(br.readLine());
			acc = new int[K + 1];
			dp = new long[K][K];
			data = new int[K];
			for(int i = 0; i < K; i++)
				Arrays.fill(dp[i], -1);
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int i = 0; i < K; i++)
				data[i] = Integer.parseInt(st.nextToken());
			for(int i = 1; i < K + 1; i++)
				acc[i] = acc[i - 1] + data[i - 1];
			sb.append(solve(0, K - 1)).append('\n');
		}
		System.out.println(sb);			
	}
	public static long solve(int start, int end) {
		if(start >= end) 
			return 0;
		if(dp[start][end] != -1)
			return dp[start][end];
		if(start + 1 == end) 
			return data[start] + data[end];
		dp[start][end] = Integer.MAX_VALUE;
		for(int i = start; i < end; i++)
			dp[start][end] = Math.min(dp[start][end], solve(start, i) + solve(i + 1, end) + acc[end + 1] - acc[start]);
		return dp[start][end];
	}
}
