package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/1981
 *
 */

public class BOJ_1981 {
	public static int[][] grid;
	public static int N, MAX = -1, MIN = 200;
	public static final int[][] DIR = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
	public static boolean[][] visited;
	public static boolean res;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		grid = new int[N][N];

		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				grid[i][j]= Integer.parseInt(st.nextToken());
				if(MAX < grid[i][j])
					MAX = grid[i][j];
				if(MIN > grid[i][j])
					MIN = grid[i][j];
			}
		}
		System.out.println(binarySearch());
	}
	
	public static int binarySearch() {
		int lo = -1;
		int hi = MAX - MIN;
		
		while(lo + 1 < hi) {
			int mid = (lo + hi) >> 1;
			if(check(mid))
				hi = mid;
			else
				lo = mid;
		}
		return hi;
	}
	
	public static boolean check(int value) {
		res = false;
		for(int min = MIN; min <= MAX - value; min++) {
			visited = new boolean[N][N];
			for(int i = 0; i < N; i++)
				for(int j = 0; j < N; j++)
					if(grid[i][j] < min || grid[i][j] > min + value)
						visited[i][j] = true;
			if(visited[0][0])
				continue;
			visited[0][0] = true;
			dfs(0,0);
			if(res)
				return res;
		}
		return res;
	}
	
	public static void dfs(int x, int y) {
		if(x == N - 1 && y == N - 1) {
			res = true;
			return;
		}
		for(int[] dir : DIR) {
			int nextX = x + dir[0];
			int nextY = y + dir[1];
			if(nextX < 0 || nextX == N || nextY < 0 || nextY == N)
				continue;
			if(visited[nextY][nextX])
				continue;
			visited[nextY][nextX] = true;
			dfs(nextX, nextY);
		}
		return;
	}
}
