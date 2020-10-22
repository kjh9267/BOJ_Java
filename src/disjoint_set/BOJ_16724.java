package disjoint_set;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/16724
 *
 */

public class BOJ_16724 {
	private static int N;
	private static int M;
	private static char[][] graph;
	private static int[] parent;
	private static int[][] DIR = {{0,-1},{1,0},{0,1},{-1,0}};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new char[N][M];
		parent = new int[N * M];
		
		for(int i = 0; i < N; i++)
			graph[i] = br.readLine().toCharArray();
		
		Arrays.fill(parent, -1);
		
		for(int i = 0; i < N; i++)
			for(int j = 0; j < M; j++)
				dfs(j,i);
		
		int cnt = 0;
		for(int i = 0; i < N * M; i++)
			if(parent[i] < 0)
				cnt += 1;
		
		System.out.println(cnt);
	}

	private static int find(int x) {
		if(parent[x] < 0)
			return x;
		return parent[x] = find(parent[x]);
	}

	private static boolean merge(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y)
			return true;
		if(parent[x] > parent[y]) {
			parent[y] += parent[x];
			parent[x] = y;
		}
		else {
			parent[x] += parent[y];
			parent[y] = x;
		}
		return false;
	}

	private static void dfs(int x, int y) {
		int cur = spanning(x, y);
		int d = dirCheck(x,y);
		int nextX = x + DIR[d][0];
		int nextY = y + DIR[d][1];
		
		if(indexError(nextX,nextY))
			return;
		
		int next = spanning(nextX, nextY);
			
		if(merge(cur, next))
			return;
		
		dfs(nextX, nextY);
	}

	private static int spanning(int x, int y) {
		return y * M + x;
	}

	private static int dirCheck(int x, int y) {
		char c = graph[y][x];
		if(c == 'U')
			return 0;
		if(c == 'R')
			return 1;
		if(c == 'D')
			return 2;
		if(c == 'L')
			return 3;
		return -1;
	}

	private static boolean indexError(int x, int y) {
		if(x < 0 || x >= M || y < 0 || y >= N)
			return true;
		return false;
	}
}
