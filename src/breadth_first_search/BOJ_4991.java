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
 * @see https://www.acmicpc.net/problem/4991
 * 
 */

public class BOJ_4991 {
	private static int W;
	private static int H;
	private static int num;
	private static char[][] graph;
	private static int[][] dust;
	private static final int[][] DIR = {{1,0},{0,1},{-1,0},{0,-1}};
	
	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			dust = new int[H][W];
			graph = new char[H][W];
			num = 0;
			
			for(int i = 0; i < H; i++)
				graph[i] = br.readLine().toCharArray();
			
			if(W == 0 && H == 0)
				break;
			
			for(int i = 0; i < H; i++)
				for(int j = 0; j < W; j++)
					if(graph[i][j] == '*')
						dust[i][j] = num++;

			for(int i = 0; i < H; i++)
				for(int j = 0; j < W; j++)
					if(graph[i][j] == 'o') {
						graph[i][j] = '.';
						sb.append(bfs(j,i)).append('\n');
						break;
					}
		}
		System.out.print(sb);
	}

	private static class Node{
		int x;
		int y;
		int dust;
		
		public Node(int x, int y, int dust) {
			this.x = x;
			this.y = y;
			this.dust = dust;
		}
	}

	private static int bfs(int x, int y) {
		Queue<Node> queue = new LinkedList<>();
		HashMap<Integer,Integer>[][] visited = new HashMap[H][W];
		
		for(int i = 0; i < H; i++)
			for(int j = 0; j < W; j++)
				visited[i][j] = new HashMap<Integer,Integer>();
		
		queue.offer(new Node(x,y,0));
		visited[y][x].put(0,0);
		int cnt = 0;

		while(!queue.isEmpty()) {
			int len = queue.size();
			cnt += 1;

			while(len-- > 0) {
				Node cur = queue.poll();

				for(int[] dir : DIR) {
					int nextX = cur.x + dir[0];
					int nextY = cur.y + dir[1];
					
					if(nextX < 0 || nextX >= W || nextY < 0 || nextY >= H)
						continue;
					if(visited[nextY][nextX].containsKey(cur.dust))
						continue;
					if(graph[nextY][nextX] == '.') {
						queue.offer(new Node(nextX,nextY,cur.dust));
						visited[nextY][nextX].put(cur.dust,0);
					}
					else if(graph[nextY][nextX] == '*') {
						int temp = cur.dust | (1 << dust[nextY][nextX]);
						queue.offer(new Node(nextX,nextY,temp));
						visited[nextY][nextX].put(temp,0);
						if(temp == (int) Math.pow(2,num) - 1)
							return cnt;
					}
				}
			}
		}
		return -1;
	}
}
