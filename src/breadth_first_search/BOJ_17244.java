package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author Junho
 * 
 * @see https://www.acmicpc.net/problem/17244
 * 
 */

public class BOJ_17244 {
	public static int W, H, num;
	public static char[][] graph;
	public static int[][] dust;
	public static final int[][] DIR = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		dust = new int[H][W];
		graph = new char[H][W];
		num = 0;

		for (int i = 0; i < H; i++)
			graph[i] = br.readLine().toCharArray();

		for (int i = 0; i < H; i++)
			for (int j = 0; j < W; j++)
				if (graph[i][j] == 'X')
					dust[i][j] = num++;

		for (int i = 0; i < H; i++)
			for (int j = 0; j < W; j++)
				if (graph[i][j] == 'S') {
					graph[i][j] = '.';
					sb.append(bfs(j, i));
					break;
				}

		System.out.print(sb);
	}

	public static class Node {
		int x;
		int y;
		int dust;

		public Node(int x, int y, int dust) {
			this.x = x;
			this.y = y;
			this.dust = dust;
		}
	}

	public static int bfs(int x, int y) {
		Queue<Node> queue = new LinkedList<>();
		HashMap<Integer, Integer>[][] visited = new HashMap[H][W];

		for (int i = 0; i < H; i++)
			for (int j = 0; j < W; j++)
				visited[i][j] = new HashMap<Integer, Integer>();

		queue.offer(new Node(x, y, 0));
		visited[y][x].put(0, 0);
		int cnt = 0;

		while (!queue.isEmpty()) {
			int len = queue.size();
			cnt += 1;

			while (len-- > 0) {
				Node cur = queue.poll();

				for (int[] dir : DIR) {
					int nextX = cur.x + dir[0];
					int nextY = cur.y + dir[1];

					if (nextX < 0 || nextX >= W || nextY < 0 || nextY >= H)
						continue;
					if (visited[nextY][nextX].containsKey(cur.dust))
						continue;
					if (graph[nextY][nextX] == '.') {
						queue.offer(new Node(nextX, nextY, cur.dust));
						visited[nextY][nextX].put(cur.dust, 0);
					} else if (graph[nextY][nextX] == 'X') {
						int temp = cur.dust | (1 << dust[nextY][nextX]);
						queue.offer(new Node(nextX, nextY, temp));
						visited[nextY][nextX].put(temp, 0);
					}
					else if(graph[nextY][nextX] =='E') {
						if (cur.dust == (int) Math.pow(2, num) - 1)
							return cnt;
					}
				}
			}
		}
		return -1;
	}
}
