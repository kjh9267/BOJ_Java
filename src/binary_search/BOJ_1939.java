package binary_search;

/**
 * @author Junho
 * 
 * @see https://www.acmicpc.net/problem/1939
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1939 {
	private static int N;
	private static int M;
	private static int source;
	private static int sink;
	private static int MAX = 0;
	private static ArrayList<Node>[] graph;
	private static boolean[] visited;
	private static boolean flag;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N + 1];
		
		for(int i = 1; i < N + 1; i++)
			graph[i] = new ArrayList<Node>();
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph[x].add(new Node(y, cost));
			graph[y].add(new Node(x, cost));
			MAX = Math.max(MAX, cost);
		}
		st = new StringTokenizer(br.readLine());
		source = Integer.parseInt(st.nextToken());
		sink = Integer.parseInt(st.nextToken());
		
		System.out.println(binarySearch());
	}

	private static class Node{
		int next;
		int cost;
		public Node(int next, int cost) {
			this.next = next;
			this.cost = cost;
		}
	}

	private static int binarySearch() {
		int lo = 1;
		int hi = MAX + 1;
		
		while(lo + 1 < hi) {
			int mid = (lo + hi) >> 1;
			visited = new boolean[N + 1];
			visited[source] = true;
			flag = false;
			dfs(source, mid);
			if(flag)
				lo = mid;
			else
				hi = mid;
		}
		return lo;
	}

	private static void dfs(int cur, int cost) {
		if(cur == sink) {
			flag = true;
			return;
		}
		for(Node n : graph[cur]) {
			if(visited[n.next])
				continue;
			if(n.cost < cost)
				continue;
			visited[n.next] = true;
			dfs(n.next, cost);
		}
	}
}
