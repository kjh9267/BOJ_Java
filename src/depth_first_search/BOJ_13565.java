package depth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13565 {
	public static char[][] graph;
	public static boolean[][] visited;
	public static int N, M;
	public static final int[][] DIR = {{0,-1},{1,0},{0,1},{-1,0}};
	public static boolean res;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new char[N][M];
		visited = new boolean[N][M];
		
		for(int i = 0; i < N; i++)
			graph[i] = br.readLine().toCharArray();
		
		for(int i = 0; i < M; i++) {
			if(res)
				break;
			if(graph[0][i] == '0' && !visited[0][i])
				dfs(i,0);
		}
		
		System.out.println(res ? "YES" : "NO");
	}
	
	public static void dfs(int x, int y) {
		if (y == N - 1) {
			res = true;
			return;
		}
		if (visited[y][x])
			return;
		visited[y][x] = true;
		
		for(int[] dir : DIR) {
			int next_x = x + dir[0];
			int next_y = y + dir[1];
			
			if(0 <= next_x && next_x < M && 0 <= next_y && next_y < N)
				if(graph[next_y][next_x] == '0')
					dfs(next_x,next_y);
		}
	}
}
