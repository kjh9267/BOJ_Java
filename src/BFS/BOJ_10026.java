package BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_10026 {
	private static BufferedReader br;
	private static char[][] graph;
	private static char[][] graph2;
	private static int N;
	private static boolean[][] visited;
	private static int[] dx = { 0, 1, 0, -1 };
	private static int[] dy = { -1, 0, 1, 0 };

	public static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static int check(char[][] graph) {
		int cnt = 0;
		visited = new boolean[N][N];
			
		for (int i = 0; i < N; i++) {
			Arrays.fill(visited[i], false);
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j] == false) {
					bfs(j, i, graph);
					cnt += 1;
				}
			}
		}
		return cnt;
	}

	public static void bfs(int x, int y, char[][] graph) {
		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(x, y));
		visited[y][x] = true;

		while (!queue.isEmpty()) {
			Point cur = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nextX = cur.x + dx[i];
				int nextY = cur.y + dy[i];

				if (0 <= nextX && nextX < N && 0 <= nextY && nextY < N) {
					if (graph[cur.y][cur.x] == graph[nextY][nextX] && !visited[nextY][nextX]) {
						visited[nextY][nextX] = true;
						queue.offer(new Point(nextX, nextY));
					}
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new char[N][N];
		graph2 = new char[N][N];
		
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < N; i++) {
			graph[i] = br.readLine().toCharArray();
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(graph[i][j] == 'R') {
					graph2[i][j] = 'G';
				}
				else {
					graph2[i][j] = graph[i][j];
				}
			}
		}
		
		sb.append(check(graph)).append("\n");
		sb.append(check(graph2)).append("\n");
		
		System.out.println(sb.toString());
	}

}
