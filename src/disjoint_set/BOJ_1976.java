package disjoint_set;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1976 {
	public static int[] graph;
	public static int N;
	
	public static int find(int cur) {
		if (graph[cur] < 0) {
			return cur;
		}
		else {
			graph[cur] = find(graph[cur]);
		}
		return graph[cur];
	}
	
	public static void merge(int a, int b) {
		a = find(a);
		b = find(b);
		if (a == b) {
			return;
		}
		else if (a > b) {
			graph[b] += graph[a];
			graph[a] = b;
		}
		else {
			graph[b] += graph[a];
			graph[b] = a;
		}
	}
	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int[] path = new int[M];
		boolean key = true;
		
		
		graph = new int[N];
		Arrays.fill(graph, -1);
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				int road = Integer.parseInt(st.nextToken());
				if (road == 1) {
					merge(i,j);
				}
			}
		}
		

		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < M; i++) {
			path[i] = Integer.parseInt(st.nextToken()) - 1;
		}
		
		
		for (int i = 0; i < M - 1; i++) {
			if (find(path[i]) != find(path[i + 1])) {
				key = false;
				break;
			}
		}
		
		System.out.println(key ? "YES" : "NO");
	}
}
