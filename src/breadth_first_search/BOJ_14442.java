package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14442 {
	public static char[][] graph;
	public static final int[][] DIR = { { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		graph = new char[N][M];

		for (int i = 0; i < N; i++) {
			graph[i] = br.readLine().toCharArray();
		}

		if(N == 1 && M == 1) {
			System.out.println(1);
			System.exit(0);
		}
		
		System.out.println(bfs(N, M, K));
	}

	public static class Node {
		int x;
		int y;
		int k;
		public Node(int x, int y, int k) {
			this.x = x;
			this.y = y;
			this.k = k;
		}
	}

	public static int bfs(int N, int M, int K) {
		Queue<Node> queue = new LinkedList<>();
		int[][] visited = new int[N][M];
		queue.offer(new Node(0, 0, 1));
		visited[0][0] = 1;
		int cnt = 1;

		while (!queue.isEmpty()) {
			int len = queue.size();
			cnt += 1;

			for (int i = 0; i < len; i++) {
				Node cur = queue.poll();

				for (int[] dir : DIR) {
					int next_x = cur.x + dir[0];
					int next_y = cur.y + dir[1];

					if (0 <= next_x && next_x < M && 0 <= next_y && next_y < N) {
						if (graph[next_y][next_x] == '0') {
							if ((visited[next_y][next_x] & (1 << cur.k)) == 0) {
								queue.offer(new Node(next_x, next_y, cur.k));
								visited[next_y][next_x] |= (1 << cur.k);

								if (next_x == M - 1 && next_y == N - 1)
									return cnt;
							}
						} 
						else {
							if (K + 1 > cur.k && (visited[next_y][next_x] & (1 << cur.k)) == 0) {
								queue.offer(new Node(next_x, next_y, cur.k + 1));
								visited[next_y][next_x] |= 1 << (cur.k + 1);

								if (next_x == M - 1 && next_y == N - 1)
									return cnt;
							}
						}
					}
				}
			}
		}
		return -1;
	}
}
