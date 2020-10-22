package topological_sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2252 {
	private static ArrayList<Integer>[] graph;
	private static int[] indegree;
	private static ArrayList<Integer> res;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		indegree = new int[N+1];
		res = new ArrayList<>();
		graph = new ArrayList[N+1];
		
		for(int i = 0; i < N + 1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			graph[A].add(B);
			indegree[B] += 1;
		}
		
		topological_sort(N);
		
		for(int i = 0; i < N; i++) {
			System.out.print(res.get(i) + " ");
		}
	}

	private static void topological_sort(int N) {
		Queue<Integer> q = new LinkedList<>();
		
		for(int i = 1; i < N + 1; i++) {
			if(indegree[i] == 0)
				q.offer(i);
		}
		
		while(N-- > 0) {
			int cur = q.poll();
			res.add(cur);
			for(int next : graph[cur]) {
				if(--indegree[next] == 0)
					q.offer(next);
			}
		}
	}
}
