package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2178 {
	
	private static char[][] graph;
	private static int[][] visited;
	private static int N;
	private static int M;
	private static int[] dx = {0, 1, 0, -1};
	private static int[] dy = {-1, 0, 1, 0};

	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new char[N][M];
		visited = new int[N][M];

		for(int i = 0; i < N; i++) {
			graph[i] = br.readLine().toCharArray();
		}

		for(int i = 0; i < N; i++) {
			Arrays.fill(visited[i], 0);
		}

		System.out.println(bfs());

	}
	private static class Point{

		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	private static int bfs() {
		Point cur;
		int x, y;
		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(0,0));
		visited[0][0] = 1;

		while(!queue.isEmpty()) {
			cur = queue.poll();
			for(int i = 0; i < 4; i++) {
				x = cur.x + dx[i];
				y = cur.y + dy[i];
				if(0 <= y && y < N && 0 <= x && x < M) {
					if(graph[y][x] == '1' && visited[y][x] == 0) {
						visited[y][x] = visited[cur.y][cur.x] + 1;
						queue.offer(new Point(x,y));
					}
				}
			}
		}


		return visited[N-1][M-1];
	}

}
