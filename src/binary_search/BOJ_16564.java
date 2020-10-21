package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/16564
 *
 */

public class BOJ_16564 {

	private static int[] levels;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		levels = new int[N];
		
		for(int i = 0; i < N; i++)
			levels[i] = Integer.parseInt(br.readLine());
		Arrays.sort(levels);
		
		System.out.println(binarySearch(N, K));
	}

	private static long binarySearch(int N, int K) {
		long lo = 1;
		long hi = 1_000_000_001;
		
		while(lo + 1 < hi) {
			long mid = (lo + hi) >> 1;
			long sum = 0;

			for(int i = 0 ; i < N; i++)
				sum += mid < levels[i] ? 0 : mid - levels[i];
			if(sum <= K)
				lo = mid;
			else
				hi = mid;
		}
		return lo;
	}
}
