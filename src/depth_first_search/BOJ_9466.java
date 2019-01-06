package depth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_9466 {
	public static int[] graph;
	public static boolean[] visited, finished;
	public static int cycle;
	
	public static void dfs(int cur) {
		if(finished[cur]) {
			return;
		}
		visited[cur] = true;
		int next = graph[cur];

		if(visited[next] && !finished[next]) {
			for(int index = next; index != cur; index = graph[index]) {
				cycle += 1;
			}
			cycle += 1;
		}
		else {
			dfs(graph[cur]);
		}
		finished[cur] = true;
	}
	
	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < T; i++) {
			int N = Integer.parseInt(br.readLine());
			graph = new int[N];
			finished = new boolean[N];
			visited = new boolean[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			cycle = 0;
			
			for(int j = 0; j < N; j++) {
				graph[j] = Integer.parseInt(st.nextToken()) - 1;
			}
			
			for(int j = 0; j < N; j++) {
				if(!visited[j]) {
					dfs(j);
				}
			}
			sb.append(N - cycle).append('\n');
		}
		System.out.println(sb);
	}
}
