package divide_conquer;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/1992
 *
 */

public class BOJ_1992 {
	private static char[][] grid;
	private static StringBuilder sb;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		grid = new char[N + 1][N + 1];
		sb = new StringBuilder();

		for(int i = 0; i < N; i++)
			grid[i] = br.readLine().toCharArray();

		solve(0, 0, N);
		System.out.println(sb);
	}

	private static void solve(int x, int y, int len) {
		boolean flag = false;
		for(int i = y; i < y + len; i++) {
			for(int j = x; j < x + len; j++) {
				if(grid[i][j] != grid[y][x]) {
					flag = true;
					break;
				}
			}
			if(flag)
				break;
		}

		if(!flag) {
			sb.append(grid[y][x]);
			return;
		}

		sb.append('(');
		solve(x, y, len / 2);
		solve(x + len / 2, y, len / 2);
		solve(x, y + len / 2, len / 2);
		solve(x + len / 2, y + len / 2, len / 2);
		sb.append(')');
	}
}
