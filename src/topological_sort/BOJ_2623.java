package topological_sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2623 {
	public static int N, M;
	public static ArrayList<Integer>[] graph;
	public static int[] indegree;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N+1];
		indegree = new int[N+1];
		
		for(int i = 0; i < N + 1; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		
		while(M-- > 0) {
			int prev, cur, K;
			st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());
			prev = Integer.parseInt(st.nextToken());
			
			for(int i = 1; i < K; i++) {
				cur = Integer.parseInt(st.nextToken());
				indegree[cur]++;
				graph[prev].add(cur);
				prev = cur;
			}
		}

		System.out.println(topological_sort());
		
	}
	
	public static <T> StringBuilder topological_sort() {
		StringBuilder sb = new StringBuilder();
		Queue<Integer> q = new LinkedList<>();
		ArrayList<Integer> res = new ArrayList<>();
		
		for(int i = 1; i < N + 1; i++) {
			if(indegree[i] == 0) {
				q.offer(i);
			}
		}
		while(N-- > 0) {
			if(q.isEmpty()) {
				System.out.println(0);
				System.exit(0);
			}
			int cur = q.poll();
			res.add(cur);
			for(Integer next : graph[cur]) {
				if(--indegree[next] == 0)
					q.offer(next);
			}
		}
		
		int len = res.size();
		for(int i = 0; i < len; i++) {
			sb.append(res.get(i)).append('\n');
		}
		
		return sb;
	}
}
