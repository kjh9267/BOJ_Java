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
 * @see https://www.acmicpc.net/problem/1647
 *
 */

public class BOJ_1647 {
	private static int[] parent;
	
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
		System.out.println(MST(pq,N));
	}

	private static class Node implements Comparable<Node>{
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

	private static void init(int N) {
		parent = new int[N+1];
		Arrays.fill(parent,-1);
	}

	private static int find(int x) {
		if(parent[x] < 0)
			return x;
		return parent[x] = find(parent[x]);
	}

	private static boolean merge(int x, int y) {
		x = find(x);
		y = find(y);
		if (x == y)
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

	private static int MST(PriorityQueue<Node> pq, int N) {
		int mincost = 0;
		int cnt = 0;
		
		while(true) {
			Node cur = pq.poll();
			
			if(merge(cur.from, cur.to)) {
				mincost += cur.cost;
				if(++cnt == N - 2)
					break;
			}
		}
		return mincost;
	}
}
