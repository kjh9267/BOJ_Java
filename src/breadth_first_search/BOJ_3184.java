package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3184 {
	public static char[][] graph;
	public static boolean[][] visited;
	public static final int[][] DIR = {{0,-1},{1,0},{0,1},{-1,0}};
	public static int R, C, O, V;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		graph = new char[R][C];
		visited = new boolean[R][C];
		
		for(int i = 0; i < R; i++)
			graph[i] = br.readLine().toCharArray();
		
		for(int i = 0; i < R; i++)
			for(int j = 0; j < C; j++)
				if(graph[i][j] != '#' && !visited[i][j])
					bfs(new Node(j,i));
		
		System.out.println(O + " " + V);
	}
	
	public static class Node{
		int x;
		int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void bfs(Node n) {
		int o = 0;
		int v = 0;
		Queue<Node> queue = new LinkedList<>();
		queue.offer(n);
		visited[n.y][n.x] = true;
		
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			
			if(graph[cur.y][cur.x] == 'o')
				o++;
			if(graph[cur.y][cur.x] == 'v')
				v++;
				
			for(int[] dir : DIR) {
				int next_x = cur.x + dir[0];
				int next_y = cur.y + dir[1];
				
				if(0 <= next_x && next_x < C && 0 <= next_y && next_y < R) {
					if(graph[next_y][next_x] != '#' && !visited[next_y][next_x]) {
						queue.offer(new Node(next_x, next_y));
						visited[next_y][next_x] = true;
					}
				}
			}
		}
		if(o > v)
			O += o;
		else
			V += v;	
	}
}
