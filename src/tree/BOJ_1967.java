package tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/1967
 *
 */

public class BOJ_1967 {
	private static ArrayList<Node>[] tree;
	private static int[] visited;
	private static int res;
	private static int leaf;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		tree = new ArrayList[N + 1];
		
		for(int i = 1; i < N + 1; i++)
			tree[i] = new ArrayList<Node>();
		
		for(int i = 1; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			tree[parent].add(new Node(child, cost));
			tree[child].add(new Node(parent, cost));
		}
		
		visited = new int[N + 1];
		visited[0] = 0;
		Arrays.fill(visited, -1);
		dfs(1);
		Arrays.fill(visited, -1);
		visited[leaf] = 0;
		res = 0;
		dfs(leaf);
		System.out.println(res);
	}

	private static class Node{
		int next;
		int cost;
		public Node(int next, int cost) {
			this.next = next;
			this.cost = cost;
		}
	}

	private static void dfs(int cur) {
		if(visited[cur] > res) {
			res = visited[cur];
			leaf = cur;
		}
		for(Node n : tree[cur]) {
			if(visited[n.next] != -1)
				continue;
			visited[n.next] = visited[cur] + n.cost;
			dfs(n.next);
		}
	}
}
