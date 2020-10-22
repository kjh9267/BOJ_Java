package floyd;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1956 {

	private static final int INF = 10_001;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		int[][] graph = new int[V + 1][V + 1];
		int res = INF;

		for (int i = 0; i < V + 1; i++) {
			Arrays.fill(graph[i], INF);
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			graph[a][b] = c;
		}

		for (int k = 1; k < V + 1; k++) {
			for (int i = 1; i < V + 1; i++) {
				for (int j = 1; j < V + 1; j++) {
					if (graph[i][j] > graph[i][k] + graph[k][j]) {
						graph[i][j] = graph[i][k] + graph[k][j];
					}
				}
			}
		}
		
		for (int i = 1; i < V + 1; i++) {
			if (res > graph[i][i]) {
				res = graph[i][i];
			}
		}
		System.out.println(res == INF ? -1 : res);
	}
}
