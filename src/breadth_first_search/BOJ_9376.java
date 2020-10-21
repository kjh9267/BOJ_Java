package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_9376 {
	private static int H;
	private static int W;
	private static int cnt;
	private static int door;
	private static final int[][] DIR = {{0,-1},{0,1},{1,0},{-1,0}};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			char[][] graph = new char[H + 2][W + 2];
			int[][] nodes = new int[2][2];
			int[][] res = new int[H+2][W+2];
			
			for(int i = 0; i < H + 2; i++)
				Arrays.fill(graph[i], '.');
			
			int cnt = 0;
			for(int i = 1; i < H + 1; i++) {
				String line = br.readLine();
				for(int j = 0; j < W; j++) {
					char value = line.charAt(j);
					if(value == '$') {
						nodes[cnt][0] = j + 1;
						nodes[cnt][1] = i;
						cnt += 1;
					}
					graph[i][j + 1] = value;
				}
			}
			
			bfs(0,0,graph,nodes,res);
			bfs(nodes[0][0],nodes[0][1],graph,nodes,res);
			bfs(nodes[1][0],nodes[1][1],graph,nodes,res);
			
			for(int i = 0; i < H + 2; i++) {
				for(int j = 0; j < W + 2; j++) {
					if(graph[i][j] == '#')
						res[i][j] -= 2;
				}
			}

			int min = 10000;
			for(int i = 0; i < H + 2; i++) {
				for(int j = 0; j < W + 2; j++) {
					if(graph[i][j] != '*' && min > res[i][j])
						min = res[i][j];
				}
			}
			sb.append(min).append('\n');
		}
		System.out.print(sb);
	}

	private static class Node{
		int x;
		int y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static void bfs(int startX, int startY, char[][] graph, int[][] node, int[][] res) {
		Queue<Node> queue = new LinkedList<>();
		int[][] visited = new int[H + 2][W + 2];
		for(int i = 0; i < H + 2; i++)
			Arrays.fill(visited[i], -1);
		
		queue.offer(new Node(startX, startY));
		visited[startY][startX] = 0;
		
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			
			for(int[] dir : DIR) {
				int nextX = cur.x + dir[0];
				int nextY = cur.y + dir[1];
				int nextDoor = visited[cur.y][cur.x];
				
				if(0 > nextX || nextX >= W + 2 || nextY < 0 || nextY >= H + 2)
					continue;
				char value = graph[nextY][nextX];

				if(value == '*')
					continue;
				if(value == '#') {
					if(visited[nextY][nextX] == -1 || visited[nextY][nextX] > nextDoor + 1) {
						visited[nextY][nextX] = nextDoor + 1;
						queue.offer(new Node(nextX, nextY));
					}
				}
				else {
					if(visited[nextY][nextX] == -1 || visited[nextY][nextX] > nextDoor) {
						visited[nextY][nextX] = nextDoor;
						queue.offer(new Node(nextX, nextY));
					}
				}
			}
		}
		for(int i = 0; i < H + 2; i++) {
			for(int j = 0; j < W + 2; j++) {
				res[i][j] += visited[i][j];
			}
		}
	}
}
