package topological_sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1516 {
	public static int[] time, indegree, res;
	public static ArrayList<Integer>[] graph;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		graph = new ArrayList[N+1];
		indegree = new int[N+1];
		time = new int[N+1];
		res = new int[N+1];
		
		for(int i = 0; i < N + 1; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		
		for (int i = 1; i < N + 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			
			while(st.hasMoreTokens()) {
				int prev = Integer.parseInt(st.nextToken());
				if(prev == -1) break;
				graph[prev].add(i);
				indegree[i] += 1;
			}
		}
		
		System.out.println(topological_sort(N, sb));
	}
	
	public static StringBuilder topological_sort(int N, StringBuilder sb) {
		Queue<Integer> q = new LinkedList<>();
		
		for(int i = 1; i < N + 1; i++) {
			if(indegree[i] == 0) {
				q.offer(i);
				res[i] = time[i];
			}
		}
		for(int i = 1; i < N + 1; i++) {
			int cur = q.poll();
			
			for(int next : graph[cur]) {
				res[next] = Math.max(res[next], res[cur] + time[next]);
				if(--indegree[next] == 0) q.offer(next);
			}
		}
		for(int i = 1; i < N + 1; i++) {
			sb.append(res[i]).append('\n');
		}
		
		return sb;
	}
}
