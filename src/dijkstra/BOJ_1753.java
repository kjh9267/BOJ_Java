package dijkstra;

// https://www.acmicpc.net/problem/1753

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1753 {

	public static final int INF = 200_000_000;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());

		ArrayList<Node>[] graph = new ArrayList[V + 1];

		for (int node = 1; node <= V; node++) {
			graph[node] = new ArrayList<>();
		}

		for (int loop = 0; loop < E; loop++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			graph[from].add(new Node(to, cost));
		}

		System.out.println(dijkstra(V, K, graph));

	}

	private static class Node implements Comparable<Node> {
		int node;
		int cost;

		public Node(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node other) {
			return this.cost - other.cost;
		}
	}

	private static StringBuilder dijkstra(int V, int start, ArrayList<Node>[] graph) {
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		int[] dist = new int[V + 1];

		Arrays.fill(dist, INF);
		dist[start] = 0;

		pq.add(new Node(start, dist[start]));

		while (!pq.isEmpty()) {
			int cur = pq.poll().node;
			
			for(Node next : graph[cur]) {
				if(dist[next.node] <= dist[cur] + next.cost) {
					continue;
				}
				dist[next.node] = dist[cur] + next.cost;
				pq.add(new Node(next.node,dist[next.node]));
			}
		}

		for(int node = 1; node <= V; node++) {
			sb.append(dist[node] == INF ? "INF" : dist[node]).append("\n");
		}

		return sb;
	}

}
