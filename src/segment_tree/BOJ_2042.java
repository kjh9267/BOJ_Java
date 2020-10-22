package segment_tree;

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
	private static int height;
	private static int size;
	private static long[] tree;
	private static long[] arr;
	private static final String NEW_LINE = "\n";

	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		height = (int) Math.ceil((Math.log(N)/Math.log(2)));
		size = (int) Math.pow(2, height+1);
		tree = new long[size];
		arr = new long[N+1];

		for(int i = 1; i < N+1; i++) {
			arr[i] = Long.parseLong(br.readLine()) % Long.MAX_VALUE;
		}

		init(1,1,N);

		for(int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if(a == 1) {
				long diff = c - arr[b];
				arr[b] = c;
				update(1,b,1,N,diff);

			}
			else {
				sb.append(sum(1,1,N,b,c)).append(NEW_LINE);
			}
		}
		System.out.println(sb.toString());
	}

	private static long init(int node, int start, int end) {
		if(start == end)
			return tree[node] = arr[start];
		int mid = (start + end) / 2;
		return tree[node] = init(node * 2, start, mid) + init(node * 2 + 1, mid + 1, end);
	}

	private static void update(int node, int index, int start, int end, long diff) {
		if (!(start <= index && index <= end))
	        return;

		tree[node] += diff;

		if(start != end) {
			int mid = (start + end) / 2;
			update(node * 2, index, start, mid, diff);
			update(node * 2 + 1, index, mid + 1, end, diff);
		}
	}

	private static long sum(int node, int start, int end, int left, int right) {
		if(left > end || right < start)
			return 0;
		if(left <= start && end <= right) {
			return tree[node];
		}
		int mid = (start + end) / 2;
		return sum(node * 2, start, mid, left, right) + sum(node * 2 + 1, mid + 1, end, left, right);
	}
}
