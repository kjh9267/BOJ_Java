package topological_sort;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1948 {
	private static int[] indegree;
	private static int[] res;
	private static ArrayList<Node>[] graph;
	private static ArrayList<Node>[] way;
	private static int cnt;
	private static int E;
	private static boolean[] visited;
	
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

	private static class Node{
		int cur, next, cost;
		
		public Node(int cur, int next, int cost) {
			this.cur = cur;
			this.next = next;
			this.cost = cost;
		}
	}

	private static void topological_sort(int N) {
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

	private static void dfs(int cur) {
		if(visited[cur])
			return;
		visited[cur] = true;
		for(Node n: way[cur]) {
			if(res[n.next] == res[n.cur] - n.cost) {
				cnt += 1;
				dfs(n.next);
			}
		}
	}
}
