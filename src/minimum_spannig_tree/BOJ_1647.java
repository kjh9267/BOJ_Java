package minimum_spannig_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1647 {
	public static int[] parent;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			pq.offer(new Node(A, B, C));
		}
		
		init(N);
		System.out.println(MST(pq));
	}
	
	
	public static class Node implements Comparable<Node>{
		int from, to, cost;
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
	
	public static void init(int N) {
		parent = new int[N+1];
		Arrays.fill(parent,-1);
	}
	
	public static int find(int x) {
		if(parent[x] < 0) {
			return x;
		}
		parent[x] = find(parent[x]);
		return parent[x];
	}
	
	public static void merge(int x, int y) {
		x = find(x);
		y = find(y);
		if (x == y) {
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
	
	public static boolean isCycle(int x, int y) {
		x = find(x);
		y = find(y);
		
		return x == y ? true : false;
	}
	
	public static int MST(PriorityQueue<Node> pq) {
		int mincost = 0;
		int maxcost = 0;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(!isCycle(cur.from, cur.to)) {
				if(maxcost < cur.cost) {
					maxcost = cur.cost;
				}
				merge(cur.from, cur.to);
				mincost += cur.cost;
			}
		}
		return mincost - maxcost;
	}
}
