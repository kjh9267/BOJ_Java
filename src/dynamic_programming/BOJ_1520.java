package dynamic_programming;

/**
 * @author Junho
 * 
 * @see https://www.acmicpc.net/problem/1520
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1520 {
	private static int[][] grid;
	private static int[][] dp;
	private static int[][] DIR = {{0, -1}, {1, 0}, {-1, 0}, {0, 1}};
	private static int N;
	private static int M;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		grid = new int[M][N];
		dp = new int[M][N];
		for(int i = 0; i < M; i++)
			Arrays.fill(dp[i], -1);
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N; j++)
				grid[i][j] = Integer.parseInt(st.nextToken());
		}
		System.out.println(dfs(0, 0));
	}

	private static int dfs(int x, int y) {
		if(y == M - 1 && x == N - 1)
			return 1;
		if(dp[y][x] != -1)
			return dp[y][x];
		dp[y][x] = 0;
		for(int[] dir : DIR) {
			int nextX = x + dir[0];
			int nextY = y + dir[1];
			if(0 > nextX || nextX >= N || 0 > nextY || nextY >= M)
				continue;
			if(grid[y][x] > grid[nextY][nextX])
				dp[y][x] += dfs(nextX, nextY);
		}
		return dp[y][x];
	}
}
