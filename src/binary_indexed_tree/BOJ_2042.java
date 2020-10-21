package binary_indexed_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/2042
 *
 */

public class BOJ_2042 {
	private static long[] tree;
	private static long[] data;
	private static final String NEW_LINE = "\n";
	private static int N;
	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		tree = new long[N + 1];
		data = new long[N + 1];
		
		for(int i = 1; i < N + 1; i++) {
			data[i] = Long.parseLong(br.readLine());
			update(i, data[i]);
		}

		for(int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if(a == 1) {
				long diff = c - data[b];
				data[b] = c;
				update(b, diff);
			}
			else {
				sb.append(sum(c) - sum(b - 1)).append(NEW_LINE);
			}
		}
		System.out.println(sb.toString());
	}

	private static void update(int idx, long diff) {
		while(idx < N + 1) {
			tree[idx] += diff;
			idx += (-idx & idx);
		}
	}

	private static long sum(int idx) {
		long res = 0;
		while(idx > 0) {
			res += tree[idx];
			idx -= (-idx & idx);
		}
		return res;
	}
}
