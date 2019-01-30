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
 * @see https://www.acmicpc.net/problem/9328
 *
 */


public class BOJ_9328 {
	public static int h, w, res;
	public static char[][] graph;
	public static int[][] visited;
	public static boolean[][] document;
	public static final int[][] DIR = {{0,-1},{1,0},{0,1},{-1,0}};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			res = 0;
			st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			graph = new char[h + 2][w + 2];
			visited = new int[h + 2][w + 2];
			document = new boolean[h + 2][w + 2];
			
			for(int i = 0; i < h + 2; i++)
				Arrays.fill(graph[i], '.');
			for(int i = 1; i < h + 1; i++) {
				String line = br.readLine();
				for(int j = 1; j < w + 1; j++)
					graph[i][j] = line.charAt(j - 1);
			}
			
			char[] keys = br.readLine().toCharArray();
			int key = 1;
			
			if(keys[0] != '0')
				for(int i = 0; i < keys.length; i++)
					key |= (1 << keyToNum(keys[i]));
			bfs(0,0,key);
			sb.append(res).append('\n');
		}
		System.out.print(sb);
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
	
	public static void bfs(int x, int y, int key) {		
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(x,y,key));
		visited[y][x] = key;
		
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			for(int[] dir : DIR) {
				int nextX = cur.x + dir[0];
				int nextY = cur.y + dir[1];
				
				if(0 > nextX || nextX >= w + 2 || 0 > nextY || nextY >= h + 2)
					continue;
				char value = graph[nextY][nextX];
				if(value == '*')
					continue;
				if((visited[nextY][nextX] | cur.key) == visited[nextY][nextX])
					continue;
				if(isDoor(value) && !hasKey(value, cur.key))
					continue;
				if(isKey(value))
					cur.key |= (1 << keyToNum(value));
				if(value == '$' && !document[nextY][nextX]) {
					document[nextY][nextX] = true;
					res += 1;
				}
				visited[nextY][nextX] |= cur.key;
				queue.offer(new Node(nextX, nextY, visited[nextY][nextX]));
			}
		}
	}
	
	public static int keyToNum(char key) {
		return key - 'a' + 1;
	}
	
	public static boolean hasKey(char door, int key) {
		int toKey = door - 'A' + 1;
		if((key & (1 << toKey)) == 0)
			return false;
		return true;
	}
	
	public static boolean isKey(char c) {
		return 0 <= c - 'a' && c - 'a' < 26 ? true : false;
	}
	
	public static boolean isDoor(char c) {
		return 0 <= c - 'A' && c - 'A' < 26 ? true : false;
	}
}
