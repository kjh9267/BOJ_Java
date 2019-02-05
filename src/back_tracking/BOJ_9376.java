package back_tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_9376 {
	public static int H, W, res;
	public static char[][] graph;
	public static final int[][] DIR = {{0,-1},{0,1},{1,0},{-1,0}};
	public static boolean[][] visited;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			graph = new char[H + 2][W + 2];
			visited = new boolean[H + 2][W + 2];
			res = 10_000;
			
			for(int i = 0; i < H + 2; i++)
				Arrays.fill(graph[i], '.');
			
			for(int i = 1; i < H + 1; i++) {
				String line = br.readLine();
				for(int j = 0; j < W; j++)
					graph[i][j + 1] = line.charAt(j);
			}
			
//			System.out.println(Arrays.deepToString(graph));
			
			visited[0][0] = true;
			dfs(0, 0, 0, 0);
			sb.append(res).append('\n');
			System.out.println();
		}
		System.out.print(sb);
	}
	
	public static void dfs(int x, int y, int wall, int cnt) {
		System.out.println(x + " " + y);
		if(res < wall)
			return;
		if(cnt == 2 && res > wall) {
			res = wall;
			return;
		}

		for(int[] dir : DIR) {
			int nextX = x + dir[0];
			int nextY = y + dir[1];
			
			if(0 > nextX || nextX >= W || nextY < 0 || nextY >= H)
				continue;
			char value = graph[nextY][nextX];
			if(visited[nextY][nextX] || value == '*')
				continue;
			visited[nextY][nextX] = true;
			if(value == '#')
				dfs(nextX, nextY, wall + 1, cnt);
			else if(value == '$')
				dfs(nextX, nextY, wall, cnt + 1);
			else
				dfs(nextX, nextY, wall, cnt);
			visited[nextY][nextX] = false;
		}
	}
}
