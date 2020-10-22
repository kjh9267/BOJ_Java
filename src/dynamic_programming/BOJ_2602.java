package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/2602
 *
 */

public class BOJ_2602 {
	private static char[] data;
	private static char[][] grid;
	private static int[][][] dp;
	private static int N;
	private static int M;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		data = br.readLine().toCharArray();
		String line = br.readLine();
		N = line.length();
		M = data.length;
		grid = new char[2][N];
		grid[0] = line.toCharArray();
		grid[1] = br.readLine().toCharArray();
		dp = new int[2][N][M];
		
		int res = 0;
		for(int i = 0; i < 2; i++)
			for(int j = 0; j < N; j++)
				if(grid[i][j] == data[0])
					res += solve(0, j, i);
		
		System.out.println(res);
	}

	private static int solve(int cur, int x, int y) {
		if(cur == M)
			return 0;
		if(cur == M - 1 && data[cur] == grid[y][x])
			return 1;
		if(data[cur] != grid[y][x])
			return 0;
		if(dp[y][x][cur] != 0)
			return dp[y][x][cur];
		for(int i = x + 1; i < N; i++)
			dp[y][x][cur] += solve(cur + 1, i, y == 0 ? 1 : 0);
		return dp[y][x][cur];
	}
}
