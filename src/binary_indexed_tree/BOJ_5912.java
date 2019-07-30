package binary_indexed_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/5912
 *
 */

public class BOJ_5912 {
	public static int N;
	public static int[] tree, res;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		tree = new int[N + 1];
		res = new int[N];
		while(K-- > 0) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			update(s, 1);
			update(e + 1, -1);
		}
		for(int i = 1; i < N + 1; i++)
			res[i - 1] = get(i);
		
		Arrays.sort(res);
		System.out.println(res[N / 2]);
	}
	public static void update(int idx, int diff) {
		while(idx < N + 1) {
			tree[idx] += diff;
			idx += (-idx & idx);
		}
	}
	public static int get(int idx) {
		int acc = 0;
		while(idx > 0) {
			acc += tree[idx];
			idx -= (-idx & idx);
		}
		return acc;
	}
}
