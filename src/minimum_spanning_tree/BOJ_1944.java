package minimum_spanning_tree;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/1944
 *
 */

public class BOJ_1944 {
	private static int N;
	private static int M;
	private static int keyNum;
	private static int[] parent;
	private static char[][] graph;
	private static boolean[][] visited;
	private static int[][] keys;
	private static boolean[] check;
	private static PriorityQueue<Node> pq;
	private static int[] dx = { 0, 1, 0, -1 };
	private static int[] dy = { -1, 0, 1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new char[N][N];
		keys = new int[N][N];
		parent = new int[M + 2];
		Arrays.fill(parent, -1);
		pq = new PriorityQueue<>();

		for (int i = 0; i < N; i++) {
			graph[i] = br.readLine().toCharArray();
		}

		init();
		System.out.println(MST(pq));
	}

	private static class Node implements Comparable<Node> {
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

	private static class Node2 {
		int x, y, start, dist;

		public Node2(int x, int y, int start, int dist) {
			this.x = x;
			this.y = y;
			this.start = start;
			this.dist = dist;
		}
	}

	private static void init() {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (graph[i][j] == 'K' || graph[i][j] == 'S')
					keys[i][j] = ++keyNum;

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (graph[i][j] == 'K' || graph[i][j] == 'S')
					bfs(j, i, keys[i][j]);

		for (int i = 0; i < M + 1; i++)
			if (!check[i]) {
				System.out.println(-1);
				System.exit(0);
			}
	}

	private static void bfs(int x, int y, int start) {
		Queue<Node2> queue = new LinkedList<>();
		queue.offer(new Node2(x, y, start, 0));
		visited = new boolean[N][N];
		check = new boolean[M + 1];
		visited[y][x] = true;

		while (!queue.isEmpty()) {
			Node2 cur = queue.poll();
			if (graph[cur.y][cur.x] == 'S' || graph[cur.y][cur.x] == 'K') {
				check[keys[cur.y][cur.x] - 1] = true;
				if (cur.start != keys[cur.y][cur.x]) {
					pq.offer(new Node(cur.start, keys[cur.y][cur.x], cur.dist));
				}
			}
			for (int i = 0; i < 4; i++) {
				int xx = cur.x + dx[i];
				int yy = cur.y + dy[i];

				if (0 <= xx && xx < N && 0 <= yy && yy < N && graph[yy][xx] != '1' && !visited[yy][xx]) {
					if (graph[cur.y][cur.x] == 'S' || graph[cur.y][cur.x] == 'K')
						queue.offer(new Node2(xx,yy,keys[cur.y][cur.x], 1));
					else
						queue.offer(new Node2(xx, yy, cur.start, cur.dist + 1));
					visited[yy][xx] = true;
				}
			}
		}
	}

	private static int find(int x) {
		if (parent[x] < 0)
			return x;
		return parent[x] = find(parent[x]);
	}

	private static boolean merge(int x, int y) {
		x = find(x);
		y = find(y);

		if (x == y)
			return false;
		else if (parent[x] > parent[y]) {
			parent[y] += parent[x];
			parent[x] = y;
		} else {
			parent[x] += parent[y];
			parent[y] = x;
		}
		return true;
	}

	private static int MST(PriorityQueue<Node> pq) {
		int mincost = 0;
		int cnt = 0;

		while (true) {
			Node cur = pq.poll();

			if (merge(cur.from, cur.to)) {
				mincost += cur.cost;
				if(++cnt == keyNum - 1)
					break;
			}
		}
		return mincost;
	}
}
