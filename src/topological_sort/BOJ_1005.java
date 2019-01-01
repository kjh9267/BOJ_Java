package topological_sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1005 {
	public static ArrayList<Integer>[] graph;
	public static int[] time, indegree, res;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			graph = new ArrayList[N + 1];
			indegree = new int[N+1];
			time = new int[N+1];
			
			for(int i = 0; i < N + 1; i++) {
				graph[i] = new ArrayList<Integer>();
			}

			st = new StringTokenizer(br.readLine());
			for(int i = 1; i < N + 1; i++) {
				time[i] = Integer.parseInt(st.nextToken());
			}
			
			while(M-- > 0) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				graph[x].add(y);
				indegree[y] += 1;
			}
			
			topological_sort(N);
			
			int W = Integer.parseInt(br.readLine());
			sb.append(res[W]).append('\n');
		}
		System.out.println(sb);
	}
	
	public static void topological_sort(int N) {
		Queue<Integer> q = new LinkedList<>();
		res = new int[N+1];
		
		for(int i = 1; i < N + 1; i++) {
			if(indegree[i] == 0) {
				q.offer(i);
				res[i] = time[i];
			}
		}
		
		while(N-- > 0) {
			int cur = q.poll();
			for(int next : graph[cur]) {
				res[next] = Math.max(res[cur] + time[next], res[next]);
				if (--indegree[next] == 0) 
					q.offer(next);
			}	
		}
	}
}
