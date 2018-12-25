package minimum_spannig_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1922 {
	static int[] parent;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		init(N);
		
		for(int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			pq.add(new Node(a, b, c));
		}
		
		System.out.println(MST(pq));
	}
	
	private static class Node implements Comparable<Node>{
		int from;
		int to;
		int cost;
		
		public Node(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;		
		}

		@Override
		public int compareTo(Node n) {
			return this.cost < n.cost ? -1 : 1;
		}
	}
	
	private static void init(int N) {
		parent = new int[N + 1];
		for(int i = 1; i < N + 1; i++) {
			parent[i] = -1;
		}
	}
	
	private static int find(int x) {
		if(parent[x] < 0) 
			return x;
		parent[x] = find(parent[x]);
		return parent[x];
	}
	
	private static void merge(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) {
			return;
		}
		else if(x > y) {
			parent[y] += parent[x];
			parent[x] = y;
		}
		else {
			parent[x] += parent[y];
			parent[y] = x;
		}
	}
	
	private static boolean isCycle(int x, int y) {
		x = find(x);
		y = find(y);
		
		return x == y ? true : false;
	}
	
	private static int MST(PriorityQueue<Node> pq) {
		int mincost = 0;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(!isCycle(cur.from, cur.to)) {
				merge(cur.from, cur.to);
				mincost += cur.cost;
			}
		}
		return mincost;
	}
}
