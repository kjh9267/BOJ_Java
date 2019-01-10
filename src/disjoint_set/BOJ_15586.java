package disjoint_set;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_15586 {
	public static int[] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		Link[] links = new Link[N - 1];
		PriorityQueue<Query> pq = new PriorityQueue<>();
		parent = new int[N + 1];
		int[] res = new int[Q];

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			links[i] = new Link(p, q, r);
		}

		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			pq.offer(new Query(i, v, k));
		}

		Arrays.sort(links);
		Arrays.fill(parent, -1);

		int index = 0;
		while (!pq.isEmpty()) {
			Query q = pq.poll();
			for (; index < N - 1; index++)
				if (links[index].cost >= q.k)
					merge(links[index].x, links[index].y);
				else
					break;
				
			res[q.index] = -parent[find(q.node)] - 1;
		}

		for (int i = 0; i < Q; i++) {
			sb.append(res[i]).append('\n');
		}
		System.out.println(sb);
	}

	public static class Link implements Comparable<Link> {
		int x;
		int y;
		int cost;

		public Link(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		@Override
		public int compareTo(Link l) {
			return l.cost - this.cost;
		}
	}

	public static class Query implements Comparable<Query> {
		int index;
		int node;
		int k;

		public Query(int index, int node, int k) {
			this.index = index;
			this.node = node;
			this.k = k;
		}

		@Override
		public int compareTo(Query q) {
			return q.k - this.k;
		}
	}

	public static int find(int x) {
		if (parent[x] < 0)
			return x;
		return parent[x] = find(parent[x]);
	}

	public static void merge(int x, int y) {
		x = find(x);
		y = find(y);

		if (x == y)
			return;
		else if (parent[x] > parent[y]) {
			parent[y] += parent[x];
			parent[x] = y;
		} else {
			parent[x] += parent[y];
			parent[y] = x;
		}
	}
}
