package minimum_spanning_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/1197
 * 
 */

public class BOJ_1197 {
	public static int[] parent;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			pq.offer(new Node(A,B,C));
		}
		init(V);
		System.out.println(MST(pq, V));
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
	public static void init(int V) {
		parent = new int[V+1];
		Arrays.fill(parent,-1);
	}
	public static int find(int x) {
		if(parent[x] < 0)
			return x;
		return parent[x] = find(parent[x]);
	}
	public static boolean merge(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y)
			return false;
		else if(parent[x] > parent[y]) {
			parent[y] += parent[x];
			parent[x] = y;
		}
		else {
			parent[x] += parent[y];
			parent[y] = x;
		}
		return true;
	}
	public static int MST(PriorityQueue<Node> pq, int V) {
		int mincost = 0;
		int cnt = 0;
		
		while(true) {
			Node cur = pq.poll();
			
			if(merge(cur.from, cur.to)) {
				mincost += cur.cost;
				if(++cnt == V - 1)
					break;
			}
		}
		return mincost;
	}
}
