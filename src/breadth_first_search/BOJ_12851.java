package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_12851 {

	private static final int MAX = 100_001;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] graph = new int[MAX];

		int[] res = bfs(graph, N, M);

		System.out.println(res[0]);
		System.out.println(res[1]);

	}

	public static int[] bfs(int[] graph, int N, int M) {

		int path = 0;
		int time = 0;
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[100001];

		queue.offer(N);
		Arrays.fill(visited, false);

		while (!queue.isEmpty()) {

			time += 1;

			int qsize = queue.size();

			for (int i = 0; i < qsize; i++) {

				int cur = queue.poll();

				visited[cur] = true;

				if (cur == M) {
					path += 1;
				}

				if (0 <= cur - 1 && visited[cur - 1] == false) {
					queue.offer(cur - 1);
				}

				if (cur + 1 < MAX && visited[cur + 1] == false) {
					queue.offer(cur + 1);
				}

				if (cur * 2 < MAX && visited[cur * 2] == false) {
					queue.offer(cur * 2);
				}
			}
			if (path != 0) {
				return new int[] { time - 1, path };
			}
		}
		return new int[] { time, path };
	}
}
