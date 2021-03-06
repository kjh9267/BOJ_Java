package disjoint_set;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1976 {
	private static int[] parent;
	private static int N;

	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int[] path = new int[M];
		boolean key = true;


		parent = new int[N];
		Arrays.fill(parent, -1);

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

	private static int find(int cur) {
		if (parent[cur] < 0) {
			return cur;
		}
		else {
			parent[cur] = find(parent[cur]);
		}
		return parent[cur];
	}

	private static void merge(int a, int b) {
		a = find(a);
		b = find(b);
		if (a == b) {
			return;
		}
		else if (parent[a] > parent[b]) {
			parent[b] += parent[a];
			parent[a] = b;
		}
		else {
			parent[b] += parent[a];
			parent[b] = a;
		}
	}
}
