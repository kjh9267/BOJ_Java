package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16562 {
	public static ArrayList<Integer> graph[];
	public static int cost[];
	public static boolean visited[];
	
	public static int bfs(int start) {
		Queue<Integer> queue = new LinkedList();
		queue.offer(start);
		visited[start] = true;
		int money = cost[start];
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			for(int friend : graph[cur]) {
				if(!visited[friend]) {
					visited[friend] = true;
					queue.offer(friend);
					if(money > cost[friend]) {
						money = cost[friend];
					}
				}
			}
		}
		return money;
	}
	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int res = 0;
		
		graph = new ArrayList[N + 1];
		cost = new int[N + 1];
		visited = new boolean[N + 1];
		
		for(int i = 1; i < N + 1; i++) {
			graph[i] = new ArrayList();
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i < N + 1; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph[v].add(w);
			graph[w].add(v);
		}
		
		for(int i = 1; i < N + 1; i++) {
			if(!visited[i]) {
				res += bfs(i);
			}
		}
		System.out.println(k - res < 0 ? "Oh no" : res);
	}	
}
