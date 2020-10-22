package disjoint_set;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1717 {
	private static int[] parent;

	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		parent = new int[N+1];
		Arrays.fill(parent, -1);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if(op == 0) {
				merge(a, b);
			}
			else {
				if(find(a) == find(b)) {
					sb.append("YES").append("\n");
				}
				else {
					sb.append("NO").append("\n");
				}
			}
		}
		System.out.println(sb);
	}

	private static int find(int cur) {
		if(parent[cur] < 0) {
			return cur;
		}
		parent[cur] = find(parent[cur]);
		return parent[cur];
	}

	private static void merge(int a, int b) {
		a = find(a);
		b = find(b);
		if(a == b) {
			return;
		}
		else if(parent[a] > parent[b]) {
			parent[b] += parent[a];
			parent[a] = b;
		}
		else {
			parent[a] += parent[b];
			parent[b] = a;
		}
	}
}
