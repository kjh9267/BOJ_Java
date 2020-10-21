package binary_indexed_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/2820
 *
 */

public class BOJ_2820 {
	private static int N;
	private static int cnt = 1;
	private static int[] tree;
	private static int[] data;
	private static int[] start;
	private static int[] end;
	private static int[] temp;
	private static ArrayList<Integer>[] adj;
	
	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		adj = new ArrayList[N + 1];
		data = new int[N + 1];
		start = new int[N + 1];
		end = new int[N + 1];
		tree = new int[N + 1];
		temp = new int[N + 1];
		
		for(int i = 1; i < N + 1; i++)
			adj[i] = new ArrayList<Integer>();
		
		temp[1] = Integer.parseInt(br.readLine());
		for(int i = 2; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			int cost = Integer.parseInt(st.nextToken());
			int parent = Integer.parseInt(st.nextToken());
			adj[parent].add(i);
			temp[i] = cost;
		}
		
		dfs(1);
		for(int i = 1; i < N + 1; i++)
			data[start[i]] = temp[i];

		for(int i = 1; i < N + 1; i++) {
			update(start[i], data[start[i]]);
			update(start[i] + 1, -data[start[i]]);
		}

		for(int i = 0 ; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			char op = st.nextToken().charAt(0);
			if(op == 'p') {
				int s = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				update(start[s] + 1, c);
				update(end[s] + 1, -c);
			}
			else {
				int s = Integer.parseInt(st.nextToken());
				sb.append(get(start[s])).append('\n');
			}
		}
		System.out.println(sb);
	}

	private static void dfs(int cur) {
		start[cur] = cnt++;
		for(int next : adj[cur])
			dfs(next);
		end[cur] = cnt - 1;
	}

	private static void update(int idx, int diff) {
		while(idx < N + 1) {
			tree[idx] += diff;
			idx += (-idx & idx);
		}
	}

	private static int get(int idx) {
		int acc = 0;
		while(idx > 0) {
			acc += tree[idx];
			idx -= (-idx & idx);
		}
		return acc;
	}
}
