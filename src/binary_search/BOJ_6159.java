package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/6159
 *
 */

public class BOJ_6159 {
	private static int[] data;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int res = 0;
		
		data = new int[N];
		for(int i = 0; i < N; i++)
			data[i] = Integer.parseInt(br.readLine());
		Arrays.sort(data);
		
		for(int i = 0; i < N - 1; i++) {
			int cnt = binarySearch(i, N, S, i + 1);
			res += cnt - i;
		}
		System.out.println(res);
	}

	private static int binarySearch(int cur, int N, int S, int lo) {
		int hi = N;
		
		while(lo + 1 < hi) {
			int mid = (lo + hi) >> 1;
			if(data[cur] + data[mid] <= S)
				lo = mid;
			else
				hi = mid;
		}
		if(lo == cur + 1 && data[lo] + data[cur] > S)
			return cur;
		return lo;
	}
}
