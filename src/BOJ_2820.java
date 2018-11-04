

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2820 {
	public static ArrayList<Integer>[] graph;
	public static int[] cost;
	public static String NEW_LINE = "\n";
	public static boolean[] visited;

	public static void dfs(int cur, int sum) {
		
		visited[cur] = true;

		for (int next : graph[cur]) {
			if (!visited[next]) {
				cost[next] += sum;
				dfs(next, sum);
			}
		}
	}

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N];
		visited = new boolean[N];
		cost = new int[N];
		cost[0] = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++) {
			graph[i] = new ArrayList();
		}

		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int money = Integer.parseInt(st.nextToken());
			int next = Integer.parseInt(st.nextToken()) - 1;
			graph[next].add(i);
			cost[i] = money;
		}
		
//		for(int i = 0; i < N; i++) {
//			System.out.println(graph[i]);
//		}
		
		for (int i = 0; i < M; i++) {
//			System.out.println(Arrays.toString(cost));
			st = new StringTokenizer(br.readLine());
			char query = st.nextToken().charAt(0);

			if (query == 'p') {
				visited = new boolean[N];
				int a = Integer.parseInt(st.nextToken()) - 1;
				int x = Integer.parseInt(st.nextToken());
				dfs(a, x);
			}

			if (query == 'u') {
				int a = Integer.parseInt(st.nextToken()) - 1;
				sb.append(cost[a]).append(NEW_LINE);
			}
		}
		System.out.println(sb.toString());
	}
}
