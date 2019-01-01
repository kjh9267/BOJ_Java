

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1948 {
	public static int[] indegree, res;
	public static ArrayList<Node>[] graph, way;
	public static int cnt, rcost, E;
	public static boolean[] visited;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		indegree = new int[N+1];
		way = new ArrayList[N+1];
		res = new int[N+1];
		graph = new ArrayList[N+1];
		visited = new boolean[N+1];
		cnt = 0;
		rcost = 0;
		
		for(int i = 0; i < N + 1; i++) {
			graph[i] = new ArrayList<Node>();
		}
		for(int i = 0; i < N + 1; i++) {
			way[i] = new ArrayList<Node>();
		}
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			graph[a].add(new Node(a,b,c));
			indegree[b] += 1;
		}
		
		topological_sort(N);
		
		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		System.out.println(res[E]);
		
		dfs(E);
		System.out.println(cnt);
	}
	
	public static class Node{
		int cur, next, cost;
		
		public Node(int cur, int next, int cost) {
			this.cur = cur;
			this.next = next;
			this.cost = cost;
		}
	}
	
	public static void topological_sort(int N) {
		Queue<Integer> q = new LinkedList<>();
		
		for(int i = 1; i < N + 1; i++) 
			if(indegree[i] == 0) 
				q.offer(i);
		
		while(N-- > 0) {
			int cur = q.poll();
			
			for(Node n : graph[cur]) {
				if(res[n.next] < res[n.cur] + n.cost)
					res[n.next] = res[n.cur] + n.cost;
				way[n.next].add(new Node(n.next,n.cur,n.cost));
				if(--indegree[n.next] == 0)
					q.offer(n.next);
			}
		}
	}
	
	public static void dfs(int cur) {
		if(res[E] - rcost == res[cur] && !visited[cur])
			cnt += 1;
		else
			return;
		visited[cur] = true;
		for(Node n: way[cur]) {
			rcost += n.cost;
			dfs(n.next);
			rcost -= n.cost;
		}
	}
}
