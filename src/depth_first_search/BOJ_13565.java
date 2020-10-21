package depth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13565 {
	private static char[][] graph;
	private static int N;
	private static int M;
	private static final int[][] DIR = {{0,-1},{1,0},{0,1},{-1,0}};
	private static boolean res;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new char[N][M];
		
		for(int i = 0; i < N; i++)
			graph[i] = br.readLine().toCharArray();
		
		for(int i = 0; i < M; i++)
			if(graph[0][i] == '0')
				dfs(i,0);
		
		System.out.println(res ? "YES" : "NO");
	}

	private static void dfs(int x, int y) {
		if(y == N - 1)
			res = true;
		graph[y][x] = '1';
		for(int[] dir : DIR) {
			int next_x = x + dir[0];
			int next_y = y + dir[1];
			
			if(0 <= next_x && next_x < M && 0 <= next_y && next_y < N)
				if(graph[next_y][next_x] == '0')
					dfs(next_x,next_y);
		}
	}
}