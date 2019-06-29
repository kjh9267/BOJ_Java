package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/9465
 *
 */

public class BOJ_9465 {
	public static int[][] dp, data;
	public static int N;
	
	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			N = Integer.parseInt(br.readLine());
			data = new int[2][N + 1];
			dp = new int[2][N + 1];
			for(int i = 0; i < 2; i++)
				Arrays.fill(dp[i], -1);
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 1; i < N + 1; i++)
				data[0][i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i < N + 1; i++)
				data[1][i] = Integer.parseInt(st.nextToken());
			int res = Math.max(solve(1, 0), solve(1, 1));
			sb.append(res).append('\n');
		}
		System.out.println(sb);
	}
	
	public static int solve(int cur, int pos) {
		if(cur > N)
			return 0;
		if(dp[pos][cur] != -1)
			return dp[pos][cur];
		if(pos == 0)
			dp[0][cur] = Math.max(solve(cur + 1, 1), solve(cur + 2, 1)) + data[0][cur];
		else
			dp[1][cur] = Math.max(solve(cur + 1, 0), solve(cur + 2, 0)) + data[1][cur];
		return dp[pos][cur];
	}
}
