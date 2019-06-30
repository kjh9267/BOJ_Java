package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/2098
 *
 */

public class BOJ_2098 {
	public static int[][] adj, dp;
	public static int N;
	public static final int MAX = 1_000_000_001;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		adj = new int[N][N];
		dp = new int[N][1 << N];
		for(int i = 0; i < N; i++)
			Arrays.fill(dp[i], -1);
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++)
				adj[i][j] = Integer.parseInt(st.nextToken());
		}
		System.out.println(dfs(0, 1));
	}
	
	public static int dfs(int cur, int visited) {
		if(visited == (1 << N) - 1)
			return adj[cur][0] != 0 ? adj[cur][0] : MAX;
		if(dp[cur][visited] != -1)
			return dp[cur][visited];
		dp[cur][visited] = MAX;
		for(int nxt = 0; nxt < N; nxt++) {
			if((visited & (1 << nxt)) != 0)
				continue;
			if(adj[cur][nxt] == 0)
				continue;
			dp[cur][visited] = Math.min(dp[cur][visited], dfs(nxt, (visited | (1 << nxt))) + adj[cur][nxt]);
		}
		return dp[cur][visited];
	}
}
