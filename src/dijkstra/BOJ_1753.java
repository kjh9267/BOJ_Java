package dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import dijkstra.BOJ_1753.Node;

public class BOJ_1753 {

	public static ArrayList<Node>[] graph = null;
	public static final int INF = 200_000_000;
	public static int[] dist;

	public static class Node implements Comparable<Node> {
		int node;
		int cost;

		public Node(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node n) {
			if (this.cost > n.cost) {
				return 1;
			} 
			else if (this.cost == n.cost) {
				return 0;
			} 
			else {
				return -1;
			}

		}
	}

	public static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		
		pq.add(new Node(start, dist[start]));

		while (!pq.isEmpty()) {
			int cur = pq.poll().node;
			
			for(Node next : graph[cur]) {
				if(dist[next.node] > dist[cur] + next.cost) {
					dist[next.node] = dist[cur] + next.cost;
					pq.add(new Node(next.node,dist[next.node]));
				}
			}
		}

	}

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());

		graph = new ArrayList[V + 1];

		for (int i = 0; i < V + 1; i++) {
			graph[i] = new ArrayList<>();
		}

		dist = new int[V + 1];
		Arrays.fill(dist, INF);
		dist[K] = 0;

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			graph[from].add(new Node(to, cost));
		}
		
		dijkstra(K);
		
		for(int i = 1; i < V+1; i++) {
			sb.append(dist[i] == INF ? "INF" : dist[i]).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
