package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/1194
 * 
 */

public class BOJ_1194 {
	public static int N, M;
	public static char[][] graph;
	public static final int[][] DIR = {{0,-1},{1,0},{0,1},{-1,0}};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new char[N][M];
		for(int i = 0; i < N; i++)
			graph[i] = br.readLine().toCharArray();
		
		for(int row = 0; row < N; row++)
			for(int col = 0; col < M; col++)
				if(graph[row][col] == '0') {
					System.out.println(bfs(col,row));
					System.exit(0);
				}
	}
	
	public static class Node{
		int x;
		int y;
		int key;
		public Node(int x, int y, int key) {
			this.x = x;
			this.y = y;
			this.key = key;
		}
	}
	
	public static int bfs(int x, int y) {
		int cnt = 0;
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(x, y, 0));
		boolean[][][] visited = new boolean[N][M][64];
		visited[y][x][0] = true;
		
		while(!queue.isEmpty()) {
			int len = queue.size();
			cnt += 1;
			
			while(len-- > 0) {
				Node cur = queue.poll();

				for(int[] dir : DIR) {
					int nextX = cur.x + dir[0];
					int nextY = cur.y + dir[1];
					int nextKey = cur.key;
					
					if(nextX < 0 || nextX >= M || nextY < 0 || nextY >= N)
						continue;
					char value = graph[nextY][nextX];
					
					if(visited[nextY][nextX][cur.key] || value == '#')
						continue;
					if('A' <= value && value <= 'F' && (cur.key & (1 << value - 'A')) == 0)
						continue;
					if('a' <= value && value <= 'f')
						nextKey = cur.key | 1 << (value - 'a');
					else if(value == '1')
						return cnt;
					
					visited[nextY][nextX][nextKey] = true;
					queue.offer(new Node(nextX,nextY,nextKey));
				}
			}
		}
		return -1;
	}
}
