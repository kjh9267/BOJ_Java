package dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_4184 {
	private static int r;
	private static int c;
	private static int[][] dist;
	private static final int[][] dir = {{0,-1},{1,-1},{1,0},{1,1},{0,1},{-1,1},{-1,0},{-1,-1}};
	private static final int INF = Integer.MAX_VALUE;

	public static void main(String args[]) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		int[][] graph = new int[r][c];

		for(int i = 0; i < r; i++) {
			String line = br.readLine();
			for(int j = 0; j < c; j++) {
				graph[i][j] = line.charAt(j) - '0';
			}
		}

		int n = Integer.parseInt(br.readLine());

		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());

			int startY = Integer.parseInt(st.nextToken()) - 1;
			int startX = Integer.parseInt(st.nextToken()) - 1;
			int endY = Integer.parseInt(st.nextToken()) - 1;
			int endX = Integer.parseInt(st.nextToken()) - 1;

			dijkstra(graph, startX, startY, endX, endY);

			sb.append(dist[endY][endX]).append('\n');
		}
		System.out.println(sb);
	}

	private static void dijkstra(int[][] graph, int startX, int startY, int endX, int endY) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.offer(new Node(startX, startY, 0));
		dist = new int[r][c];

		for(int i = 0; i < r; i++) {
			Arrays.fill(dist[i], INF);
		}

		dist[startY][startX] = 0;

		while(!pq.isEmpty()) {
			Node cur = pq.poll();

			for(int i = 0; i < 8; i++) {
				int nextX = cur.x + dir[i][0];
				int nextY = cur.y + dir[i][1];
				int cost = 1;
				if (0 <= nextX && nextX < c && 0 <= nextY && nextY < r) {
					if (graph[cur.y][cur.x] == i) {
						cost = 0;
					}
					if (dist[nextY][nextX] > cur.cost + cost) {
						dist[nextY][nextX] = cur.cost + cost;
						pq.offer(new Node(nextX, nextY, dist[nextY][nextX]));
					}
				}
			}
		}
	}
	private static class Node implements Comparable<Node> {
		int x, y, cost;

		public Node(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node n) {
			if(this.cost > n.cost) return 1;
			else if(this.cost == n.cost) return 0;
			else return -1;
		}
	}
}
