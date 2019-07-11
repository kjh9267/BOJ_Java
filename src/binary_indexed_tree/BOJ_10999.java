package binary_indexed_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/10999
 *
 */

public class BOJ_10999 {
	public static int N;
	public static long[] aTree, mTree;
	
	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		aTree = new long[N + 1];
		mTree = new long[N + 1];
		
		for(int i = 1; i < N + 1; i++) {
			long value = Long.parseLong(br.readLine());
			rangeUpdate(i, i, value);
		}
		
		for(int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			if(op == 1) {
				long value = Integer.parseInt(st.nextToken());
				rangeUpdate(s, e, value);
			}
			else {
				sb.append(sum(e) - sum(s - 1)).append('\n');
			}
		}
		System.out.println(sb);
	}
	
	public static void rangeUpdate(int l, int r, long diff) {
		update(l, diff, -(l - 1) * diff);
		update(r + 1, -diff, r * diff);
	}
	
	public static void update(int idx, long mDiff, long aDiff) {
		while(idx < N + 1) {
			mTree[idx] += mDiff;
			aTree[idx] += aDiff;
			idx += (-idx & idx);
		}
	}
	
	public static long sum(int idx) {
		long a = 0, m = 0;
		int i = idx;
		while(i > 0) {
			a += aTree[i];
			m += mTree[i];
			i -= (-i & i);
		}
		return a + m * idx;
	}
}
