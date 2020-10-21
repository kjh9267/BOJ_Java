package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/16469
 *
 */

public class BOJ_16469 {
	private static int R;
	private static int C;
	private static int res;
	private static char[][] grid;
	private static Queue<Node> queue = new LinkedList<Node>();
	private static int[][] visited;
	private static final int[][] DIR = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		grid = new char[R][C];
		visited = new int[R][C];
		
		for(int i = 0; i < R; i++)
			grid[i] = br.readLine().toCharArray();
		
		for(int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			queue.add(new Node(y - 1, x - 1, i));
			visited[x - 1][y - 1] |= (1 << i);
		}
		int cnt = bfs();
		System.out.println(cnt == -1 ? -1 : cnt + " " + res);
	}

	private static class Node{
		int x, y, id;
		public Node(int x, int y, int id) {
			this.x = x;
			this.y = y;
			this.id = id;
		}
	}

	private static int bfs() {
		int cnt = 0;
		boolean key = false;
		while(!queue.isEmpty()) {
			int len = queue.size();
			cnt += 1;
			while(len-- > 0) {
				Node cur = queue.poll();
				for(int[] dir : DIR) {
					int nextX = cur.x + dir[0];
					int nextY = cur.y + dir[1];
					if(0 > nextX || nextX >= C || 0 > nextY || nextY >= R)
						continue;
					if((visited[nextY][nextX] & (1 << cur.id)) != 0)
						continue;
					if(grid[nextY][nextX] == '1')
						continue;
					visited[nextY][nextX] |= (1 << cur.id);
					if(visited[nextY][nextX] == 7) {
						key = true;
						res += 1;
					}
					queue.add(new Node(nextX, nextY, cur.id));
				}
			}
			if(key)
				return cnt;
		}
		return -1;
	}
}
