package depth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_9205 {
	public static int N;
	public static Node[] graph;
	public static boolean visited[], result;
	
	public static class Node{
		int x, y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static boolean dfs(int cur) {
		if (cur == N + 1) {
			return true;
		}
		
		visited[cur] = true;
		int x = graph[cur].x;
		int y = graph[cur].y;
		
		for (int i = 1; i < N + 2; i++) {
			int nextX = graph[i].x;
			int nextY = graph[i].y;
			int dist = Math.abs(x - nextX) + Math.abs(y - nextY);
			
			if (dist <= 1000 && !visited[i]) {
				result = dfs(i);
			}
		}
		return result;
	}
	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < t; i++) {
			N = Integer.parseInt(br.readLine());
			graph = new Node[N+2];
			
			for (int j = 0; j < N + 2; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				graph[j] = new Node(x, y);
			}
			visited = new boolean[N+2];
			System.out.println(dfs(0) ? "happy" : "sad");
		}
	}
}
