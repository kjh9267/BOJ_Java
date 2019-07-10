package binary_indexed_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/14268
 *
 */

public class BOJ_14268 {
	public static int N, cnt;
	public static int[] tree, start, end;
	public static ArrayList<Integer>[] adj;
	
	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		adj = new ArrayList[N + 1];
		start = new int[N + 1];
		end = new int[N + 1];
		tree = new int[N + 1];
		
		for(int i = 1; i < N + 1; i++)
			adj[i] = new ArrayList<Integer>();
		
		st = new StringTokenizer(br.readLine());
		st.nextToken();
		for(int i = 2; i < N + 1; i++)
			adj[Integer.parseInt(st.nextToken())].add(i);
		
		dfs(1);
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());
			if(op == 1) {
				int num = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				update(start[num], cost);
				update(end[num] + 1, -cost);
			}
			else {
				int num = Integer.parseInt(st.nextToken());
				sb.append(get(start[num])).append('\n');
			}
		}
		System.out.println(sb);
	}
	
	public static void dfs(int cur) {
		start[cur] = ++cnt;
		for(int nxt : adj[cur])
			dfs(nxt);
		end[cur] = cnt;
	}
	
	public static int get(int idx) {
		int res = 0;
		while(idx > 0) {
			res += tree[idx];
			idx -= (-idx & idx);
		}
		return res;
	}
	
	public static void update(int idx, int diff) {
		while(idx < N + 1) {
			tree[idx] += diff;
			idx += (-idx & idx);
		}
	}
}
